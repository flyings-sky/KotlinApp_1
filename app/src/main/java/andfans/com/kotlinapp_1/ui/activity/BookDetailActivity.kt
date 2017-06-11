package andfans.com.kotlinapp_1.ui.activity

import andfans.com.kotlinapp_1.Data.BookDetail
import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Data.Page
import andfans.com.kotlinapp_1.Model.BookDetailSource
import andfans.com.kotlinapp_1.Model.SBSSource
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book_detail.*
import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Util.WebDetailDialog
import andfans.com.kotlinapp_1.Util.snackbar
import andfans.com.kotlinapp_1.ui.adapter.PageAdapter
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class BookDetailActivity : AppCompatActivity() {
    lateinit var url:String
    lateinit var adapter:PageAdapter
    lateinit var bookDetail:BookDetail

    companion object{
        val INTENT_COVER_URL = "cover"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init(){
        val coverUrl = intent.getStringExtra(INTENT_COVER_URL)
        val title = intent.getStringExtra(INTENT_TITLE)
        url = intent.getStringExtra(INTENT_URL)
        //给左上角图标的左边加一个返回的图标,使得可以返回上一级界面
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = title
        Picasso.with(this).load(coverUrl).into(backgroundImage)
        pageRefresh.setOnRefreshListener {
            load()
        }

        pageList.layoutManager = GridLayoutManager(this,4)
        adapter = PageAdapter{ _ ,position ->
            //如果漫画标题中包含SBS使用WebView加载
            if(title.contains("SBS")){
                val news = News(bookDetail[position].title,"",bookDetail[position].link)
                WebDetailDialog(this,news,SBSSource())
            }else{
                jump2Read(position)
            }
        }
        pageList.adapter = adapter

        fabbutton.setOnClickListener { showBookInfo() }
    }

    override fun onResume() {
        super.onResume()
        pageRefresh.post {
            pageRefresh.isRefreshing = true
        }
        load()
    }

    fun load() = async {
        bookDetail = BookDetailSource().obtain(url)
        val data = bookDetail.pages as ArrayList<Page>

        uiThread {
            adapter.refreshData(data)
            pageRefresh.isRefreshing = false
            if(bookDetail.size() == 0){
                showError()
            }
        }
    }

    private fun showError(){
        pageList.snackbar(R.string.page_load_error)
    }

    private fun jump2Read(position:Int){
        var intent = Intent(this,ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL,bookDetail[position].link)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_book_detail,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == android.R.id.home){
            onBackPressed()
            return true
        }else if(id == R.id.action_info){
            showBookInfo()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showBookInfo(){
        val bookInfo = bookDetail.info
        pageList.snackbar(bookInfo.description + "\n" + bookInfo.updateTime)
    }
}
