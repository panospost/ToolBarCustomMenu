package com.example.jetpackcomposetest

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.animation.PathInterpolatorCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

//    fun showAction(view: View) {
//        menu_button.visibility = View.GONE
//        close_button.visibility = View.VISIBLE
//        customViewTest.visibility = View.VISIBLE
//        original_parent_height = (view.parent as View).measuredHeight
//        animateMenuOptionsHeight(original_parent_height)
//
//    }
//
//    fun hideMenu(view: View) {
//        customViewTest.visibility = View.GONE
//        view.visibility = View.GONE
//        menu_button.visibility = View.VISIBLE
//        animateMenuOptionsHeight(original_parent_height)
//
//    }

    private fun animateMenuOptionsHeight(parentheight: Int) {
        val widthAnimator = ValueAnimator.ofInt(customViewTest.height, parentheight * 2/3)
        widthAnimator.duration = 400
        widthAnimator.interpolator =  PathInterpolatorCompat.create(0.215f, 0.610f, 0.355f, 1.000f)
        widthAnimator.addUpdateListener (object : ValueAnimator.AnimatorUpdateListener{
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
             R.id.open ->
             {
                 Log.d("icon", R.drawable.ic_menu_black_24dp.toString())
                 Log.d("icon", item.icon.toString())
                 if(customViewTest.visibility == View.GONE) {
                     customViewTest.visibility = View.VISIBLE
                     item.setIcon(R.drawable.ic_close_black_24dp)
                 } else {
                     customViewTest.visibility = View.GONE
                     item.setIcon(R.drawable.ic_menu_black_24dp)
                 }
                 return true
             }
            else ->
                return true
        }

    }
}
