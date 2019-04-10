package android.com.mykotlin_heima.util

import android.support.v7.widget.Toolbar

interface ToolBarManager {

    val toolBar : Toolbar

    fun initToolBar(title:String){
        toolBar.setTitle(title)
    }
}