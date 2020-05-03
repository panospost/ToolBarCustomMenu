package com.example.jetpackcomposetest

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.animation.PathInterpolatorCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }


    private fun animateMenuOptionsHeight(parentheight: Int) {
        val widthAnimator = ValueAnimator.ofInt(customViewTest.height, parentheight * 2 / 3)
        widthAnimator.duration = 400
        widthAnimator.interpolator = PathInterpolatorCompat.create(0.215f, 0.610f, 0.355f, 1.000f)
        widthAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                if (animation != null) {
                    customViewTest.layoutParams.height = animation.getAnimatedValue() as Int
                    customViewTest.requestLayout()
                }
            }

        })
        widthAnimator.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open -> {
                Log.d("icon", R.drawable.ic_menu_black_24dp.toString())
                Log.d("icon", item.icon.toString())
                if (customViewTest.visibility == View.GONE) {
                    FadeAnimation(customViewTest, true, 200)
                    item.setIcon(R.drawable.ic_close_black_24dp)
                } else {
                    FadeAnimation(customViewTest, false, 200)
                    item.setIcon(R.drawable.ic_menu_black_24dp)
                }
                return true
            }
            else ->
                return true
        }

    }

    private fun FadeAnimation(
        fadeTarget: View,
        shoudFadeIn: Boolean,
        duration: Long
    ) {
        val mAnimationSet = AnimatorSet()
        var start = 0f
        var end: Float
        var visibility = View.VISIBLE
        if (shoudFadeIn) {
            end = 1f
        } else {
            end = 0f
            start = 1f
            visibility = View.GONE
        }
        val fade = ObjectAnimator.ofFloat(
            fadeTarget,
            View.ALPHA,
            start,
            end
        )

        fadeTarget.visibility = View.VISIBLE


        fade.addListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                fadeTarget.visibility = visibility
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        fade.interpolator = LinearInterpolator()

        mAnimationSet.duration = duration
        mAnimationSet.play(fade)
        mAnimationSet.start()
    }
}
