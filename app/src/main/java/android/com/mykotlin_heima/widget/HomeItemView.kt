package android.com.mykotlin_heima.widget

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.model.HomeItemBean
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class HomeItemView : RelativeLayout, AnkoLogger{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_home,this)
    }

    /**
     * 刷新每个条目的数据
     */
    fun setData(data: HomeItemBean) {
        val title : TextView = find(R.id.title)
        val desc : TextView = find(R.id.desc)
        val bg : ImageView = find(R.id.bg)
        title.text = data.address
        desc.text = data.name

        Picasso.get().load(data.img).into(bg)

        this.setOnClickListener {
            context?.runOnUiThread { toast("asss---${data.id}" ) }
        }

    }
}