package andfans.com.kotlinapp_1.ui.fragment

import andfans.com.kotlinapp_1.Data.Cover
import andfans.com.kotlinapp_1.Model.BookSource
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.ui.activity.BookDetailActivity
import andfans.com.kotlinapp_1.ui.adapter.CoverAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class BookFragment:Fragment() {
    companion object{
        val AIM_URL = "http://www.ishuhui.net/ComicBookList/"
    }

    var mData = ArrayList<Cover>()
    lateinit var adapter:CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_book,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        bookList.layoutManager = GridLayoutManager(context,2)
        adapter = CoverAdapter{_:View,i:Int -> jump2Detail(i)}
        bookList.adapter = adapter

        bookRefresh.setOnRefreshListener {
            load()
        }

        bookRefresh.post {
            bookRefresh.isRefreshing = true
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser&&mData.size == 0){
            load()
        }
    }

    private fun jump2Detail(position:Int){
        val intent = Intent(context, BookDetailActivity().javaClass)
        intent.putExtra(BookDetailActivity.INTENT_COVER_URL,mData[position].coverUrl)
        intent.putExtra(BookDetailActivity.INTENT_URL,mData[position].link)
        intent.putExtra(BookDetailActivity.INTENT_TITLE,mData[position].title)
        startActivity(intent)

    }

    private fun load(){
        async {
            val data = BookSource().obtain(AIM_URL)

            uiThread {
                mData = data
                adapter.refreshData(mData)
                bookRefresh.isRefreshing = false
            }
        }
    }
}