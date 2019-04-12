package android.com.mykotlin_heima.net

/**
 * 请求的回调
 */
interface ResponseHandler<RESPONSE> {
    fun onError(msg : String?,type : Int)
    fun onSuccess(result : RESPONSE,type : Int)
}