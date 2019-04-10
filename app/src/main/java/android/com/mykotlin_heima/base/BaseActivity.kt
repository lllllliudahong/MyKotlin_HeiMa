package android.com.mykotlin_heima.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 所有Activity的基类
 */
abstract class BaseActivity : AppCompatActivity() ,AnkoLogger{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
        initData()
        initListener()
    }

    /**
     * 获取布局文件id
     */
    abstract fun getLayoutId() : Int

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

    protected fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

    inline fun <reified T: BaseActivity> startActivityAndFinsh(){
        startActivity<T>()
        finish()
    }



}