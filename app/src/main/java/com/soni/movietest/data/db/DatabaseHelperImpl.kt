package com.soni.movietest.data.db

import androidx.lifecycle.LiveData
import com.soni.movietest.webservices.responsebean.Result
import com.soni.movietest.webservices.responsebean.TopRatedMoviesResponse

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */

class DatabaseHelperImpl(private val appDatabase: MoviesDataBase) : DatabaseHelper {

    override suspend fun getMovies(): List<Result> =appDatabase.moviesDao().getAll()

    override suspend fun insertAll(users: List<Result>) = appDatabase.moviesDao().insertAll(users)

}