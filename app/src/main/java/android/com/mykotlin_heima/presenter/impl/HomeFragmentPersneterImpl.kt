package android.com.mykotlin_heima.presenter.impl

import android.com.mykotlin_heima.model.HomeItemBean
import android.com.mykotlin_heima.presenter.interf.HomeFragmentPresenterF
import android.com.mykotlin_heima.util.ThreadUtil
import android.com.mykotlin_heima.view.HomeFragmentView
import okhttp3.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.IOException

class HomeFragmentPersneterImpl(var homeFragmentView: HomeFragmentView) : HomeFragmentPresenterF,AnkoLogger {
    var listHomeItemBean : ArrayList<HomeItemBean> = ArrayList()
    /**
     * 初始化数据和刷新
     */
    override fun loadDatas() {
        //        val path =
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/CaoJieNan/Sample/master/user.txt")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//                myToast("获取数据失败")
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
//                        refreshLayout.isRefreshing = false
                        homeFragmentView.onError(e.message)
                    }
                })

            }

            override fun onResponse(call: Call, response: Response) {
//                myToast("获取数据成功")
//                val result = response.body()?.string()
//                val gson = Gson()
//                gson.fromJson<List<HomeItemBean>>(result,object : TypeToken<List<HomeItemBean>>(){}.type)
                listHomeItemBean.clear()
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com1",24,1,"jason1","http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com2",24,2,"jason2","http://e.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com3",24,3,"jason3","http://e.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com4",24,4,"jason4","http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com5",24,5,"jason5","http://e.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg"))

                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
//                        adapter.updateList(listHomeItemBean)
//                        info { "size = ${listHomeItemBean.size}" }
//                        refreshLayout.isRefreshing = false//隐藏刷新控件
                        homeFragmentView.loadSuccess(listHomeItemBean)
                    }
                })
            }

        })
    }

    override fun loadMoreDatas(offset: Int) {
        info { "offset=$offset" }
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/CaoJieNan/Sample/master/user.txt")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
//                isSetIntent = true//获取数据成功才能继续加载
//                myToast("加载更多获取数据失败")
                homeFragmentView.onError(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                listHomeItemBean.clear()
//                myToast("加载更多获取数据成功")
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com1",24,1,"jason1","http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com2",24,2,"jason2","http://e.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com3",24,3,"jason3","http://e.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com4",24,4,"jason4","http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com5",24,5,"jason5","http://e.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg"))

                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
//                        adapter.updateMoreList(listHomeItemBean)
//                        isSetIntent = true//获取数据成功才能继续加载
                        homeFragmentView.loadMoreSuccess(listHomeItemBean)
                    }
                })

            }

        })
    }

}