package com.mishaki.knobutil.util

import com.mishaki.knobutil.KnobAction
import com.mishaki.knobutil.KnobIndex
import com.mishaki.knobutil.KnobUtil
import com.mishaki.knobutil.view.BaseKnobView

object KnobPageUtil {
    const val FIRST_PAGE = "FIRST"
    const val SECOND_PAGE = "SECOND"
    const val THIRD_PAGE = "THIRD"
    const val POP_PAGE = "POP"

    @JvmStatic
    fun addNextPageRule(currentPage: String, action: KnobAction, nextPage: String) {
        KnobUtil.addNextPageRule(currentPage, action, nextPage)
    }

    @JvmStatic
    fun removeNextPageRule(currentPage: String) {
        KnobUtil.removeNextPageRule(currentPage)
    }

    @JvmStatic
    fun removeNextPageRule(currentPage: String, action: KnobAction) {
        KnobUtil.removeNextPageRule(currentPage, action)
    }

    @JvmStatic
    fun clearNextPageRule() {
        KnobUtil.clearNextPageRule()
    }

    @JvmStatic
    fun updateFirstEnabled(isEnabled: Boolean) {
        KnobUtil.updateEnable(FIRST_PAGE, isEnabled)
    }

    @JvmStatic
    fun updateSecondEnabled(isEnabled: Boolean) {
        KnobUtil.updateEnable(SECOND_PAGE, isEnabled)
    }

    @JvmStatic
    fun updateThirdEnabled(isEnabled: Boolean) {
        KnobUtil.updateEnable(THIRD_PAGE, isEnabled)
    }

    @JvmStatic
    fun updatePopEnabled(isEnabled: Boolean) {
        KnobUtil.updateEnable(POP_PAGE, isEnabled)
    }

    //========================================updateViewList========================================

    @JvmStatic
    fun updateFirstViewList(viewList: List<BaseKnobView<*>>) {
        updateFirstEnabled(true)
        KnobUtil.updateViewList(FIRST_PAGE, viewList)
    }

    @JvmStatic
    fun updateSecondViewList(viewList: List<BaseKnobView<*>>) {
        updateSecondEnabled(true)
        KnobUtil.updateViewList(SECOND_PAGE, viewList)
    }

    @JvmStatic
    fun updateThirdViewList(viewList: List<BaseKnobView<*>>) {
        updateThirdEnabled(true)
        KnobUtil.updateViewList(THIRD_PAGE, viewList)
    }

    @JvmStatic
    fun updatePopViewList(viewList: List<BaseKnobView<*>>) {
        updatePopEnabled(true)
        KnobUtil.updateViewList(POP_PAGE, viewList)
    }

    //================================updateViewListAndRequestFocus================================

