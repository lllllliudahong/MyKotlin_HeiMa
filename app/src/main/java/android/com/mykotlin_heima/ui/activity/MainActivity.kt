package android.com.mykotlin_heima.ui.activity

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseActivity
import android.com.mykotlin_heima.util.FragmentUtil
import android.com.mykotlin_heima.util.ToolBarManager
import android.content.Intent
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() , ToolBarManager{

    override val toolBar: Toolbar
        get() = toolbar

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initToolBar("Main")
        toolBar.inflateMenu(R.menu.main)
        toolBar.setOnMenuItemClickListener {
            when(it?.itemId){
                R.id.setting ->{
//                        startActivity(Intent(toolBar.context,SettingActivity::class.java))
                    startActivity<SettingActivity>()
                }
            }
             true
        }
        toolBar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                when(p0?.itemId){
                    R.id.setting ->{
//                        startActivity(Intent(toolBar.context,SettingActivity::class.java))
                        startActivity<SettingActivity>()
                    }
                }
                return true
            }
        })
    }

    override fun initListener() {
        //设置tab切换监听
        bottomBar.setOnTabSelectListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()
        }
    }
}
