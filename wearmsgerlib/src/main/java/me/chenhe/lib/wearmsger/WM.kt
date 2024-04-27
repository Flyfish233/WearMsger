package me.chenhe.lib.wearmsger

import android.util.Log
import me.chenhe.lib.wearmsger.compatibility.ClientCompat
import me.chenhe.lib.wearmsger.compatibility.GmsImpl

/**
 * 发送请求超时时长，指的是提交到 GMS 而不是到达目标设备。
 */
internal const val SEND_TIMEOUT: Long = 3000L

/**
 * 双向通讯等待超时时间（毫秒），包含了发送等待时间。
 */
internal const val bothWayTimeout = 3000L

/**
 * 传输 Data 时附加 ID 所在的字段。
 */
internal const val DATA_ID_KEY = "WearMsger_ID"

/**
 * 响应 path 前缀。
 * 完整格式为 /WMRE/{reqId}{path}
 */
internal const val WMRE = "/WMRE"

internal fun getClient(): ClientCompat {
    return GmsImpl
}

internal fun logd(tag: String, a: Any) {
    Log.d("WearMsger", "[$tag] $a")
}

internal fun logw(tag: String, a: Any, e: Exception? = null) {
    Log.w("WearMsger", "[$tag] $a", e)
}

internal fun loge(tag: String, a: Any, e: Exception? = null) {
    Log.e("WearMsger", "[$tag] $a", e)
}
