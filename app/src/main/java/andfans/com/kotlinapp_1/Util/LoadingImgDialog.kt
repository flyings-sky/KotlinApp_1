package andfans.com.kotlinapp_1.Util

import andfans.com.kotlinapp_1.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.PopupWindow
import android.widget.RelativeLayout

/**
 * 自定义圆形进度条
 * Created by 兆鹏 on 2017/6/10.
 */
class LoadingImgDialog(private val mContext: Context, private val residBg: Int//背景图片的ID
) {
    private var popupDialog: PopupWindow? = null
    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var layout: RelativeLayout? = null
    private var layoutBg: RelativeLayout? = null
    private var loadingDialog: View? = null
    //背景旋转动画
    private var rotateAnimation: RotateAnimation? = null
    //透明度动画效果
    private var alphaAnimationIn: AlphaAnimation? = null
    private var alphaAnimationOut: AlphaAnimation? = null

    private fun initAnim() {
        rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation!!.duration = 2000
        //使动画无限循环
        rotateAnimation!!.repeatMode = Animation.RESTART
        rotateAnimation!!.repeatCount = Animation.INFINITE
        //设置匀速变化的插值器
        rotateAnimation!!.interpolator = LinearInterpolator()
        alphaAnimationIn = AlphaAnimation(0f, 1f)
        //设置动画结束后，停留在最后的位置
        alphaAnimationIn!!.fillAfter = true
        alphaAnimationIn!!.duration = 200
        alphaAnimationIn!!.interpolator = LinearInterpolator()
        alphaAnimationOut = AlphaAnimation(1f, 0f)
        alphaAnimationIn!!.fillAfter = true
        alphaAnimationIn!!.duration = 100
        alphaAnimationIn!!.interpolator = LinearInterpolator()
        // 监听动作，动画结束时，隐藏LoadingColorDialog
        alphaAnimationOut!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                dismiss()
            }
        })
    }

    fun dismiss() {
        if (popupDialog != null && popupDialog!!.isShowing) {
            layoutBg!!.clearAnimation()
            loadingDialog!!.clearAnimation()
            popupDialog!!.dismiss()
        }
    }

    val isShowing: Boolean
        get() {
            if (popupDialog != null && popupDialog!!.isShowing) {
                return true
            }
            return false
        }

    fun show() {
        dismiss()
        initAnim()
        layout = layoutInflater.inflate(R.layout.view_loadingdialog, null) as RelativeLayout
        loadingDialog = layout!!.findViewById(R.id.loading_dialog)
        loadingDialog!!.setBackgroundResource(residBg)
        layoutBg = layout!!.findViewById(R.id.bgLayout) as RelativeLayout
        popupDialog = PopupWindow(layout, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)
        val parentView = (mContext as Activity).window.findViewById(Window.ID_ANDROID_CONTENT)
        popupDialog!!.showAtLocation(parentView, Gravity.CENTER, 0, 0)
        layoutBg!!.startAnimation(alphaAnimationIn)
        loadingDialog!!.startAnimation(rotateAnimation)
    }
}
