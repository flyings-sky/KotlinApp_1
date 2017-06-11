package andfans.com.kotlinapp_1.ui.fragment

import andfans.com.kotlinapp_1.R
import andfans.com.kotlinapp_1.Util.LoadingImgDialog
import andfans.com.kotlinapp_1.Util.snackbar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_comic_page.*
/**
 *
 * Created by 兆鹏 on 2017/6/7.
 */
class ComicFragment(val url:String):Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_comic_page,container,false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.material_deep_purple_50)
                .into(iv_comic,object :Callback{
                    override fun onSuccess() {
                        if(progressBar != null){
                            progressBar.visibility = View.GONE
                        }
                    }

                    override fun onError() {
                        iv_comic.snackbar(R.string.network_error)
                    }
                })
    }

}