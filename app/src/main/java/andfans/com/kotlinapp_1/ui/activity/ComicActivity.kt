package andfans.com.kotlinapp_1.ui.activity

import andfans.com.kotlinapp_1.Data.Comic
import andfans.com.kotlinapp_1.Model.ComicSource
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_comic.*

import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.ui.adapter.ComicPagerAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class ComicActivity : AppCompatActivity() {
    companion object{
        val INTENT_COMIC_URL = "url"
    }
    lateinit var adapter:ComicPagerAdapter
    var mData = ArrayList<Comic>()
    lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        url = intent.getStringExtra(INTENT_COMIC_URL)
        adapter = ComicPagerAdapter(mData,supportFragmentManager)
        comicPagers.adapter = adapter
        comicPagers.offscreenPageLimit = 2
    }

    override fun onResume() {
        super.onResume()
        async {
            val data = ComicSource().obtain(url)
            mData = data

            uiThread {
                adapter.refreshData(data)
            }
        }
    }
}
