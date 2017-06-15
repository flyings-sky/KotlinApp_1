package andfans.com.kotlinapp_1.ui.activity

import andfans.com.kotlinapp_1.MainActivity
import andfans.com.kotlinapp_1.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yasic.library.particletextview.MovingStrategy.RandomMovingStrategy
import com.yasic.library.particletextview.Object.ParticleTextViewConfig
import kotlinx.android.synthetic.main.activity_opening.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class OpeningActivity : AppCompatActivity() {
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        init()
    }

    private fun init(){
        val randomMovingStrategy = RandomMovingStrategy()
        val config = ParticleTextViewConfig.Builder()
                .setRowStep(8)
                .setColumnStep(8)
                .setTargetText("Comics")
                .setReleasing(0.1)
                .setParticleRadius(4.0f)
                .setMiniDistance(0.1)
                .setTextSize(180)
                .setDelay(-1)       //设置动画为不重复
                .setMovingStrategy(randomMovingStrategy)
                .instance()
        particleTextView.setConfig(config)
        particleTextView.startAnimation()
        async {
            //判断动画是否结束
            while(true){
                if(particleTextView.isAnimationPause){
                    flag = true
                    break
                }
            }
            if(flag){
                Thread.sleep(400)
                uiThread {
                    startActivitys()
                }
            }
        }
    }

    fun startActivitys(){
        startActivity(Intent(this,MainActivity().javaClass))
        finish()
    }
}
