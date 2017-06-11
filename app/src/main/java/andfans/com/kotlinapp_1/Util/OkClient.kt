package andfans.com.kotlinapp_1.Util

import com.squareup.okhttp.OkHttpClient

/**
 * 创建了一个OkHttpClient的单例
 * Created by 兆鹏 on 2017/6/4.
 */
object OkClient {
    private val client = OkHttpClient()
    fun instance() = client
}