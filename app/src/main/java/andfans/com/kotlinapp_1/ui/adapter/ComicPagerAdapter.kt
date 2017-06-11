package andfans.com.kotlinapp_1.ui.adapter

import andfans.com.kotlinapp_1.Data.Comic
import andfans.com.kotlinapp_1.ui.fragment.ComicFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class ComicPagerAdapter(var data:ArrayList<Comic> = ArrayList<Comic>()
                        ,fragmentManager:FragmentManager) :FragmentPagerAdapter(fragmentManager){
    override fun getCount() = data.size

    override fun getItem(position: Int) = newInstance(data[position].comicUrl)

    fun refreshData(newData:ArrayList<Comic>){
        data = newData
        notifyDataSetChanged()
    }

    fun newInstance(url:String) = ComicFragment(url)
}