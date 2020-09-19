package com.soni.movietest.data.db

import android.content.Context
import androidx.room.Room

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
object DatabaseBuilder {

    private var INSTANCE: MoviesDataBase? = null

    fun getInstance(context: Context): MoviesDataBase {
        if (INSTANCE == null) {
            synchronized(MoviesDataBase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MoviesDataBase::class.java,
            "movies_database"
        ).build()

}