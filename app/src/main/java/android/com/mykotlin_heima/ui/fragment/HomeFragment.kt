package android.com.mykotlin_heima.ui.fragment

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.adapter.HomeAdapter
import android.com.mykotlin_heima.base.BaseFragment
import android.com.mykotlin_heima.model.HomeItemBean
import android.com.mykotlin_heima.presenter.impl.HomeFragmentPersneterImpl
import android.com.mykotlin_heima.view.HomeFragmentView
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.find

class HomeFragment : BaseFragment(), HomeFragmentView {
    val adapter by lazy { HomeAdapter() }
    lateinit var recyclerView : RecyclerView
    lateinit var refreshLayout : SwipeRefreshLayout
    var isSetIntent = true
    val homePersenter by lazy { HomeFragmentPersneterImpl(this) }
    override fun initView(): View? {
        info("HomeFragment：initView")
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


        //下拉刷新的监听
        refreshLayout.setOnRefreshListener {
            homePersenter.loadDatas()
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
                            homePersenter.loadMoreDatas(adapter.itemCount-1)
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
        homePersenter.loadDatas()
    }


    override fun loadMoreSuccess(listHomeItemBean: ArrayList<HomeItemBean>) {
        myToast("加载更多获取数据成功")
        isSetIntent = true
        adapter.updateMoreList(listHomeItemBean)
        info { "size = ${listHomeItemBean.size}" }
    }

    override fun onError(message: String?) {
        isSetIntent = true
        myToast("获取数据失败")
        refreshLayout.isRefreshing = false//隐藏刷新控件
    }

    override fun loadSuccess(listHomeItemBean: ArrayList<HomeItemBean>) {
        myToast("获取数据成功")
        adapter.updateList(listHomeItemBean)
        refreshLayout.isRefreshing = false//隐藏刷新控件
    }

}