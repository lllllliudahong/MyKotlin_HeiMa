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
    var fragments : ArrayList<String> = ArrayList()
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }//通过懒加载，只会初始化一次，by lazy线程安全
    }


    /**
     * 根据tab id 获取对应的fragment   https://www.jianshu.com/p/c4c63e43bd00
     */
    var isNull = 0
    fun getFragment(tabId : Int, transaction : FragmentTransaction){
        when(tabId){
            R.id.tab_card ->  {
                for (it in fragments){
                    if(it.equals("homeFragmenty")){
                        isNull += 1
                    }
                }
                if(isNull>0){
                    Log.d("liuhong","111----")
                    transaction.show(homeFragmenty)
                    transaction.hide(mvFragment)
                    transaction.hide(vbangFragment)
                    transaction.hide(yuedanFragment)
                }else{
                    Log.d("liuhong","111====")
                    fragments.add("homeFragmenty")
                    transaction.replace(R.id.container,homeFragmenty ,tabId.toString())
                }
            }
            R.id.tab_commend ->  {
                for (it in fragments){
                    if(it.equals("mvFragment")){
                        isNull += 1
                    }
                }
                if(isNull>0){
                    Log.d("liuhong","222----")
                    transaction.show(mvFragment)
                    transaction.hide(homeFragmenty)
                    transaction.hide(vbangFragment)
                    transaction.hide(yuedanFragment)
                }else{
                    Log.d("liuhong","222====")
                    fragments.add("mvFragment")
                    transaction.replace(R.id.container,mvFragment ,tabId.toString())
                }
            }
            R.id.tab_hall ->  {
                for (it in fragments){
                    for (it in fragments){
                        if(it.equals("vbangFragment")){
                            isNull += 1
                        }
                    }
                }
                if(isNull>0){
                    Log.d("liuhong","333----")
                    transaction.show(vbangFragment)
                    transaction.hide(homeFragmenty)
                    transaction.hide(mvFragment)
                    transaction.hide(yuedanFragment)
                }else{
                    Log.d("liuhong","333====")
                    fragments.add("vbangFragment")
                    transaction.replace(R.id.container,vbangFragment ,tabId.toString())
                }
            }
            R.id.tab_meet ->  {
                for (it in fragments){
                    if(it.equals("yuedanFragment")){
                        isNull += 1
                    }
                }
                if(isNull>0){
                    Log.d("liuhong","444----")
                    transaction.show(yuedanFragment)
                    transaction.hide(homeFragmenty)
                    transaction.hide(mvFragment)
                    transaction.hide(vbangFragment)
                }else{
                    Log.d("liuhong","444====")
                    fragments.add("yuedanFragment")
                    transaction.replace(R.id.container,yuedanFragment ,tabId.toString())
                }
            }
//            R.id.tab_commend ->  mvFragment
//            R.id.tab_hall ->  vbangFragment
//            R.id.tab_meet ->  yuedanFragment
        }
//        transaction.replace(R.id.container,fagment!! ,tabId.toString())
        isNull = 0
        transaction.commit()

    }


}