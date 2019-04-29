package com.s32xlevel.foodtracker.util

import android.app.Application
import androidx.room.Room



class App: Application() {

    var database: DBHelper? = null

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        @Synchronized
        fun getInstance() = instance ?: App()
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, DBHelper::class.java, "foodtracker.db").build()
    }
}