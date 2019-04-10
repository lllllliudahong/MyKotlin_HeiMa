package android.com.mykotlin_heima.ui.activity

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.base.BaseActivity
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationStart(p0: View?) {
    }

    override fun onAnimationCancel(p0: View?) {
    }

    override fun onAnimationEnd(p0: View?) {
        startActivityAndFinsh<MainActivity>()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(splash_imageView).scaleX(1.0f).scaleY(1.0f).setDuration(2000).setListener(this)
    }
}