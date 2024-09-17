package com.mishaki.knobutil

import android.view.View
import android.view.View.AccessibilityDelegate
import android.view.ViewGroup
import android.view.ViewParent
import android.view.accessibility.AccessibilityEvent
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import com.mishaki.knobutil.listener.KnobFocusChangedObserver
import com.mishaki.knobutil.util.AutoClearViewList
import com.mishaki.knobutil.view.BaseKnobView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.lang.ref.WeakReference
import java.util.EnumMap
import java.util.Stack

object KnobUtil {
    @JvmField
    var debounceTime = 300L
    @JvmField
    var isAutoHideKnob = true
    @JvmField
    var autoHideKnobTime = 5_000L
    var currentPage = ""
        private set

    private val pageViewInfoList = HashMap<String, KnobViewInfo>()
    private val pageEnableList = HashMap<String, Boolean>()
    private val pageNextPageRuleList = HashMap<String, MutableMap<KnobAction, String>>()
    private var preTime = 0L
    private var coroutineContext = Job() + Dispatchers.Main
    private val autoHideKnobFlow = MutableSharedFlow<Unit>()
    private val knobFocusChangedObserverList = ArrayList<KnobFocusChangedObserver>()
    private var isInitialized = false

    @JvmStatic
    fun init() {
        if (isInitialized) {
            return
        }
        isInitialized = true
        if (coroutineContext.job.isCancelled) {
            coroutineContext = Job() + Dispatchers.Main
        }
        CoroutineScope(coroutineContext).launch {
            autoHideKnobFlow.collectLatest {
                delay(autoHideKnobTime)
                withContext(Dispatchers.Main) {
                    getPageViewInfo(currentPage)?.getCurrentFocusedView()?.onClearKnobFocus()
                }
            }
        }
    }

    @JvmStatic
    fun collectView(rootView: View): List<BaseKnobView<*>> {
        val allView = mutableListOf<View>()
        val stack = Stack<View>()
        stack.push(rootView)

        var scrollView: View? = null

        while (stack.isNotEmpty()) {
            val currentView = stack.pop()
            allView.add(currentView)
            if (currentView is ViewGroup) {
                if (scrollView == null && currentView.isScrollView()) {
                    scrollView = currentView
                }
                for (i in currentView.childCount - 1 downTo 0) {
                    val child = currentView.getChildAt(i)
                    stack.push(child)
                }
            }
        }

        val viewList = AutoClearViewList()

        for (view in allView) {
            if (view !is BaseKnobView<*>) {
                continue
            }
            viewList.add(view)
            view.putData(KEY_ROOT_VIEW, rootView)
            collectNextFocusView(rootView, view)
            if (scrollView == null){
                view.putData(KEY_SCROLL_HELPER, null)
                continue
            }
            val scrollHelperList = ArrayList<View>()
            var viewParent: ViewParent? = view.parent
            var currentScrollView: View = scrollView
            while (viewParent != null && viewParent != rootView) {
                val parent = viewParent as? View
                if (parent != null) {
                    if (parent.isScrollView()) {
                        currentScrollView = parent
                        break
                    } else {
                        scrollHelperList.add(parent)
                    }
                }
                viewParent = viewParent.parent
            }
            view.putData(KEY_SCROLL_HELPER, ScrollHelper(currentScrollView, scrollHelperList))
        }
        viewList.bindView(rootView)
        return viewList
    }

    fun updateViewList(page: String, viewList: List<BaseKnobView<*>>) {
        val viewInfo = getPageViewInfo(page) ?: KnobViewInfo().also {
            pageViewInfoList[page] = it
        }
        viewInfo.getCurrentFocusedView()?.onClearKnobFocus()
        viewInfo.viewList.clear()
        viewInfo.viewList.addAll(viewList)
        viewInfo.viewList.forEach {
            it.putData(KEY_CURRENT_PAGE, page)
            it.view.accessibilityDelegate = knobAccessibilityDelegate
        }
        viewInfo.setPreFocusedView(null)
    }

