package android.com.mykotlin_heima.ui.fragment

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.adapter.HomeAdapter
import android.com.mykotlin_heima.base.BaseFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment : BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {

        recyclerView.layoutManager = LinearLayoutManager(context)

//        val adapter = HomeAdapter()
        recyclerView.adapter = HomeAdapter()
    }


    override fun initData() {
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
            }

            override fun onResponse(call: Call, response: Response) {
                myToast("获取数据成功"+response.body()?.string())
            }

        })
    }
}