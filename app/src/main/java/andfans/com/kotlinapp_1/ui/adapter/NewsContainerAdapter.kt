package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.Data.NewsContainer
import andfans.com.kotlinapp_1.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_news.view.*

/**
 *
 * Created by 兆鹏 on 2017/6/11.
 */
class NewsContainerAdapter(var data:List<NewsContainer> = ArrayList()):RecyclerView.Adapter<NewsContainerAdapter.NewsContainerAdapterViewHolder>() {
    override fun onBindViewHolder(holder: NewsContainerAdapterViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }

    private fun bindView(itemView: View,position: Int){
        val newsContainer = data[position]
        itemView.tv_container_title.text = newsContainer.title
        itemView.rv_child_container.layoutManager = LinearLayoutManager(itemView.context)
        itemView.rv_child_container.adapter = NewsAdapter(newsContainer.newsList)
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsContainerAdapterViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_news,parent,false)
        return NewsContainerAdapterViewHolder(itemView)
    }

    fun refreshData(newsData:List<NewsContainer>){
        data = newsData
        notifyDataSetChanged()
    }

    class NewsContainerAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}