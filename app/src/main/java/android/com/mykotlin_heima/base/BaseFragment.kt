package android.com.mykotlin_heima.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.support.v4.toast

/**
 * 所有Fragment的基类
 */
abstract class BaseFragment : Fragment() , AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * fragment初始化
     */
    protected fun init() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局view
     */
    abstract fun initView():View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initListener()
    }

    /**
     * adapter listener
     */
   open protected fun initListener() {
    }

    /**
     * 初始化数据
     */
    open protected fun initData() {
    }
    fun myToast(msg:String){
        context?.runOnUiThread { toast(msg) }
    }
}