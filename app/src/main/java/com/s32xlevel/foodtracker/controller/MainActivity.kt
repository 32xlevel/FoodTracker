package com.s32xlevel.foodtracker.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity(), ChangeFragment {
    private var userRepository: UserRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        JodaTimeAndroid.init(this)
        userRepository = UserRepositoryImpl(this)

        if (userRepository!!.findById(1) == null) {
            bottom_navigation.visibility = View.INVISIBLE
            changeFragment(SettingsFragment() as Fragment, false)
        } else {
            changeFragment(FoodFragment() as Fragment, false)
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
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

    override fun changeFromSetting() {
        changeFragment(WaterFragment(), true)
    }

    override fun changeToAddWater() {
        changeFragment(AddWaterFragment(), true)
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
