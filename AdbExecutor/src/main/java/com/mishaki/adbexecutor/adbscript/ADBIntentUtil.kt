package com.mishaki.adbexecutor.adbscript

import com.mishaki.adbexecutor.entity.ADBIntent
import com.mishaki.adbexecutor.script.IADBIntent
import com.mishaki.adbexecutor.script.ScriptIO
import java.net.URI

infix fun <T> BuildADBIntentTemp<T>._a(action: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.action = action
    return this
}

infix fun <T> BuildADBIntentTemp<T>._d(dataUri: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.dataUri = dataUri
    return this
}


infix fun <T> BuildADBIntentTemp<T>._t(mimeType: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.mimeType = mimeType
    return this
}

infix fun <T> BuildADBIntentTemp<T>._n(component: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.component = component
    return this
}

infix fun <T> BuildADBIntentTemp<T>._c(category: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.addCategory(category)
    return this
}

infix fun <T> BuildADBIntentTemp<T>._f(flag: String): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.addFlags(flag)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__es(pair: Pair<String, String>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addString(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__ez(pair: Pair<String, Boolean>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addBoolean(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__ei(pair: Pair<String, Int>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addInt(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__el(pair: Pair<String, Long>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addLong(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__ef(pair: Pair<String, Float>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addFloat(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__eu(pair: Pair<String, URI>): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    val (key, value) = pair
    intent.addUri(key, value)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__grant_read_uri_permission(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_read_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__grant_read_uri_permission(action: finish) where T : ScriptIO, T : IADBIntent {
    __grant_read_uri_permissionInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__grant_read_uri_permissionInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.GRANT_READ_URI_PERMISSION, action)
}

infix fun <T> BuildADBIntentTemp<T>.__grant_write_uri_permission(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__grant_write_uri_permission(action: finish) where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__grant_write_uri_permissionInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.GRANT_WRITE_URI_PERMISSION, action)
}

infix fun <T> BuildADBIntentTemp<T>.__debug_log_resolution(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__debug_log_resolution(action: finish) where T : ScriptIO, T : IADBIntent {
    __debug_log_resolutionInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__debug_log_resolutionInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.DEBUG_LOG_RESOLUTION, action)
}

infix fun <T> BuildADBIntentTemp<T>.__exclude_stopped_packages(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__exclude_stopped_packages(action: finish) where T : ScriptIO, T : IADBIntent {
    __exclude_stopped_packagesInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__exclude_stopped_packagesInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.EXCLUDE_STOPPED_PACKAGES, action)
}

infix fun <T> BuildADBIntentTemp<T>.__include_stopped_packages(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__include_stopped_packages(action: finish) where T : ScriptIO, T : IADBIntent {
    __include_stopped_packagesInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__include_stopped_packagesInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.INCLUDE_STOPPED_PACKAGES, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_brought_to_front(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_brought_to_front(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_brought_to_frontInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_brought_to_frontInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_BROUGHT_TO_FRONT, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_top(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_top(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_clear_topInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_clear_topInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_CLEAR_TOP, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_when_task_reset(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_when_task_reset(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_clear_when_task_resetInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_clear_when_task_resetInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_CLEAR_WHEN_TASK_RESET, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_exclude_from_recents(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_exclude_from_recents(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_exclude_from_recentsInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_exclude_from_recentsInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_EXCLUDE_FROM_RECENTS, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_launched_from_history(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_launched_from_history(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_launched_from_historyInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_launched_from_historyInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_LAUNCHED_FROM_HISTORY, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_multiple_task(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_multiple_task(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_multiple_taskInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_multiple_taskInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_MULTIPLE_TASK, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_animation(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_animation(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_no_animationInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_no_animationInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_NO_ANIMATION, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_history(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_history(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_no_historyInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_no_historyInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_NO_HISTORY, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_user_action(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_no_user_action(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_no_user_actionInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_no_user_actionInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_NO_USER_ACTION, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_previous_is_top(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_previous_is_top(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_previous_is_topInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_previous_is_topInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_PREVIOUS_IS_TOP, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_reorder_to_front(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_reorder_to_front(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_reorder_to_frontInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_reorder_to_frontInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_REORDER_TO_FRONT, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_reset_task_if_needed(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_reset_task_if_needed(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_reset_task_if_neededInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_reset_task_if_neededInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_RESET_TASK_IF_NEEDED, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_single_top(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_single_top(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_single_topInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_single_topInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_SINGLE_TOP, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_task(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_clear_task(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_clear_taskInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_clear_taskInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_CLEAR_TASK, action)
}

infix fun <T> BuildADBIntentTemp<T>.__activity_task_on_home(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__activity_task_on_home(action: finish) where T : ScriptIO, T : IADBIntent {
    __activity_task_on_homeInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__activity_task_on_homeInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.ACTIVITY_TASK_ON_HOME, action)
}

infix fun <T> BuildADBIntentTemp<T>.__receiver_registered_only(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__receiver_registered_only(action: finish) where T : ScriptIO, T : IADBIntent {
    __receiver_registered_onlyInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__receiver_registered_onlyInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.RECEIVER_REGISTERED_ONLY, action)
}

infix fun <T> BuildADBIntentTemp<T>.__receiver_replace_pending(action: process): BuildADBIntentTemp<T> where T : ScriptIO, T : IADBIntent {
    __grant_write_uri_permissionInner(action)
    return this
}

infix fun <T> BuildADBIntentTemp<T>.__receiver_replace_pending(action: finish) where T : ScriptIO, T : IADBIntent {
    __receiver_replace_pendingInner(action)
}

private fun <T> BuildADBIntentTemp<T>.__receiver_replace_pendingInner(action: Any) where T : ScriptIO, T : IADBIntent {
    flagProcessOrFinish(ADBIntent.Flag.RECEIVER_REPLACE_PENDING, action)
}

private fun <T> BuildADBIntentTemp<T>.flagProcessOrFinish(flag: ADBIntent.Flag, action: Any) where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    intent.addFlag(flag)
    if (action == finish) {
        build(finish)
    }
}

infix fun <T> BuildADBIntentTemp<T>.build(ignore: finish) where T : ScriptIO, T : IADBIntent {
    val intent = caller.getIntent()
    caller.scriptInput?.apply {
        add(intent.getScript())
    }
    caller.output()
}

private fun <T> T.getIntent(): ADBIntent where T : ScriptIO, T : IADBIntent {
    return ADBIntentMapping.get(this) ?: ADBIntent().also {
        ADBIntentMapping.put(this, it)
    }
}

@JvmInline
value class BuildADBIntentTemp<T>(val caller: T) where T : ScriptIO, T : IADBIntent

object buildStart
object process
object finish

private object ADBIntentMapping {
    private val intentMapping = HashMap<Any, ADBIntent>()

    fun get(key: Any): ADBIntent? = intentMapping[key]

    fun put(key: Any, adbIntent: ADBIntent) {
        intentMapping[key] = adbIntent
    }

    fun remove(key: Any) {
        intentMapping.remove(key)
    }
}
