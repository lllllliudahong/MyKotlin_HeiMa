package android.com.mykotlin_heima.view

import android.com.mykotlin_heima.model.HomeItemBean

/**
 * HomeFragment和presenter交互
 */
interface HomeFragmentView {
    fun loadMoreSuccess(listHomeItemBean: ArrayList<HomeItemBean>)
    fun onError(message: String?)
    fun loadSuccess(listHomeItemBean: ArrayList<HomeItemBean>)
}