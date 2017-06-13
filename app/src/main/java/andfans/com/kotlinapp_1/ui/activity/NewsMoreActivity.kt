package andfans.com.kotlinapp_1.ui.activity

import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Model.MoreNewsSource
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.ui.adapter.MoreNewsAdapter
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_news_more.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class NewsMoreActivity : AppCompatActivity() {

    companion object{
        val TOOLBAR_TITLE = "toolbarTitle"
        val MORE_LINK = "moreLink"
    }

    lateinit var adapter:MoreNewsAdapter
    var mData = ArrayList<News>()
    lateinit var url:String
    lateinit var title:String
    val context:Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_more)
        init()
    }

    fun init(){
        url = intent.getStringExtra(MORE_LINK)
        title = intent.getStringExtra(TOOLBAR_TITLE)
        mToolBar_title.text = title
        mToolBar.title = ""
        setSupportActionBar(mToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter = MoreNewsAdapter()
        moreNewsList.adapter = adapter
        moreNewsList.layoutManager = LinearLayoutManager(context)
        moreNewsRefresh.setOnRefreshListener {
            moreNewsRefresh.isRefreshing = true
            load()
        }

        moreNewsRefresh.post {
            moreNewsRefresh.isRefreshing = true
        }
    }

    fun load() = async {
        val data = MoreNewsSource().obtain(url)
        mData = data

        uiThread {
            adapter.refreshData(mData)
            moreNewsRefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
