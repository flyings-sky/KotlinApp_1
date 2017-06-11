package andfans.com.kotlinapp_1.Data

/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */
data class Cover(val coverUrl:String,val title:String,val link:String)

data class Comic(val comicUrl:String)

data class Page(val title:String,val link:String)

data class BookInfo(val updateTime:String,val description:String)

data class BookDetail(val pages:List<Page>,val info:BookInfo){
    operator fun get(position:Int) = pages[position]
    fun size() = pages.size
}

data class News(val title: String,val createdTime:String,val link: String)

data class NewsContainer(val title: String,val newsList:List<News>)