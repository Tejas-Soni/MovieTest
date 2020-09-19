package com.soni.movietest

import android.app.Application
import com.soni.movietest.data.db.DatabaseBuilder
import com.soni.movietest.data.db.DatabaseHelperImpl
import com.soni.movietest.extensions.prefrence.MyDB

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDB.init(this)
    }
}