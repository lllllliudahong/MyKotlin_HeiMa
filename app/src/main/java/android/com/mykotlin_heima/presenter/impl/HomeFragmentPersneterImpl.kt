package android.com.mykotlin_heima.presenter.impl

import android.com.mykotlin_heima.model.HomeItemBean
import android.com.mykotlin_heima.net.HomeRequest
import android.com.mykotlin_heima.net.NetManager
import android.com.mykotlin_heima.net.ResponseHandler
import android.com.mykotlin_heima.presenter.interf.HomeFragmentPresenterF
import android.com.mykotlin_heima.util.ThreadUtil
import android.com.mykotlin_heima.view.HomeFragmentView
import okhttp3.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.IOException

class HomeFragmentPersneterImpl(var homeFragmentView: HomeFragmentView) : HomeFragmentPresenterF,AnkoLogger,
    ResponseHandler<HomeItemBean> {


//    var listHomeItemBean : ArrayList<HomeItemBean> = ArrayList()
    /**
     * 初始化数据和刷新
     */
    override fun loadDatas() {
        //定义一个request
        HomeRequest(this,HomeFragmentPresenterF.TYPE_INIT_OR_REFRESH).execute()
    }

    override fun loadMoreDatas(offset: Int) {
        info { "offset=$offset" }
        //定义一个request
        HomeRequest(this,HomeFragmentPresenterF.TYPE_LOAD_MORE).execute()

    }

    override fun onError(msg: String?, type: Int) {
        homeFragmentView.onError(msg)
    }

    override fun onSuccess(result: HomeItemBean, type: Int) {
        var listHomeItemBean : ArrayList<HomeItemBean> = ArrayList()
        listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com1",24,1,"jason1","http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"))
        listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com2",24,2,"jason2","http://e.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"))
        listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com3",24,3,"jason3","http://e.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"))
        listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com4",24,4,"jason4","http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"))
        listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com5",24,5,"jason5","http://e.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg"))
        if (type == HomeFragmentPresenterF.TYPE_INIT_OR_REFRESH ) homeFragmentView.loadSuccess(listHomeItemBean)
        else homeFragmentView.loadMoreSuccess(listHomeItemBean)
    }


}