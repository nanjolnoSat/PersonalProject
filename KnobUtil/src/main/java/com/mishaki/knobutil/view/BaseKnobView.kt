package com.mishaki.knobutil.view

import android.view.View
import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.KnobUtil
import com.mishaki.knobutil.entity.KnobViewController
import com.mishaki.knobutil.entity.KnobViewDataHolder
import com.mishaki.knobutil.util.Cleanable

interface BaseKnobView<T : KnobViewDataHolder> : Cleanable {
    val dataHolder: T
    val viewController: KnobViewController
        get() = dataHolder.knobViewController

    fun isInterceptNextKnobAction(action: KnobAction): Boolean

    fun dispatchKnobFocusAction(action: KnobAction): Boolean {
        return true
    }

    fun onKnobFocused(action: KnobAction) {
        dataHolder.hasKnobFocus = true
        onKnobFocusedInner(action)
    }

    fun onClearKnobFocus() {
        dataHolder.hasKnobFocus = false
        onClearKnobFocusInner()
    }

    fun hasKnobFocus(): Boolean {
        return dataHolder.hasKnobFocus
    }

    fun onKnobFocusedInner(action: KnobAction)
    fun onClearKnobFocusInner()
    fun onKnobEnter()

    fun onKnobBackPressed(): Boolean {
        return false
    }

    val view: View

    fun getNextKnobViewId(action: KnobAction): Int {
        return when (action) {
            KnobAction.LEFT -> view.nextFocusLeftId
            KnobAction.UP -> view.nextFocusUpId
            KnobAction.RIGHT -> view.nextFocusRightId
            KnobAction.DOWN -> view.nextFocusDownId
            else -> {
                viewController.nextKnobViewIdList[action] ?: View.NO_ID
            }
        }
    }

    fun setNextKnobViewId(action: KnobAction, id: Int) {
        when (action) {
            KnobAction.LEFT -> view.nextFocusLeftId = id
            KnobAction.UP -> view.nextFocusUpId = id
            KnobAction.RIGHT -> view.nextFocusRightId = id
            KnobAction.DOWN -> view.nextFocusDownId = id
            else -> if (id == View.NO_ID) {
                viewController.nextKnobViewIdList.remove(action)
            } else {
                viewController.nextKnobViewIdList[action] = id
            }
        }
        KnobUtil.reCollectViewNextFocusView(this)
    }

    var nextFocusKnobAfterClickId: Int
        get() = viewController.nextKnobAfterClickId
        set(id) {
            viewController.nextKnobAfterClickId = id
            KnobUtil.reCollectViewNextFocusView(this)
        }

    fun putData(key: Int, tag: Any?) {
        dataHolder.keyedTags.put(key, tag)
    }

    fun getData(key: Int): Any? {
        return dataHolder.keyedTags[key]
    }

    override fun clear() {
        dataHolder.clear()
    }

    //========================================dataBinding use========================================
    fun setNextFocusKnobLeftId(id: Int) {
        setNextKnobViewId(KnobAction.KNOB_LEFT, id)
    }

    fun setNextFocusKnobRightId(id: Int) {
        setNextKnobViewId(KnobAction.KNOB_RIGHT, id)
    }
}
