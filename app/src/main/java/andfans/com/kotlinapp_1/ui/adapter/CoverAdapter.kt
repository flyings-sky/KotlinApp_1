package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Data.Cover
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cover.view.*

/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */
/**
 * CoverAdapter的构造函数包含两个参数:
 * 第一个参数是:List<Cover>类型，默认值为ArrayList()
 * 第二个参数是:一个函数，这个函数的参数是(View,Int)，返回值是Unit(空)
 */

class CoverAdapter(var data:List<Cover> = ArrayList(),
                   var itemClick:(View,Int) -> Unit)
    :RecyclerView.Adapter<CoverAdapter.CoverViewHolder>(){

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }
    //如果函数中只有一个表达式，可以采用=的简明写法
    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cover,parent,false)
        return CoverViewHolder(itemView)
    }

    fun refreshData(newData:List<Cover>){
        data = newData
        notifyDataSetChanged()
    }

    private fun bindView(itemView: View,position: Int){
        val cover = data[position]
        itemView.tv_cover.text = cover.title
        Picasso.with(itemView.context).load(cover.coverUrl).into(itemView.iv_cover)
        itemView.coverContainer.setOnClickListener {
            itemClick(itemView,position)
        }
    }

    class CoverViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}