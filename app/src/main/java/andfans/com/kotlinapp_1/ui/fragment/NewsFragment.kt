package andfans.com.kotlinapp_1.ui.fragment

import andfans.com.kotlinapp_1.Data.NewsContainer
import andfans.com.kotlinapp_1.Model.NewsSource
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.ui.adapter.NewsContainerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 *
 * Created by 兆鹏 on 2017/6/11.
 */
class NewsFragment:Fragment() {
    companion object{
        val AIM_URL = "http://www.ishuhui.net/CMS/"
    }

    var mData = ArrayList<NewsContainer>()
    lateinit var adapter:NewsContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsList.layoutManager = LinearLayoutManager(context)
        adapter = NewsContainerAdapter()
        newsList.adapter = adapter
        newsRefresh.setOnRefreshListener {
            load()
        }

        newsRefresh.post {
            newsRefresh.isRefreshing = true
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && mData.size == 0){
            load()
        }
    }

    private fun load(){
        async {
            val data = NewsSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(data)
                newsRefresh.isRefreshing = false
            }
        }
    }


}