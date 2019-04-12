package android.com.mykotlin_heima.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * 所有请求的基类
 */
open class MRequest<RESPONSE>(val url:String,val handler: ResponseHandler<RESPONSE>) {

    /**
     * 解析网络请求的结果
     */
    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val list = gson.fromJson<RESPONSE>(result, type)
        return list
    }

    fun execute(){
        NetManager.manager.sendRequest(this)
    }
}