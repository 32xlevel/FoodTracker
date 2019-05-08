package com.s32xlevel.foodtracker.controller.hello


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.controller.HelloFragment
import com.s32xlevel.foodtracker.controller.HelpFragment
import com.s32xlevel.foodtracker.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hello.*
import kotlinx.android.synthetic.main.fragment_hello_5.*

class HelloFragment5 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hello_5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*hello_next5.setOnClickListener {
            HelloFragment.userRepository!!.save(
                User(
                    id = null,
                    name = HelloFragment.name,
                    gender = HelloFragment.gender,
                    weight = HelloFragment.weight,
                    height = HelloFragment.height,
                    startDayTime = HelloFragment.startTime,
                    endDayTime = HelloFragment.endTime,
                    foodNumber = HelloFragment.foodCount,
                    rateWater = 0.03 * HelloFragment.weight!!
                )
            )
            changeFragment(HelpFragment(), false)
            activity!!.bottom_navigation.visibility = View.VISIBLE
        }*/
    }

    private fun changeFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(frag_container_hello.id, fragment)
        if (isAddToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun showToast() {
        Toast.makeText(context!!, "Заполните данные корректно", Toast.LENGTH_SHORT).show()
    }

}
