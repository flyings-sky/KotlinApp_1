package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Model.NewsDetailSource
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Util.WebDetailDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_child_news.view.*
/**
 *
 * Created by 兆鹏 on 2017/6/11.
 */
class NewsAdapter(var data:List<News> = ArrayList())
    :RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>(){
    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }

    private fun bindView(itemView: View,position: Int){
        val news = data[position]
        itemView.tv_title.text = news.title
        itemView.tv_time.text = news.createdTime
        if(position % 2 == 0){
            itemView.container.setBackgroundResource(R.color.alpha_grey)
        }

        itemView.container.setOnClickListener {
            WebDetailDialog(itemView.context,news,NewsDetailSource())
        }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsAdapterViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_child_news,parent,false)
        return NewsAdapterViewHolder(itemView)
    }

    class NewsAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}