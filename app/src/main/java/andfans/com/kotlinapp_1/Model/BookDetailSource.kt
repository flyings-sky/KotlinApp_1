package andfans.com.kotlinapp_1.Model

import andfans.com.kotlinapp_1.Data.BookDetail
import andfans.com.kotlinapp_1.Data.BookInfo
import andfans.com.kotlinapp_1.Data.Page
import andfans.com.kotlinapp_1.Util.getHtml
import org.jsoup.Jsoup

/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class BookDetailSource():Source<BookDetail> {
    override fun obtain(url: String): BookDetail {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val pages = ArrayList<Page>()
        val elements = doc.select("div.volumeControl").select("a")

        for (element in elements) {
            val title = element.text()
            val link = "http://ishuhui.net/" + element.attr("href")
            val page = Page(title, link)
            pages.add(page)
        }

        val updateTime = doc.select("div.mangaInfoDate").text()
        val detail = doc.select("div.mangaInfoTextare").text()
        val info = BookInfo(updateTime, detail)

        return BookDetail(pages, info)
    }

}