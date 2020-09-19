package com.soni.movietest.data.db

import androidx.lifecycle.LiveData
import com.soni.movietest.webservices.responsebean.Result
import com.soni.movietest.webservices.responsebean.TopRatedMoviesResponse

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
interface DatabaseHelper {

    suspend fun getMovies(): List<Result>

    suspend fun insertAll(users: List<Result>)

}