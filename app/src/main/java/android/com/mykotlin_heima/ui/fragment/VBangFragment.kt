package android.com.mykotlin_heima.ui.fragment

import android.com.mykotlin_heima.base.BaseFragment
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.info

class VBangFragment : BaseFragment() {
    override fun initView(): View? {
        info("VBangFragment：initView")
        val tv = TextView(context)
        tv.text = javaClass.simpleName
        return tv
    }
}