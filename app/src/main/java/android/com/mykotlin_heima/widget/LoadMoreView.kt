package android.com.mykotlin_heima.widget

import android.com.mykotlin_heima.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class LoadMoreView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_loadmore,this)
    }
}