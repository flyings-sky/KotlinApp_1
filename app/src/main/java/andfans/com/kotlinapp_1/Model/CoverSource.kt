package andfans.com.kotlinapp_1.Model

import andfans.com.kotlinapp_1.Data.Cover
import andfans.com.kotlinapp_1.Util.getHtml
import org.jsoup.Jsoup

/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */
class CoverSource : Source<ArrayList<Cover>> {
    //根据传入的url获取数据
    override fun obtain(url: String): ArrayList<Cover> {
        val list = ArrayList<Cover>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        val elements = doc.select("ul.mangeListBox").select("li")
        for (element in elements){
            val coverUrl = element.select("img").attr("src")
            val title = element.select("h1").text() + "\n" + element.select("h2").text()
            val link = "http://www.ishuhui.net" + element.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl,title,link)
            list.add(cover)
        }

        return list
    }
}