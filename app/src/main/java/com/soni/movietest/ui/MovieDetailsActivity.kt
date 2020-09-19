package com.soni.movietest.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.soni.movietest.BuildConfig
import com.soni.movietest.R
import com.soni.movietest.base.BaseActivity
import com.soni.movietest.extensions.glide.loadImg
import com.soni.movietest.webservices.ApiClient
import com.soni.movietest.webservices.responsebean.MovieDetailsResponse
import com.soni.movietest.webservices.responsebean.Result
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_movies_details.*

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
class MovieDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        if (intent != null) {
            val data = Gson().fromJson(intent.getStringExtra("data"), Result::class.java)
            loadImg(ApiClient.MOVIE_URL+data.posterPath, ivMovie)
            Glide.with(this).load(ApiClient.MOVIE_URL+data.posterPath).transform(BlurTransformation(20,20)).into(ivBackground)
            getMovieDetailsApiCall(data.id)
        }
    }

    private fun getMovieDetailsApiCall(id: Int?) {
        callApiAndShowDialog(call = {
            ApiClient.getClient(application)
                .getMovieDetails(id!!)
        }, handleSuccess = {
            setResponse(it)
        }, handleGenric = {
            showSnackBar(it)
        })
    }

    private fun setResponse(it: MovieDetailsResponse) {
        tvMovieName.text = it.originalTitle
        tvMovieType.text = if (it.genres.isNullOrEmpty()) "" else it.genres[0].name
        tvOverview.text = it.overview
        tvRating.text = it.voteAverage.toString()
        tvYear.text = if (!it.releaseDate.isNullOrEmpty()) it.releaseDate.substring(0, 4) else ""
    }
}