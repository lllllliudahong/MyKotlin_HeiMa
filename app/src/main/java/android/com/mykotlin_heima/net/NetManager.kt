package android.com.mykotlin_heima.net

import android.com.mykotlin_heima.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * 发送网络请求的类
 */
class NetManager private constructor(){

    val client by lazy { OkHttpClient() }

    companion object {
        val manager by lazy { NetManager() }
    }


    /**
     * 发送网络请求
     */
    fun<RESPONSE> sendRequest(req:MRequest<RESPONSE>){
        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        req.handler.onError(e.message,req.type)
                    }
                })

            }
            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        req.handler.onSuccess(parseResult,req.type)
                    }
                })
            }

        })
    }
}