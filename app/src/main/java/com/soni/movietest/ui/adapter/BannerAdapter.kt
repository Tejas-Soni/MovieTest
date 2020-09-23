package com.soni.movietest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.soni.movietest.R

class BannerAdapter() : PagerAdapter() {

    var onBoardItems: ArrayList<Int> = ArrayList();


    override fun instantiateItem(container: ViewGroup, position: Int): View {
//        val itemView: View =
////            LayoutInflater.from(container.context).inflate(R.layout.item_banner, container, false)
////        itemView.ivBanner.setImageResource(onBoardItems[position])
//        container.addView(itemView)
//        return null
        TODO("")
    }

    override fun getCount(): Int {
        return onBoardItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    fun addData(category: ArrayList<Int>) {
        if (onBoardItems.size > 0) {
            onBoardItems.clear()
        }
        onBoardItems = category;
        notifyDataSetChanged()
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView?)
    }


}