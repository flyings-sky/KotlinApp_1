package andfans.com.kotlinapp_1.Model

import andfans.com.kotlinapp_1.Data.News
import andfans.com.kotlinapp_1.Util.getHtml
import org.jsoup.Jsoup

/**
 *
 * Created by 兆鹏 on 2017/6/12.
 */
class MoreNewsSource :Source<ArrayList<News>>{
    override fun obtain(url: String): ArrayList<News> {
        val newsList = ArrayList<News>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        val elements = doc.select("div.reportersBox").select("div.reportersMain").select("ul.reportersList")
        for (element in elements.select("li").select("a")){
            val newsTitle = element.select("span")[0].text()
            val creatAtTime = element.select("span")[1].text()
            val link = "http://www.ishuhui.net" + element.attr("href")
            val news = News(newsTitle,creatAtTime,link)
            newsList.add(news)
        }

        return newsList
    }

}