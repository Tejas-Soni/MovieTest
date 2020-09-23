package com.soni.movietest.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.soni.movietest.R
import com.soni.movietest.base.BaseViewModel
import com.soni.movietest.base.BaseViewModelFragment
import com.soni.movietest.ui.adapter.BannerAdapter
import java.util.*

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
class EmptyFragment : BaseViewModelFragment<BaseViewModel>(R.layout.activity_main) {
    private val DELAY_MS: Long = 2000
    private val PERIOD_MS: Long = 2000
    var timer: Timer? = null
    private var handler: Handler? = null
    var bannerAdapter: BannerAdapter? = null
    override fun buildViewModel(): BaseViewModel {
        return ViewModelProviders.of(this).get(BaseViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({ automateViewPagerSwiping() }, PERIOD_MS)
    }

    private fun automateViewPagerSwiping() {
        if (handler == null) {
            handler = Handler()
        }
//        val update = Runnable {
//            if (context != null) {
//                if (vpImages != null && bannerAdapter != null) {
//                    if (vpImages.currentItem == bannerAdapter!!.count - 1) {
//                        vpImages.currentItem = 0
//                    } else {
//                        vpImages.setCurrentItem(vpImages.currentItem + 1, true)
//                    }
//                }
//            }
//        }
        if (timer == null) {
            timer = Timer()
        }
        timer!!.schedule(
            object : TimerTask() {
                override fun run() {
//                    handler!!.post(update)
                }
            }, DELAY_MS, PERIOD_MS
        )
    }


    override fun onAttach(context: Context) {
        if (timer == null) {
            timer = Timer()
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        timer!!.cancel()
        timer = null
        if (handler != null) {
            handler!!.removeCallbacksAndMessages(null)
        }
        super.onDetach()
    }
}