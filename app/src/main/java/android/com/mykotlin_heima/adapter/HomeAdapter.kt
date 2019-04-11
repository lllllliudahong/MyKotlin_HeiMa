package android.com.mykotlin_heima.adapter

import android.com.mykotlin_heima.model.HomeItemBean
import android.com.mykotlin_heima.widget.HomeItemView
import android.com.mykotlin_heima.widget.LoadMoreView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    var list : ArrayList<HomeItemBean> = ArrayList()
    /**
     * 更新数据
     */
    fun updateList(list : ArrayList<HomeItemBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    /**
     * 加载更多
     */
    fun updateMoreList(list : ArrayList<HomeItemBean>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): HomeHolder {
        if (viewType == 1){
            //最后一条
            return HomeHolder(LoadMoreView(p0.context))
        }else{
            return HomeHolder(HomeItemView(p0.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {
        //如果是最后一条就不需要刷新
        if(p1 == list.size)return
        //条目数据
        val data = list.get(p1)
        //条目view
        val itemView = p0.itemView as HomeItemView
        //条目刷新
        itemView.setData(data)
    }


    override fun getItemViewType(position: Int): Int {
        if (position == list.size){
            //最后一条
            return 1
        }else{
            return 0
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}