    fun updateViewListAndRequestFocus(page: String, viewList: List<BaseKnobView<*>>, index: Int, isShowFocus: Boolean) {
        updateViewList(page, viewList)
        val viewInfo = getPageViewInfo(page) ?: return
        currentPage = page
        viewList.getOrNull(index)?.apply {
            viewInfo.setPreFocusedView(this)
            if (isShowFocus) {
                dispatchKnobFocusAction(KnobAction.ACTIVE)
                onKnobFocused(KnobAction.ACTIVE)
                startAutoHideKnobTask()
            }
        }
    }

    fun updateViewIndex(page: String, index: Int, isShowFocus: Boolean) {
        val viewInfo = getPageViewInfo(page) ?: return
        viewInfo.getCurrentFocusedView()?.onClearKnobFocus()
        var view: BaseKnobView<*>? = null
        if (index == KnobIndex.CURRENT_VIEW_INDEX) {
            view = viewInfo.getCurrentFocusedView()
            if (view == null) {
                view = viewInfo.viewList.getOrNull(0)
            }
        } else if (index != KnobIndex.INVALIDATE_INDEX) {
            view = viewInfo.viewList.getOrNull(index)
        }
        view?.apply {
            viewInfo.setPreFocusedView(this)
            if (isShowFocus) {
                dispatchKnobFocusAction(KnobAction.ACTIVE)
                onKnobFocused(KnobAction.ACTIVE)
                tryToScrollIfNeed()
                startAutoHideKnobTask()
            }
        }
    }

    fun requestFocus(page: String, index: Int, isShowFocus: Boolean) {
        val preViewInfo = getPageViewInfo(currentPage)
        preViewInfo?.getCurrentFocusedView()?.onClearKnobFocus()
        currentPage = page
        updateViewIndex(page, index, isShowFocus)
    }

    fun updateEnable(page: String, enabled: Boolean) {
        pageEnableList[page] = enabled
    }

    fun addNextPageRule(currentPage: String, action: KnobAction, nextPage: String) {
        pageNextPageRuleList.getOrPut(currentPage) { EnumMap(KnobAction::class.java) }[action] = nextPage
    }

    fun removeNextPageRule(currentPage: String) {
        pageNextPageRuleList.remove(currentPage)
    }

    fun removeNextPageRule(currentPage: String, action: KnobAction) {
        pageNextPageRuleList[currentPage]?.remove(action)
    }

    fun clearNextPageRule() {
        pageNextPageRuleList.clear()
    }

    @JvmStatic
    fun onKnobChanged(knobAction: Int) {
        val action = KnobAction.get(knobAction) ?: return
        if (action == KnobAction.ENTER) {
            onKnobEnter()
        } else {
            onKnobChangedWithoutEnter(action)
        }
    }

    @JvmStatic
    fun onBackPressed(): Boolean {
        return getPageViewInfo(currentPage)?.getCurrentFocusedView()?.onKnobBackPressed() ?: false
    }

    @JvmStatic
    fun onDestroy() {
        isInitialized = false
        clearKnobFocusChangedObserver()
        currentPage = ""
        pageViewInfoList.values.forEach(KnobViewInfo::recycle)
        pageViewInfoList.clear()
        pageEnableList.clear()
        pageNextPageRuleList.clear()
        coroutineContext.cancel()
    }

    @JvmStatic
    fun addKnobFocusChangedObserver(observer: KnobFocusChangedObserver) {
        if (knobFocusChangedObserverList.contains(observer).not()) {
            knobFocusChangedObserverList.add(observer)
        }
    }

    @JvmStatic
    fun removeKnobFocusChangedObserver(observer: KnobFocusChangedObserver) {
        knobFocusChangedObserverList.remove(observer)
    }

