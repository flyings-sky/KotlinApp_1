package andfans.com.kotlinapp_1

import andfans.com.kotlinapp_1.ui.activity.AboutActivity
import andfans.com.kotlinapp_1.ui.adapter.ContentPagerAdapter
import andfans.com.kotlinapp_1.ui.fragment.BookFragment
import andfans.com.kotlinapp_1.ui.fragment.HomeFragment
import andfans.com.kotlinapp_1.ui.fragment.NewsFragment
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        val GITHUB_URL = "https://github.com/flyings-sky/KotlinApp_1"
    }

    val nameResList:ArrayList<Int> = arrayListOf(R.string.tab_one,R.string.tab_two,R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        //若高阶函数的最后一个参数是函数，那么在使用Lambda表达式作为参数值时，可以把Lambda表达式放在参数括号外面
        fab.setOnClickListener { jump2MyGithub() }
        val fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())
        //将Int型ArrayList转换成String型ArrayList
        val nameList = nameResList.map(this::getString)
        //给ViewPager设置Adapter
        viewPager.adapter = ContentPagerAdapter(fragments,nameList,supportFragmentManager)
        //设置ViewPager缓存的页数
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun jump2MyGithub(){
        val uri = Uri.parse(GITHUB_URL)
        /**
         * Intent.ACTION_VIEW用于显示用户数据，会根据用户的数据类型打开相应的Activity
         * 例如:
         * Uri uri = Uri.parse("http://www.google.com");     //浏览器(网址必须带http)
         * Uri uri = Uri.parse("tel:1232333");              //拨号程序
         * Uri uri = Uri.parse("geo:39.899533,116.036476");  //打开地图定位
         */
        val intent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.action_about){
            val intent = Intent(this,AboutActivity().javaClass)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
