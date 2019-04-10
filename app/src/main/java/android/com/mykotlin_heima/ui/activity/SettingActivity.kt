package android.com.mykotlin_heima.ui.activity

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseActivity
import android.com.mykotlin_heima.util.ToolBarManager
import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import android.util.Log
import kotlinx.android.synthetic.main.toolbar.*

class SettingActivity : BaseActivity(),ToolBarManager{
    override val toolBar: Toolbar by lazy { toolbar }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initToolBar("Setting")
        //获取设置状态
        val ps =  PreferenceManager.getDefaultSharedPreferences(this)
        val push = ps.getBoolean("push",false)
        Log.d("liuhong","push = ${push}")
    }
}
