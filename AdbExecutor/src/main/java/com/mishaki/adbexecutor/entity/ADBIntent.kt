package com.mishaki.adbexecutor.entity

import java.net.URI

class ADBIntent {
    private val scriptList = ArrayList<String>()
    private val extraDataList = ArrayList<ExtraData>()
    private val flagsHashSet = HashSet<Flag>()

    var action: String? = null
    var dataUri: String? = null
    var mimeType: String? = null
    var component: String? = null

    fun addCategory(category: String) {
        scriptList.add("-c")
        scriptList.add(category)
    }

    fun addFlags(flag: String) {
        scriptList.add("-f")
        scriptList.add(flag)
    }

    fun addString(key: String, value: String) {
        addExtraData(ExtraType.String, key, value)
    }

    infix fun String.addExtraData(value: String) {
        addString(this, value)
    }

    fun addBoolean(key: String, value: Boolean) {
        addExtraData(ExtraType.Boolean, key, value.toString())
    }

    infix fun String.addExtraData(value: Boolean) {
        addBoolean(this, value)
    }

    fun addInt(key: String, value: Int) {
        addExtraData(ExtraType.Int, key, value.toString())
    }

    infix fun String.addExtraData(value: Int) {
        addInt(this, value)
    }

    fun addLong(key: String, value: Long) {
        addExtraData(ExtraType.Long, key, value.toString())
    }

    infix fun String.addExtraData(value: Long) {
        addLong(this, value)
    }

    fun addFloat(key: String, value: Float) {
        addExtraData(ExtraType.Float, key, value.toString())
    }

    infix fun String.addExtraData(value: Float) {
        addFloat(this, value)
    }

    fun addUri(key: String, value: URI) {
        addExtraData(ExtraType.Uri, key, value.toString())
    }

    infix fun String.addExtraData(value: URI) {
        addUri(this, value)
    }

    fun addExtraData(type: ExtraType, key: String, value: String) {
        val extraData = ExtraData(type, key, value)
        if (extraData.type == ExtraType.String || extraData.type == ExtraType.Uri) {
            extraData.value = extraData.value.toQuoteString()
        }
        if (extraDataList.contains(extraData).not()) {
            extraDataList.add(extraData)
        }
    }

    fun removeExtraData(type: ExtraType, key: String) {
        val extraData = ExtraData(type, key, "")
        extraDataList.remove(extraData)
    }

    fun addFlag(flag: Flag) {
        flagsHashSet.add(flag)
    }

    fun clearScript() {
        scriptList.clear()
    }

    fun clearFlags() {
        flagsHashSet.clear()
    }

    fun clearExtraData(){
        extraDataList.clear()
    }

    fun clear() {
        clearScript()
        clearFlags()
        clearExtraData()
        action = null
        dataUri = null
        mimeType = null
        component = null
    }

    fun getScript(): String {
        val list = listOfNotNull(
            action?.let { "-a" to it },
            dataUri?.let { "-d" to it },
            mimeType?.let { "-t" to it },
            component?.let { "-n" to it },
        ).flatMap { listOf(it.first, it.second.toQuoteString()) }.toMutableList()
        if (scriptList.isNotEmpty()) {
            list.addAll(scriptList)
        }
        if (extraDataList.isNotEmpty()) {
            list.addAll(extraDataList.flatMap { (type, key, value) -> listOf(type.value.string, key.toQuoteString(), value) })
        }
        if (flagsHashSet.isNotEmpty()) {
            list.addAll(flagsHashSet.map { it.value })
        }
        return list.joinToString(" ")
    }

    private fun String.toQuoteString() = "\"$this\""

    data class ExtraData(val type: ExtraType, val key: String, var value: String) {
        override fun hashCode(): Int {
            return type.hashCode() * 31 + key.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            val otherValue = other?.let { it as? ExtraData } ?: return false
            return type == otherValue.type && key == otherValue.key
        }
    }

    enum class ExtraType(val value: StringWrapper) {
        String(StringWrapper("--es")),
        Boolean(StringWrapper("--ez")),
        Int(StringWrapper("--ei")),
        Long(StringWrapper("--el")),
        Float(StringWrapper("--ef")),
        Uri(StringWrapper("--eu"))
    }

    @JvmInline
    value class StringWrapper(val string: String)

    @JvmInline
    value class Flag(val value: String) {
        companion object {
            val GRANT_READ_URI_PERMISSION = Flag("--grant-read-uri-permission")
            val GRANT_WRITE_URI_PERMISSION = Flag("--grant-write-uri-permission")
            val DEBUG_LOG_RESOLUTION = Flag("--debug-log-resolution")
            val EXCLUDE_STOPPED_PACKAGES = Flag("--exclude-stopped-packages")
            val INCLUDE_STOPPED_PACKAGES = Flag("--include-stopped-packages")
            val ACTIVITY_BROUGHT_TO_FRONT = Flag("--activity-brought-to-front")
            val ACTIVITY_CLEAR_TOP = Flag("--activity-clear-top")
            val ACTIVITY_CLEAR_WHEN_TASK_RESET = Flag("--activity-clear-when-task-reset")
            val ACTIVITY_EXCLUDE_FROM_RECENTS = Flag("--activity-exclude-from-recents")
            val ACTIVITY_LAUNCHED_FROM_HISTORY = Flag("--activity-launched-from-history")
            val ACTIVITY_MULTIPLE_TASK = Flag("--activity-multiple-task")
            val ACTIVITY_NO_ANIMATION = Flag("--activity-no-animation")
            val ACTIVITY_NO_HISTORY = Flag("--activity-no-history")
            val ACTIVITY_NO_USER_ACTION = Flag("--activity-no-user-action")
            val ACTIVITY_PREVIOUS_IS_TOP = Flag("--activity-previous-is-top")
            val ACTIVITY_REORDER_TO_FRONT = Flag("--activity-reorder-to-front")
            val ACTIVITY_RESET_TASK_IF_NEEDED = Flag("--activity-reset-task-if-needed")
            val ACTIVITY_SINGLE_TOP = Flag("--activity-single-top")
            val ACTIVITY_CLEAR_TASK = Flag("--activity-clear-task")
            val ACTIVITY_TASK_ON_HOME = Flag("--activity-task-on-home")
            val RECEIVER_REGISTERED_ONLY = Flag("--receiver-registered-only")
            val RECEIVER_REPLACE_PENDING = Flag("--receiver-replace-pending")
        }
    }
}

fun buildADBIntent(action: ADBIntent.() -> Unit): ADBIntent {
    return ADBIntent().apply(action)
}
