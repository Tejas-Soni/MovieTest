package com.soni.movietest.extensions.view

import android.view.View

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Sets an on click listener for a view, but ensures the action cannot be triggered more often than [coolDown] milliseconds.
 */
inline fun View.setOnClickListenerCooldown(
    coolDown: Long = 1000L,
    crossinline action: (view: View) -> Unit
) {
    setOnClickListener(object : View.OnClickListener {
        var lastTime = 0L
        override fun onClick(v: View) {
            val now = System.currentTimeMillis()
            if (now - lastTime > coolDown) {
                action(v)
                lastTime = now
            }
        }
    })
}
