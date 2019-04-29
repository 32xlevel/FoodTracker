package com.s32xlevel.foodtracker.util

import android.app.Application
import androidx.room.Room



class App: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        private var database: DBHelper? = null

        @Synchronized
        fun getInstance() = instance ?: App()

        fun getDatabase() = database
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, DBHelper::class.java, "foodtracker.db").build()
    }
}