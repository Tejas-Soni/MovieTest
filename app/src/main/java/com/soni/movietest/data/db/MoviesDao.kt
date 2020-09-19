package com.soni.movietest.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.soni.movietest.webservices.responsebean.Result

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
@Dao
interface MoviesDao {
    @Query("SELECT * FROM Result")
    suspend fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Result>)

    @Delete
    suspend fun delete(user: Result)
}