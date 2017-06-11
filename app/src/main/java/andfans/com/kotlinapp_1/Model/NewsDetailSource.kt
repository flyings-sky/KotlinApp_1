package andfans.com.kotlinapp_1.Model

import andfans.com.kotlinapp_1.Util.getHtml
import org.jsoup.Jsoup

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class NewsDetailSource :Source<String> {
    override fun obtain(url: String): String {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        val contentHtml = "<html>${doc.select("head")}<body>${doc.select("div.featureContentTextare")}</body></html>"
        return contentHtml
    }
}