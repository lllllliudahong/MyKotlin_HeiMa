package android.com.mykotlin_heima.net

import android.com.mykotlin_heima.model.HomeItemBean

class HomeRequest(handler: ResponseHandler<HomeItemBean>)
    : MRequest<HomeItemBean>("https://raw.githubusercontent.com/CaoJieNan/Sample/master/user.txt",handler) {
}