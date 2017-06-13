package andfans.com.kotlinapp_1

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 *
 * Created by 兆鹏 on 2017/6/11.
 */
class App:Application() {
    private val TAG = App::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        val maxMem = Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(maxMem/8)).build()
    }
}