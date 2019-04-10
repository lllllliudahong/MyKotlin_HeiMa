package android.com.mykotlin_heima.ui.activity

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseActivity
import android.com.mykotlin_heima.util.ToolBarManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : BaseActivity() , ToolBarManager{

    override val toolBar: Toolbar
        get() = toolbar

    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initData() {
        initToolBar("About")
    }
}
