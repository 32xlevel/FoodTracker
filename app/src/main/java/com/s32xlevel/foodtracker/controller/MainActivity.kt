package com.s32xlevel.foodtracker.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.s32xlevel.foodtracker.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // https://stackoverflow.com/questions/13086840/actionbar-up-navigation-with-fragments/40342939#40342939
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}
