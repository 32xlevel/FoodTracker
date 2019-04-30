package com.s32xlevel.foodtracker.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.util.App
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChangeFragment {

    private val db = App.getInstance().database
    private val userRepository = db?.userRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (userRepository!!.findAll().isEmpty()) {
            changeFragment(SettingsFragment(), false)
        } else {
            changeFragment(FoodFragment(), false)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_food -> changeFragment(FoodFragment(), true)
                R.id.action_water -> changeFragment(WaterFragment(), true)
                R.id.action_help -> changeFragment(HelpFragment(), true)
            }
            true
        }
    }

    override fun changeToSettings() {
        changeFragment(SettingsFragment(), true)
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

    private fun changeFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frag_container.id, fragment)
        if (isAddToBackStack)
            transaction.addToBackStack(null)
        transaction.commit()
    }
}
