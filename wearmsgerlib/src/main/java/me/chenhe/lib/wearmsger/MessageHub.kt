package me.chenhe.lib.wearmsger

import android.content.Context
import android.net.Uri
import me.chenhe.lib.wearmsger.bean.Result
import me.chenhe.lib.wearmsger.bean.Result.Companion.failed
import me.chenhe.lib.wearmsger.listener.MessageListener
import java.nio.charset.Charset

/**
 * 用于发送数据。
 */
object MessageHub {

    suspend fun sendMessage(
        context: Context,
        nodeId: String,
        path: String,
        data: ByteArray,
        timeout: Long = SEND_TIMEOUT
    ): Result {
        return client.sendMessage(context, nodeId, path, data, timeout)
    }

    suspend fun sendMessage(
        context: Context,
        nodeId: String,
        path: String,
        data: String,
        timeout: Long = SEND_TIMEOUT
    ): Result {
        return client.sendMessage(context, nodeId, path, data, timeout)
    }

    /**
     * 向所有节点发送 Message. 遇到发送失败会继续执行。
     *
     * @return 若全部发送成功则返回第一个发送成功的相关数据。否则返回第一个失败的相关数据。
     */
    suspend fun sendMessage(
        context: Context,
        path: String,
        data: ByteArray,
        timeout: Long = SEND_TIMEOUT
    ): Result {
        var failResult: Result? = null
        var requestId = 0L
        try {
            client.getNodesId(context)?.forEach { nodeId ->
                sendMessage(context, nodeId, path, data, timeout).let { r ->
                    if (r.result != Result.RESULT_OK) {
                        failResult = r
                    } else if (requestId == 0L) {
                        requestId = r.requestId
                    }
                }
            }
        } catch (e: Exception) {
            /**
             * Common exception:
             * 1) The connection to Google Play services was lost due to service disconnection.
             * 2) Wearable.API is not available on this device.
             * For latter, install wear app and manually trigger once `sendMessage` on wear device.
             */
            return failed(e)
        }
        return failResult ?: Result(Result.RESULT_OK, requestId)
    }


    /**
     * 向所有节点发送 Message. 遇到发送失败会继续执行。
     *
     * @param data 字符串数据，采用 utf8 编码。
     * @return 若全部发送成功则返回第一个发送成功的相关数据。否则返回第一个失败的相关数据。
     */
    suspend fun sendMessage(
        context: Context,
        path: String,
        data: String,
        timeout: Long = SEND_TIMEOUT
    ): Result {
        return sendMessage(context, path, data.toByteArray(Charset.forName("utf8")), timeout)
    }

    /**
     * 添加 Message 监听器。
     * 必须在适当的时候调用 [removeMessageListener]，否则会引发内存泄露。
     *
     * 若传入 [uri] 则格式应为 `wear://<nodeId>/<path>`，用 `*` 匹配任意节点。
     *
     * **注意：** [uri] 在 GMS 模式下为原生实现，可以提高性能。但是在 MMS 下为手动实现，仅能保证行为一致性。通常情况
     * 下此差别不会对使用造成影响。
     *
     * @param uri 过滤器，若不为 `null` 则只响应匹配的 event.
     * @param literal [uri] 不为 `null` 时有效。是否完全匹配 `path`，传入 `false` 则只匹配前缀。
     */
    fun addMessageListener(
        context: Context,
        listener: MessageListener,
        uri: Uri? = null,
        literal: Boolean = false
    ) {
        client.addMessageListener(context, listener, uri, literal)
    }

    @Suppress("unused")
    fun removeMessageListener(context: Context, listener: MessageListener) {
        client.removeMessageListener(context, listener)
    }

}