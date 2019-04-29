package com.s32xlevel.foodtracker.controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.util.App


class SettingsFragment : Fragment() {
    private val db = App.getInstance().database
    private val userRepository = db?.userRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar_top)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        if (userRepository!!.findAll().isNotEmpty()) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
        return rootView
    }


}
