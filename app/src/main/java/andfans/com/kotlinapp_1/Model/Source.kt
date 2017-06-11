package andfans.com.kotlinapp_1.Model

/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */
interface Source<out T> {
    fun obtain(url:String):T
}