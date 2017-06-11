package andfans.com.kotlinapp_1.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * ViewPager的适配器
 * Created by 兆鹏 on 2017/6/4.
 */
class ContentPagerAdapter(val fragments:List<Fragment>,
                          val nameList:List<String>,
                          val fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount() = fragments.size
    override fun getItem(position: Int) = fragments[position]
    override fun getPageTitle(position: Int) = nameList[position]
    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }
}