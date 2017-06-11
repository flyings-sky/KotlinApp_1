package andfans.com.kotlinapp_1.Util

import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Model.NewsDetailSource
import andfans.com.kotlinapp_1.Model.Source
import andfans.com.kotlinapp_1.R
import android.content.Context
import android.graphics.Bitmap
import android.support.design.widget.BottomSheetDialog
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class WebDetailDialog(val context:Context,val news:News,val source:Source<String>) {
    val dialog = BottomSheetDialog(context)
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_web_detail,null)
        val webView = view.find<ScrollWebView>(R.id.webView)
        val titleBar = view.find<Toolbar>(R.id.titleBar)
        val refresh = view.find<SwipeRefreshLayout>(R.id.newsDetailRefresh)
        //val loadDialog = LoadingImgDialog(context,R.drawable.loadings)
        titleBar.subtitle = news.title

        webView.settings.textZoom = 80
        //将图片调整到适合webView的大小
        webView.settings.useWideViewPort = true
        //缩放至屏幕大小
        webView.settings.loadWithOverviewMode = true
        //支持缩放
        webView.settings.setSupportZoom(true)
        //设置内置的缩放控件
        webView.settings.builtInZoomControls = true
        //隐藏原生的缩放控件
        webView.settings.displayZoomControls = false
        //支持内容重新布局
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        //开启Application Caches功能
        webView.settings.setAppCacheEnabled(true)
        val cacheDirPath = context.filesDir.absolutePath + "firstKotlin"
        //设置缓存路径
        webView.settings.setAppCachePath(cacheDirPath)
        class MyWebViewClient:WebViewClient(){
            //开始加载界面
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //Log.d("AAA","开始")
                //loadDialog.show()

            }
            //界面加载结束
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //Log.d("AAA","结束")
                //loadDialog.dismiss()
            }
        }
        webView.setWebViewClient(MyWebViewClient())

        dialog.setContentView(view)

        refresh.post { refresh.isRefreshing = true }
        async {
            val html = NewsDetailSource().obtain(news.link)

            uiThread {
                webView.loadDataWithBaseURL("http://www.ishuhui.net",html,"text/html","utf-8",null)
                //webView.loadData(html, "text/html", null)
                refresh.isRefreshing = false
            }
        }

        refresh.setOnRefreshListener {
            async {
                val html = source.obtain(news.link)
                uiThread {
                    webView.loadDataWithBaseURL("http://www.ishuhui.net",html,"text/html","utf-8",null)
                    //webView.loadData(html,"text/html",null)
                    refresh.isRefreshing = false
                }
            }
        }
        dialog.show()
    }
}