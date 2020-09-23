//package com.soni.infusiontest.ui
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.gson.Gson
//import com.soni.infusiontest.R
//import com.soni.infusiontest.base.BaseActivity
//import com.soni.infusiontest.extensions.clicklistener.forItemClickListenerDSL
//import com.soni.infusiontest.extensions.view.hide
//import com.soni.infusiontest.extensions.view.show
//import com.soni.infusiontest.ui.adapter.FeedAdapter
//import com.soni.infusiontest.webservices.ApiClient
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.custom_toolbar.view.*
//
//class MainActivity : BaseActivity() {
//    private var feedAdapter: FeedAdapter? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        feedAdapter = FeedAdapter()
//        rvList.adapter = feedAdapter
//        toolBar.toolbarTitle.text = getString(R.string.lbl_dashboard)
//        rvList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
//        setScrollListener()
//        setRefreshListener()
//
//        feedAdapter?.onItemClick(forItemClickListenerDSL { item, position, any ->
//            startActivity(
//                Intent(this@MainActivity, DetailActivity::class.java).putExtra(
//                    "data",
//                    Gson().toJson(item)
//                )
//            )
//        })
//        getListApiCall()
//    }
//
//    private fun setRefreshListener() {
//        srlMain.setOnRefreshListener {
//            feedAdapter?.clear()
//            PAGENUM = 1
//            isLoading = false
//            STOP = false
//            getListApiCall()
//        }
//
//    }
//
//    private fun setScrollListener() {
//        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (!STOP && !isLoading) {
//                        isLoading = true
//                        PAGENUM += 1
//                        getListApiCall()
//                    }
//                }
//            }
//        })
//    }
//
//    /**
//     * API call of the user list
//     */
//    private fun getListApiCall() {
//        SHOW = PAGENUM == 1
//        if (!SHOW) {
//            pbMain.show()
//        }
//        callApiAndShowDialog(call = {
//            ApiClient.getClient(application).getFeed(PAGENUM)
//        }, handleSuccess = {
//            MAX_PAGE = it.totalPages!!
//            srlMain.isRefreshing = false
//            if (PAGENUM == 1) {
//                feedAdapter?.addData(it.data)
//            } else {
//                feedAdapter?.addMore(it.data)
//                isLoading = false
//                pbMain.hide()
//                if (PAGENUM == MAX_PAGE) STOP = true
//            }
//        }, handleGenric = {
//            showSnackBar(it)
//        }, showDialg = SHOW)
//    }
//
//    companion object {
//        private var PAGENUM = 1  //Page Number from to start
//        private var isLoading = false // Check if page is loading
//        private var STOP = false //Stop if we reach the page limit
//        private var SHOW = false //To show bottom loader or main
//        private var MAX_PAGE = 0 // Max page available count
//    }
//}