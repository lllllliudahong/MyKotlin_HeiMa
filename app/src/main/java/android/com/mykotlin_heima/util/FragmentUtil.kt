package android.com.mykotlin_heima.util

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseFragment
import android.com.mykotlin_heima.ui.fragment.HomeFragment
import android.com.mykotlin_heima.ui.fragment.MvFragment
import android.com.mykotlin_heima.ui.fragment.VBangFragment
import android.com.mykotlin_heima.ui.fragment.YuedanFragment

/**
 * 管理fragment的util类
 */
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YuedanFragment() }
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }//通过懒加载，只会初始化一次，by lazy线程安全
    }


    /**
     * 根据tab id 获取对应的fragment
     */
    fun getFragment(tabId : Int) : BaseFragment?{
        when(tabId){
            R.id.tab_card -> return homeFragment
            R.id.tab_commend -> return mvFragment
            R.id.tab_hall -> return vbangFragment
            R.id.tab_meet -> return yuedanFragment
        }
        return null
    }


}