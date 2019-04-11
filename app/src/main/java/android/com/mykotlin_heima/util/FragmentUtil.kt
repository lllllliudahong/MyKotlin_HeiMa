package android.com.mykotlin_heima.util

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseActivity
import android.com.mykotlin_heima.base.BaseFragment
import android.com.mykotlin_heima.ui.fragment.HomeFragment
import android.com.mykotlin_heima.ui.fragment.MvFragment
import android.com.mykotlin_heima.ui.fragment.VBangFragment
import android.com.mykotlin_heima.ui.fragment.YuedanFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log

/**
 * 管理fragment的util类
 */
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragmenty by lazy {  HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YuedanFragment() }
//    var fragments : ArrayList<String> = ArrayList()
    var fragments = listOf(homeFragmenty,mvFragment,vbangFragment,yuedanFragment)
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }//通过懒加载，只会初始化一次，by lazy线程安全
    }


    /**
     * 根据tab id 获取对应的fragment   https://www.jianshu.com/p/c4c63e43bd00
     */
    fun getFragment(tabId : Int, transaction : FragmentTransaction, position : Int){
//        when(tabId){
//            R.id.tab_card ->  {
//                    transaction.replace(R.id.container,homeFragmenty ,tabId.toString())
//                    transaction.commit()
//            }
//            R.id.tab_commend ->  {
//                    transaction.replace(R.id.container,mvFragment ,tabId.toString())
//                    transaction.commit()
//            }
//            R.id.tab_hall ->  {
//                    transaction.replace(R.id.container,vbangFragment ,tabId.toString())
//                    transaction.commit()
//            }
//            R.id.tab_meet ->  {
//                    transaction.replace(R.id.container,yuedanFragment ,tabId.toString())
//                    transaction.commit()
//            }
//        }
//        isNull = 0
        Log.d("liuhong","position----${position}")
       var isFragment = fragments[position]
        when(tabId){
            R.id.tab_card -> switchContent(position,homeFragmenty,transaction,isFragment)
            R.id.tab_commend -> {switchContent(position,mvFragment,transaction,isFragment)}
            R.id.tab_hall -> {switchContent(position,vbangFragment,transaction,isFragment)}
            R.id.tab_meet -> {switchContent(position,yuedanFragment,transaction,isFragment)}
        }

    }


    fun switchContent(i:Int, to: BaseFragment, ft : FragmentTransaction, from: BaseFragment) {

        if (!to.isAdded) {
            Log.d("liuhong","----")
            ft.hide(from).add(R.id.container, to).show(to)
        }else {
            Log.d("liuhong","=====")
            ft.hide(from).show(to) // 隐藏当前的fragment，显示下一个
        }

        ft.commit()
    }

}