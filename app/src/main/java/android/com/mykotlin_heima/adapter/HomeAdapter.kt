package android.com.mykotlin_heima.adapter

import android.com.mykotlin_heima.widget.HomeItemView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeHolder {
        return HomeHolder(HomeItemView(p0.context))
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}