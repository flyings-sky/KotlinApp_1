package andfans.com.kotlinapp_1.ui.fragment

import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Data.Cover
import andfans.com.kotlinapp_1.Model.CoverSource
import andfans.com.kotlinapp_1.ui.activity.ComicActivity
import andfans.com.kotlinapp_1.ui.adapter.CoverAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.fragment_home.*
/**
 *
 * Created by 兆鹏 on 2017/6/4.
 */
class HomeFragment : Fragment(){
    //伴生对象，可以使用类名.变量名的方式访问，类似于static
    companion object{
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }
    var mData = ArrayList<Cover>()
    //延迟初始化下面三个属性
    lateinit var mAdapter:CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 设置当该Fragment对应的Activity在发生配置变化(例如：横竖屏转换)时，
         * 该Fragment的实例不会被销毁，而是会被保留下来，这个属性不能应用于存在于回退栈中的Fragment
         * 当设置它为true时，
         * ·onDestroy()方法不会被调用，但是onDetach()方法仍然会调用，
         *      因为在发生配置变化时，Activity要重新创建，所以Fragment要先解除与旧Activity的绑定，
         *      再绑定新的Activity
         * ·onCreate(Bundle)将不会再次调用，因为Fragment的实例已经被保存下来的，不需要重新创建
         * ·onAttach(Activity)和onActivityCreated(Bundle)也将会被重新调用
         */
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //?.在这里表示当inflater不为空时执行LayoutInflater的inflate方法，否则返回空
        return inflater?.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mCoverList.layoutManager = GridLayoutManager(context,2)
//      CoverAdapter(mData,)
        //由于第一个参数存在默认值，所以可以只传入第二个参数
        mAdapter = CoverAdapter { view: View, position: Int ->  jump2Comic(position)}
        mCoverList.adapter = mAdapter
        homeRefresh.setOnRefreshListener {
            load()
        }
        //在UI线程中进行需要的操作，post的内容被添加到队列尾部
        homeRefresh.post { homeRefresh.isRefreshing = true }
    }

    //实现Fragment的懒加载，在Fragment可见时才进行数据的加载
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && mData.size == 0){
            load()
        }
    }
    private fun jump2Comic(position:Int){
        var intent = Intent(context,ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL,mData[position].link)
        startActivity(intent)
    }

    private fun load(){
        //启动一个协程，执行耗时操作
        async {
            //耗时操作:进行网络请求数据
            val data = CoverSource().obtain(AIM_URL)
            //切换回UI线程更新数据
            uiThread {
                mData = data
                mAdapter.refreshData(data)
                homeRefresh.isRefreshing = false
            }
        }
    }
}