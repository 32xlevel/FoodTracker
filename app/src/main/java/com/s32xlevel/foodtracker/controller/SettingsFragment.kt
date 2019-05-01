package com.s32xlevel.foodtracker.controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl


// TODO: bottom.visibility = OK
class SettingsFragment : Fragment() {
    private var userRepository: UserRepository? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userRepository = UserRepositoryImpl(context!!)
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        if (userRepository!!.findById(1) != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.title = "Настройки"
        } else {
            (activity as AppCompatActivity).supportActionBar?.title = "Food Tracker"
        }
        return rootView
    }


}
