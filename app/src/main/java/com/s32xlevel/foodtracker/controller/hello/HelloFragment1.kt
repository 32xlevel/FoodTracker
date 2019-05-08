package com.s32xlevel.foodtracker.controller.hello

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.controller.HelloFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hello.*
import kotlinx.android.synthetic.main.fragment_hello_1.*

class HelloFragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hello_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*hello_next1.setOnClickListener {
            changeFragment(HelloFragment2(), true)
        }*/
    }

    private fun changeFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(activity!!.findViewById<FrameLayout>(R.id.frag_container_hello).id, fragment)
        if (isAddToBackStack)
            transaction.addToBackStack(null)
        transaction.commit()
    }
}