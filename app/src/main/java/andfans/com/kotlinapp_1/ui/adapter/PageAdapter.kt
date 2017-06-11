package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.Data.Page
import andfans.com.kotlinapp_1.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_page.*
import kotlinx.android.synthetic.main.item_page.view.*

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class PageAdapter(var data:List<Page> = ArrayList(),val itemClick:(View,Int) -> Unit)
    :RecyclerView.Adapter<PageAdapter.PageAdapterViewHolder>(){

    override fun onBindViewHolder(holder: PageAdapterViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }

    private fun bindView(itemView: View,position: Int){
        val page = data[position]
        itemView.tv_page.text = page.title
        itemView.tv_page.setOnClickListener { itemClick(itemView,position) }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PageAdapterViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_page,parent,false)
        return PageAdapterViewHolder(itemView)
    }

    fun refreshData(newData:List<Page>){
        data = newData
        notifyDataSetChanged()
    }


    class PageAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}