package com.soni.movietest.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.soni.movietest.R
import com.soni.movietest.base.BaseActivity
import com.soni.movietest.data.db.DatabaseBuilder
import com.soni.movietest.data.db.DatabaseHelper
import com.soni.movietest.data.db.DatabaseHelperImpl
import com.soni.movietest.data.keys.PrefKeys
import com.soni.movietest.extensions.clicklistener.forItemClickListenerDSL
import com.soni.movietest.extensions.prefrence.MyDB
import com.soni.movietest.ui.adapter.MovieListAdapter
import com.soni.movietest.webservices.ApiClient
import com.soni.movietest.webservices.responsebean.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private var movieListAdapter: MovieListAdapter? = null
    private var layoutManager: GridLayoutManager? = null
    private var dbHelper: DatabaseHelper? = null
    var movieData: List<Result> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecyclerView()


        configureDB()   // User this to get from Room Database
//      getListFromPrefrence() // Use This function if you want to get list from sharedprefrence


        movieListAdapter?.onItemClick(forItemClickListenerDSL { item, position, any ->
            startActivity(
                Intent(this, MovieDetailsActivity::class.java).putExtra(
                    "data",
                    Gson().toJson(item)
                )
            )
        })

    }


    /**
     * Get data from Room Database
     */
    private fun configureDB() {
        dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        launch {
            movieData = dbHelper?.getMovies()!!
            if (!movieData.isNullOrEmpty()) {
                movieListAdapter?.addData(ArrayList(movieData))
                getTopRateMoviesApiCall(false)
            } else {
                getTopRateMoviesApiCall(true)
            }
        }


    }

    /**
     * Use This function if you want to get list from sharedprefrence
     */
    private fun getListFromPrefrence() {
        val data = MyDB.getListObject(PrefKeys.moviesList, Result::class.java)
        if (!data.isNullOrEmpty()) {
            data as ArrayList<Result>
            movieListAdapter?.addData(data)
            getTopRateMoviesApiCall(false)
        } else {
            getTopRateMoviesApiCall(true)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvMoviesList.layoutManager = GridLayoutManager(this, 5)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvMoviesList.layoutManager = GridLayoutManager(this, 3)

        }
    }

    /**
     * Api call to get this movies list
     */
    private fun getTopRateMoviesApiCall(showDialog: Boolean) {
        callApiAndShowDialog(call = {
            ApiClient.getClient(application).getTopRatedMovies()
        }, handleSuccess = {
            movieListAdapter?.addData(it.results)
//            MyDB.putListObject(PrefKeys.moviesList, ArrayList(it.results)) // User this to store in sharedPrefrence
            launch { dbHelper?.insertAll(it.results!!) }
        }, handleGenric = {
            showSnackBar(it)
        }, handleNetwork = {
            showSnackBar(it)
        }, showDialg = showDialog)
    }

    private fun configureRecyclerView() {
        layoutManager =
            if (rvMoviesList.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this, 3)

            } else {
                GridLayoutManager(this, 5)
            }
        movieListAdapter = MovieListAdapter()
        rvMoviesList.layoutManager = layoutManager
        rvMoviesList.adapter = movieListAdapter
    }

}