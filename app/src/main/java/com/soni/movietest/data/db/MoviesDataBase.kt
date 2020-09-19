package com.soni.movietest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soni.movietest.webservices.responsebean.Result

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
@Database(entities = [Result::class], version = 1,exportSchema = false)
abstract class MoviesDataBase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
}