    @JvmStatic
    fun clearKnobFocusChangedObserver() {
        knobFocusChangedObserverList.clear()
    }

    @JvmStatic
    fun hasAnyKnobFocus(): Boolean {
        for ((page, viewInfo) in pageViewInfoList) {
            if (pageEnableList[page] ?: false) {
                val result = viewInfo.viewList.any { it.hasKnobFocus() }
                if (result) {
                    return true
                }
            }
        }
        return false
    }

    @JvmStatic
    fun reCollectViewNextFocusView(knobView: BaseKnobView<*>) {
        val rootView = knobView.getData(KEY_ROOT_VIEW)?.let { it as? View } ?: return
        collectNextFocusView(rootView, knobView)
    }

    fun getViewList(focusView: String): List<BaseKnobView<*>> {
        return getPageViewInfo(focusView)?.viewList ?: ArrayList()
    }

    private fun onKnobChangedWithoutEnter(action: KnobAction) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - preTime < debounceTime){
            return
        }
        preTime = currentTime
        startAutoHideKnobTask()
        knobFocusChangedObserverList.forEach { it.onKnobFocusChanged(action) }
        val viewInfo = getPageViewInfo(currentPage) ?: return
        val currentFocusedView = viewInfo.getCurrentFocusedView()
        val intercepted = currentFocusedView?.isInterceptNextKnobAction(action) ?: false
        if (intercepted) {
            currentFocusedView?.onKnobFocused(action)
            return
        }
        var nextFocusView = findNextFocusView(currentFocusedView, action)
        if (nextFocusView == null) {
            nextFocusView = findNextFocusViewByActionChain(currentFocusedView, viewInfo.viewList, action)
        }
        if (nextFocusView != null) {
            currentFocusedView?.onClearKnobFocus()
            viewInfo.setPreFocusedView(nextFocusView)
            nextFocusView.onKnobFocused(action)
            nextFocusView.tryToScrollIfNeed()
            return
        }
        // 处理逻辑，先判断左右旋是否有View handle，如果没有就判断page是否handle，如果还没有再执行currentFocusedView.onKnobFocused
        // 为什么要这样做？当左右旋没有被View消费时，尝试将左右旋给page，可以增加旋钮功能的灵活性
        // 如果5秒内没有操作，旋钮就会被隐藏。此时如果左右旋没有被消费，就必须调用currentFocusedView.onKnobFocused，否则旋钮不会重新显示
        if (action == KnobAction.KNOB_LEFT || action == KnobAction.KNOB_RIGHT) {
            findTargetView(viewInfo, action)?.apply {
                if (viewInfo.viewList.size != 1) {
                    currentFocusedView?.onClearKnobFocus()
                }
                onKnobFocused(action)
                tryToScrollIfNeed()
                return
            }
        }
        val actionMap = pageNextPageRuleList[currentPage]
        if (actionMap != null) {
            val nextPage = actionMap[action]
            if (nextPage != null) {
                if (pageEnableList[nextPage] ?: false) {
                    requestFocus(nextPage, KnobIndex.CURRENT_VIEW_INDEX, true)
                    return
                }
            }
        }
        currentFocusedView?.onKnobFocused(action)
    }

    private fun findNextFocusView(knobView: BaseKnobView<*>?, action: KnobAction): BaseKnobView<*>? {
        var currentKnobView = knobView
        while (currentKnobView != null) {
            val nextFocusHelper = currentKnobView.getData(KEY_NEXT_FOCUS_HELPER) ?: return null
            if (nextFocusHelper !is NextFocusHelper) {
                return null
            }
            val nextFocusView = nextFocusHelper.nextFocusList[action] ?: return null
            if (nextFocusView !is BaseKnobView<*>) {
                return null
            }
            if (nextFocusView.dispatchKnobFocusAction(action)) {
                return nextFocusView
            }
            currentKnobView = nextFocusView
        }
        return null
    }

    private fun findNextFocusViewByActionChain(knobView: BaseKnobView<*>?, viewList: List<BaseKnobView<*>>, action: KnobAction): BaseKnobView<*>? {
        if (knobView == null){
            return null
        }
        if (action == KnobAction.LEFT || action == KnobAction.UP) {
            val isHorizontalEnabled = action == KnobAction.LEFT && knobView.viewController.actionChainType.isHorizontalChainType()
            val isVerticalEnabled = action == KnobAction.UP && knobView.viewController.actionChainType.isVerticalChainType()
            val isEnabled = isHorizontalEnabled || isVerticalEnabled
            if (isEnabled.not()) {
                return null
            }
            var index = viewList.indexOf(knobView)
            index--
            while (index >= 0) {
                val view = viewList.getOrNull(index)
                index--
                if (view == null) {
                    continue
                }
                if (isHorizontalEnabled) {
                    if (view.viewController.actionChainType.isHorizontalChainType() && view.dispatchKnobFocusAction(action)){
                        return view
                    }
                } else {
                    if (view.viewController.actionChainType.isVerticalChainType() && view.dispatchKnobFocusAction(action)){
                        return view
                    }
                }
            }
        } else if (action == KnobAction.RIGHT || action == KnobAction.DOWN) {
            val isHorizontalEnabled = action == KnobAction.RIGHT && knobView.viewController.actionChainType.isHorizontalChainType()
            val isVerticalEnabled = action == KnobAction.DOWN && knobView.viewController.actionChainType.isVerticalChainType()
            val isEnabled = isHorizontalEnabled || isVerticalEnabled
            if (isEnabled.not()) {
                return null
            }
            var index = viewList.indexOf(knobView)
            index++
            while (index < viewList.size) {
                val view = viewList.getOrNull(index)
                index++
                if (view == null) {
                    continue
                }
                if (isHorizontalEnabled) {
                    if (view.viewController.actionChainType.isHorizontalChainType() && view.dispatchKnobFocusAction(action)){
                        return view
                    }
                } else {
                    if (view.viewController.actionChainType.isVerticalChainType() && view.dispatchKnobFocusAction(action)){
                        return view
                    }
                }
            }
        }
        return null
    }

    private fun findTargetView(viewInfo: KnobViewInfo, action: KnobAction): BaseKnobView<*>? {
        if (viewInfo.viewList.isEmpty()) {
            return null
        }
        var index = viewInfo.viewIndex
        var targetView: BaseKnobView<*>? = null
        val currentView = viewInfo.viewList[index]
        if (currentView.viewController.isAutoDispatchKnobFocusActionToNextView.not()) {
            if (action == KnobAction.KNOB_LEFT) {
                index--
            } else if (action == KnobAction.KNOB_RIGHT) {
                index++
            }
            val view = viewInfo.viewList[index]
            if (view.dispatchKnobFocusAction(action)) {
                targetView = view
            }
        } else {
            if (action == KnobAction.KNOB_LEFT) {
                index--
                while (index >= 0) {
                    val view = viewInfo.viewList[index]
                    if (view.dispatchKnobFocusAction(action)) {
                        targetView = view
                        break
                    }
                    index--
                }
            } else if (action == KnobAction.KNOB_RIGHT) {
                index++
                while (index < viewInfo.viewList.size) {
                    val view = viewInfo.viewList[index]
                    if (view.dispatchKnobFocusAction(action)) {
                        targetView = view
                        break
                    }
                    index++
                }
            }
        }
        if (targetView != null) {
            viewInfo.setPreFocusedView(targetView)
        }
        return targetView
    }

    private fun startAutoHideKnobTask(){
        if (isAutoHideKnob) {
            CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                autoHideKnobFlow.emit(Unit)
            }
        }
    }

    private fun onKnobEnter() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - preTime < debounceTime){
            return
        }
        preTime = currentTime
        knobFocusChangedObserverList.forEach { it.onKnobFocusChanged(KnobAction.ENTER) }
        getPageViewInfo(currentPage)?.getCurrentFocusedView()?.apply {
            putData(KEY_CLICK_BY_KNOB, true)
            onKnobEnter()
            putData(KEY_CLICK_BY_KNOB, null)
        }
    }

    private fun collectNextFocusView(rootView: View, knobView: BaseKnobView<*>) {
        val leftView = getNextFocusViewOrNull(rootView, knobView.view.nextFocusLeftId)
        val upView = getNextFocusViewOrNull(rootView, knobView.view.nextFocusUpId)
        val rightView = getNextFocusViewOrNull(rootView, knobView.view.nextFocusRightId)
        val downView = getNextFocusViewOrNull(rootView, knobView.view.nextFocusDownId)
        val knobLeftView = getNextFocusViewOrNull(rootView, knobView.getNextKnobViewId(KnobAction.KNOB_LEFT))
        val knobRightView = getNextFocusViewOrNull(rootView, knobView.getNextKnobViewId(KnobAction.KNOB_RIGHT))
        val knobAfterClickView = getNextFocusViewOrNull(rootView, knobView.nextFocusKnobAfterClickId)
        val map = listOfNotNull(
            leftView?.let { KnobAction.LEFT to it },
            upView?.let { KnobAction.UP to it },
            rightView?.let { KnobAction.RIGHT to it },
            downView?.let { KnobAction.DOWN to it },
            knobLeftView?.let { KnobAction.KNOB_LEFT to it },
            knobRightView?.let { KnobAction.KNOB_RIGHT to it },
        ).takeIf { it.isNotEmpty() }?.let { list ->
            HashMap<KnobAction, View>().apply {
                list.forEach {
                    put(it.first, it.second)
                }
            }
        }
        knobView.putData(KEY_NEXT_FOCUS_HELPER, map?.let { NextFocusHelper(it) })
        knobView.putData(KEY_AFTER_CLICK_NEXT_KNOB_FOCUS, knobAfterClickView)
    }

    private fun getNextFocusViewOrNull(rootView: View, id: Int?): View? {
        return id?.takeIf { it != View.NO_ID }?.let { rootView.findViewById(it) }
    }

    private fun getPageViewInfo(knobPage: String): KnobViewInfo? {
        return pageViewInfoList[knobPage]
    }

    private fun BaseKnobView<*>.tryToScrollIfNeed() {
        if (viewController.isAutoScrollWhenViewFocused.not()) {
            return
        }
        val parent = getData(KEY_SCROLL_HELPER)?.let { it as? ScrollHelper } ?: return
        when (parent.scrollView) {
            is ScrollView -> {
                val top = parent.scrollHelperNode.computeTotalTop() + view.top
                parent.scrollView.smoothScrollTo(0, top - parent.scrollView.paddingTop)
            }
            is NestedScrollView -> {
                val top = parent.scrollHelperNode.computeTotalTop() + view.top
                parent.scrollView.smoothScrollTo(0, top - parent.scrollView.paddingTop)
            }
            is HorizontalScrollView -> {
                // 这里只计算了left，没有考虑RTL，如果需要，请自己实现
                val left = parent.scrollHelperNode.computeTotalLeft() + view.left
                parent.scrollView.smoothScrollTo(left - parent.scrollView.paddingLeft, 0)
            }
        }
    }

    private fun View.isScrollView(): Boolean {
        return this is ScrollView || this is NestedScrollView || this is HorizontalScrollView
    }

    private fun BaseKnobView<*>.isClickByKnob(): Boolean {
        return getData(KEY_CLICK_BY_KNOB)?.let { it as? Boolean } ?: false
    }

    private fun List<View>.computeTotalTop(): Int {
        return computeViewTotalSize { it.top }
    }

    private fun List<View>.computeTotalLeft(): Int {
        return computeViewTotalSize { it.left }
    }

    private inline fun List<View>.computeViewTotalSize(action: (View) -> Int): Int {
        if (isEmpty()) {
            return 0
        }
        return sumOf(action)
    }

    private class ScrollHelper(val scrollView: View, val scrollHelperNode: ArrayList<View>)
    private class NextFocusHelper(val nextFocusList: HashMap<KnobAction, View>)

    private data class KnobViewInfo(var viewIndex: Int = 0, val viewList: MutableList<BaseKnobView<*>> = ArrayList(), var preFocusedView: WeakReference<BaseKnobView<*>> = WeakReference(null)) {
        fun setPreFocusedView(view: BaseKnobView<*>?) {
            preFocusedView.clear()
            if (view != null) {
                preFocusedView = WeakReference(view)
                val index = viewList.indexOf(view)
                viewIndex = if (index != -1) index else 0
            } else {
                viewIndex = 0
            }
        }

        fun getCurrentFocusedView(): BaseKnobView<*>? {
            return preFocusedView.get() ?: viewList.getOrNull(viewIndex)
        }

        fun recycle() {
            preFocusedView.clear()
            viewList.clear()
        }
    }

    private const val KEY_SCROLL_HELPER = 0
    private const val KEY_NEXT_FOCUS_HELPER = 1
    private const val KEY_ROOT_VIEW = 2
    private const val KEY_CLICK_BY_KNOB = 3
    private const val KEY_AFTER_CLICK_NEXT_KNOB_FOCUS = 4
    private const val KEY_CURRENT_PAGE = 5

    private val knobAccessibilityDelegate = object : AccessibilityDelegate() {
        override fun sendAccessibilityEvent(host: View, eventType: Int) {
            super.sendAccessibilityEvent(host, eventType)
            if (eventType != AccessibilityEvent.TYPE_VIEW_CLICKED) {
                return
            }
            val knobView = host.let { it as? BaseKnobView<*> } ?: return
            val page = knobView.getData(KEY_CURRENT_PAGE)?.let { it as? String } ?: return
            val isAutoRequestCurrentPage = knobView.viewController.isAutoRequestCurrentPage
            val isDiffPage = page != currentPage
            if (isDiffPage && isAutoRequestCurrentPage) {
                getPageViewInfo(currentPage)?.getCurrentFocusedView()?.onClearKnobFocus()
                currentPage = page
            }
            val viewInfo = getPageViewInfo(page) ?: return
            val nextFocusView = knobView.getData(KEY_AFTER_CLICK_NEXT_KNOB_FOCUS)?.let { it as? BaseKnobView<*> }
            val index = nextFocusView?.let { viewInfo.viewList.indexOf(it) } ?: -1
            val isClickByKnob = knobView.isClickByKnob()
            if (nextFocusView != null && index != -1) {
                val handled = nextFocusView.dispatchKnobFocusAction(KnobAction.ACTIVE)
                if (handled) {
                    val currentFocusedView = viewInfo.getCurrentFocusedView()
                    if (currentFocusedView != nextFocusView) {
                        currentFocusedView?.onClearKnobFocus()
                        viewInfo.setPreFocusedView(nextFocusView)
                    }
                    if (isClickByKnob) {
                        nextFocusView.onKnobFocused(KnobAction.ACTIVE)
                    }
                } else {
                    if (isDiffPage && isClickByKnob.not() && isAutoRequestCurrentPage) {
                        requestFocus(page, viewInfo.viewList.indexOf(knobView), false)
                    }
                }
            } else {
                if (isClickByKnob) {
                    return
                }
                viewInfo.getCurrentFocusedView()?.onClearKnobFocus()
                if (knobView.viewController.isAutoUpdateIndexWhenUserClick) {
                    viewInfo.setPreFocusedView(knobView)
                }
            }
        }
    }
}