    @JvmStatic
    @JvmOverloads
    fun updateFirstViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateFirstViewListAndRequestFocus(viewList, 0, isShowFocus)
    }

    @JvmStatic
    fun updateFirstViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>) {
        updateFirstViewListAndRequestFocus(viewList, view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateFirstViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateFirstViewListAndRequestFocus(viewList, viewList.indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateFirstViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int) {
        updateFirstViewListAndRequestFocus(viewList, index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateFirstViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int, isShowFocus: Boolean) {
        updateFirstEnabled(true)
        KnobUtil.updateViewListAndRequestFocus(FIRST_PAGE, viewList, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updateSecondViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateSecondViewListAndRequestFocus(viewList, 0, isShowFocus)
    }

    @JvmStatic
    fun updateSecondViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>) {
        updateSecondViewListAndRequestFocus(viewList, view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateSecondViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateSecondViewListAndRequestFocus(viewList, viewList.indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateSecondViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int) {
        updateSecondViewListAndRequestFocus(viewList, index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateSecondViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int, isShowFocus: Boolean) {
        updateSecondEnabled(true)
        KnobUtil.updateViewListAndRequestFocus(SECOND_PAGE, viewList, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updateThirdViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateThirdViewListAndRequestFocus(viewList, 0, isShowFocus)
    }

    @JvmStatic
    fun updateThirdViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>) {
        updateThirdViewListAndRequestFocus(viewList, view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateThirdViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateThirdViewListAndRequestFocus(viewList, viewList.indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateThirdViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int) {
        updateThirdViewListAndRequestFocus(viewList, index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateThirdViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int, isShowFocus: Boolean) {
        updateThirdEnabled(true)
        KnobUtil.updateViewListAndRequestFocus(THIRD_PAGE, viewList, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updatePopViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updatePopViewListAndRequestFocus(viewList, 0, isShowFocus)
    }

    @JvmStatic
    fun updatePopViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>) {
        updatePopViewListAndRequestFocus(viewList, view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updatePopViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, view: BaseKnobView<*>, isShowFocus: Boolean) {
        updatePopViewListAndRequestFocus(viewList, viewList.indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updatePopViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int) {
        updatePopViewListAndRequestFocus(viewList, index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updatePopViewListAndRequestFocus(viewList: List<BaseKnobView<*>>, index: Int, isShowFocus: Boolean) {
        updatePopEnabled(true)
        KnobUtil.updateViewListAndRequestFocus(POP_PAGE, viewList, index, isShowFocus)
    }

    //========================================updateViewIndex========================================

    @JvmStatic
    @JvmOverloads
    fun updateFirstViewIndex(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateFirstViewIndex(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun updateFirstViewIndex(view: BaseKnobView<*>) {
        updateFirstViewIndex(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateFirstViewIndex(view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateFirstViewIndex(KnobUtil.getViewList(FIRST_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateFirstViewIndex(index: Int) {
        updateFirstViewIndex(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateFirstViewIndex(index: Int, isShowFocus: Boolean) {
        KnobUtil.updateViewIndex(FIRST_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updateSecondViewIndex(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateSecondViewIndex(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun updateSecondViewIndex(view: BaseKnobView<*>) {
        updateSecondViewIndex(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateSecondViewIndex(view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateSecondViewIndex(KnobUtil.getViewList(SECOND_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateSecondViewIndex(index: Int) {
        updateSecondViewIndex(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateSecondViewIndex(index: Int, isShowFocus: Boolean) {
        KnobUtil.updateViewIndex(SECOND_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updateThirdViewIndex(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updateThirdViewIndex(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun updateThirdViewIndex(view: BaseKnobView<*>) {
        updateThirdViewIndex(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateThirdViewIndex(view: BaseKnobView<*>, isShowFocus: Boolean) {
        updateThirdViewIndex(KnobUtil.getViewList(THIRD_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updateThirdViewIndex(index: Int) {
        updateThirdViewIndex(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updateThirdViewIndex(index: Int, isShowFocus: Boolean) {
        KnobUtil.updateViewIndex(THIRD_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun updatePopViewIndex(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        updatePopViewIndex(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun updatePopViewIndex(view: BaseKnobView<*>) {
        updatePopViewIndex(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updatePopViewIndex(view: BaseKnobView<*>, isShowFocus: Boolean) {
        updatePopViewIndex(KnobUtil.getViewList(POP_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun updatePopViewIndex(index: Int) {
        updatePopViewIndex(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun updatePopViewIndex(index: Int, isShowFocus: Boolean) {
        KnobUtil.updateViewIndex(POP_PAGE, index, isShowFocus)
    }

    //========================================requestViewFocus========================================

    @JvmStatic
    @JvmOverloads
    fun requestFirstFocus(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        requestFirstFocus(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun requestFirstFocus(view: BaseKnobView<*>) {
        requestFirstFocus(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestFirstFocus(view: BaseKnobView<*>, isShowFocus: Boolean) {
        requestFirstFocus(KnobUtil.getViewList(FIRST_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun requestFirstFocus(index: Int) {
        requestFirstFocus(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestFirstFocus(index: Int, isShowFocus: Boolean) {
        KnobUtil.requestFocus(FIRST_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun requestSecondFocus(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        requestSecondFocus(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun requestSecondFocus(view: BaseKnobView<*>) {
        requestSecondFocus(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestSecondFocus(view: BaseKnobView<*>, isShowFocus: Boolean) {
        requestSecondFocus(KnobUtil.getViewList(SECOND_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun requestSecondFocus(index: Int) {
        requestSecondFocus(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestSecondFocus(index: Int, isShowFocus: Boolean) {
        KnobUtil.requestFocus(SECOND_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun requestThirdFocus(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        requestThirdFocus(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun requestThirdFocus(view: BaseKnobView<*>) {
        requestThirdFocus(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestThirdFocus(view: BaseKnobView<*>, isShowFocus: Boolean) {
        requestThirdFocus(KnobUtil.getViewList(THIRD_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun requestThirdFocus(index: Int) {
        requestThirdFocus(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestThirdFocus(index: Int, isShowFocus: Boolean) {
        KnobUtil.requestFocus(THIRD_PAGE, index, isShowFocus)
    }

    @JvmStatic
    @JvmOverloads
    fun requestPopFocus(isShowFocus: Boolean = KnobUtil.hasAnyKnobFocus()) {
        requestPopFocus(KnobIndex.CURRENT_VIEW_INDEX, isShowFocus)
    }

    @JvmStatic
    fun requestPopFocus(view: BaseKnobView<*>) {
        requestPopFocus(view, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestPopFocus(view: BaseKnobView<*>, isShowFocus: Boolean) {
        requestPopFocus(KnobUtil.getViewList(POP_PAGE).indexOf(view), isShowFocus)
    }

    @JvmStatic
    fun requestPopFocus(index: Int) {
        requestPopFocus(index, KnobUtil.hasAnyKnobFocus())
    }

    @JvmStatic
    fun requestPopFocus(index: Int, isShowFocus: Boolean) {
        KnobUtil.requestFocus(POP_PAGE, index, isShowFocus)
    }
}
