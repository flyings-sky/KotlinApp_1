package andfans.com.kotlinapp_1.Util

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class ScrollWebView : WebView{
    private val TAG = ScrollWebView::class.java.simpleName

    var scrollListener:((x:Int,y:Int) -> Unit) ?= null
    var topListener:((isScrollToTop:Boolean) -> Unit) ?= null
    var bottomListener:((isScrollToBottom:Boolean) -> Unit) ?= null

    constructor(context: Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs, defStyleAttr)
    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
    }
}