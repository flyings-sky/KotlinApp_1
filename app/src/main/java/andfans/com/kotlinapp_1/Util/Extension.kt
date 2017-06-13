package andfans.com.kotlinapp_1.Util

import android.support.design.widget.Snackbar
import android.view.View
import com.squareup.okhttp.Request

/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */

fun getHtml(url:String):String{
    val client = OkClient.instance()
    val request = Request.Builder()
            .url(url)
            .build()
    val response = client.newCall(request).execute()
    return response.body().string()
}

fun View.snackbar(message:Int, duration: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(this,message,duration).show()
}

fun View.snackbar(message:String,duration: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(this,message,duration).show()
}