package android.com.mykotlin_heima.ui.fragment

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.adapter.HomeAdapter
import android.com.mykotlin_heima.base.BaseFragment
import android.com.mykotlin_heima.model.HomeItemBean
import android.com.mykotlin_heima.util.ThreadUtil
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.find
import java.io.IOException

class HomeFragment : BaseFragment() {

    val adapter by lazy { HomeAdapter() }
//    val recyclerView : RecyclerView by lazy { find(R.id.recyclerView) }
    lateinit var recyclerView : RecyclerView
    lateinit var refreshLayout : SwipeRefreshLayout
    var listHomeItemBean : ArrayList<HomeItemBean> = ArrayList()
    var isSetIntent = true
    override fun initView(): View? {
        info("HomeFragment：initView")
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
//        val adapter = HomeAdapter()
        recyclerView.adapter = adapter


        //下拉刷新的监听
        refreshLayout.setOnRefreshListener {
            loadDatas()
        }
        //上拉加载的监听
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){//是否停止滑动
                    //是否已显示最后一条
                    val laoutManager = recyclerView.layoutManager
                    if (laoutManager is LinearLayoutManager){
                        val manager : LinearLayoutManager = laoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if ((lastPosition == adapter.itemCount-1) && isSetIntent){
                            isSetIntent = false
                            loadMoreDatas(adapter.itemCount-1)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            }
        })

    }


    override fun initData() {
        recyclerView = find(R.id.recyclerView)
        refreshLayout = find(R.id.refreshLayout)
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.GRAY)
        loadDatas()
    }

    private fun loadDatas() {
//        val path =
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/CaoJieNan/Sample/master/user.txt")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                myToast("获取数据失败")
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }
                })

            }

            override fun onResponse(call: Call, response: Response) {
                myToast("获取数据成功")
//                val result = response.body()?.string()
//                val gson = Gson()
//                gson.fromJson<List<HomeItemBean>>(result,object : TypeToken<List<HomeItemBean>>(){}.type)
//                myToast("获取数据成功"+result)
//                val address: String,
//                val age: Int,
//                val id: Int,
//                val name: String
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com1",24,1,"jason1","http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com2",24,2,"jason2","http://e.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com3",24,3,"jason3","http://e.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com4",24,4,"jason4","http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com5",24,5,"jason5","http://e.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg"))

                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        adapter.updateList(listHomeItemBean)
                        refreshLayout.isRefreshing = false//隐藏刷新控件
                    }
                })
            }

        })
    }
    private fun loadMoreDatas(offset:Int) {
        info { "offset=$offset" }
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/CaoJieNan/Sample/master/user.txt")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                isSetIntent = true//获取数据成功才能继续加载
                myToast("加载更多获取数据失败")
                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        refreshLayout.isRefreshing = false
                    }
                })

            }

            override fun onResponse(call: Call, response: Response) {
                listHomeItemBean.clear()
                myToast("加载更多获取数据成功")
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com1",24,1,"jason1","http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com2",24,2,"jason2","http://e.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com3",24,3,"jason3","http://e.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5f2c2a9e953da81cb39db3d1d.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com4",24,4,"jason4","http://e.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg"))
                listHomeItemBean.add(HomeItemBean("caojienan1008@foxmail.com5",24,5,"jason5","http://e.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg"))

                ThreadUtil.runOnMainThread(object : Runnable{
                    override fun run() {
                        adapter.updateMoreList(listHomeItemBean)
                        refreshLayout.isRefreshing = false//隐藏刷新控件
                        isSetIntent = true//获取数据成功才能继续加载
                    }
                })
            }

        })
    }
}