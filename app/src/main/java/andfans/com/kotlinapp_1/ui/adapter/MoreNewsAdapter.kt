package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Model.NewsDetailSource
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Util.WebDetailDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_child_more_news.view.*
/**
 *
 * Created by 兆鹏 on 2017/6/11.
 */
class MoreNewsAdapter(var data:List<News> = ArrayList())
    :RecyclerView.Adapter<MoreNewsAdapter.MoreNewsAdapterViewHolder>(){
    override fun onBindViewHolder(holder: MoreNewsAdapterViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }

    private fun bindView(itemView: View,position: Int){
        val news = data[position]
        itemView.tv_titles.text = news.title
        itemView.tv_times.text = news.createdTime
        itemView.containers.setOnClickListener {
            WebDetailDialog(itemView.context,news,NewsDetailSource())
        }
    }

    fun refreshData(newData:List<News>){
        data = newData
        notifyDataSetChanged()
    }
    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoreNewsAdapterViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_child_more_news,parent,false)
        return MoreNewsAdapterViewHolder(itemView)
    }

    class MoreNewsAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}