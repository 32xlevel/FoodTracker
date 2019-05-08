package com.s32xlevel.foodtracker.controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.controller.hello.*
import com.s32xlevel.foodtracker.model.User
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hello.*
import kotlinx.android.synthetic.main.fragment_hello_1.*
import kotlinx.android.synthetic.main.fragment_hello_2.*
import kotlinx.android.synthetic.main.fragment_hello_3.*
import kotlinx.android.synthetic.main.fragment_hello_4.*
import kotlinx.android.synthetic.main.fragment_hello_5.*
import kotlinx.android.synthetic.main.fragment_settings.*

class HelloFragment : Fragment() {

    var userRepository: UserRepository? = null
    var name: String? = null
    var gender: String? = null
    var weight: Int? = null
    var height: Int? = null
    var startTime: String? = null
    var endTime: String? = null
    var foodCount: Byte? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userRepository = UserRepositoryImpl(context!!)
        return inflater.inflate(R.layout.fragment_hello, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeFragment(HelloFragment1(), false)
        /*activity!!.findViewById<View>(R.id.hello_next1).setOnClickListener {
            changeFragment(HelloFragment2(), true)
        }
        activity!!.findViewById<View>(R.id.hello_next2).setOnClickListener {
            val p1 = activity!!.findViewById<TextView>(R.id.helloEd_name).text.toString()
            val p2 = activity!!.findViewById<RadioButton>(helloEd_gender.checkedRadioButtonId).text.toString()
            if (p1.isBlank() || p2.isBlank()) {
                showToast()
            } else {
                name = p1
                gender = p2
                changeFragment(HelloFragment3(), true)
            }
        }
        activity!!.findViewById<View>(R.id.hello_next3).setOnClickListener {
            val p1 = activity!!.findViewById<TextView>(R.id.helloEd_weight).text.toString().toInt()
            val p2 = activity!!.findViewById<TextView>(R.id.helloEd_height).text.toString().toInt()
            if (p1 <= 30 || p2 <= 50) {
                showToast()
            } else {
                weight = p1
                height = p2
                changeFragment(HelloFragment4(), true)
            }
        }
        activity!!.findViewById<View>(R.id.hello_next4).setOnClickListener {
            startTime = activity!!.findViewById<TextView>(R.id.fillEd_start_date_time).text.toString()
            endTime = activity!!.findViewById<TextView>(R.id.fillEd_end_date_time).text.toString()
            foodCount = activity!!.findViewById<RadioButton>(helloEd_count.checkedRadioButtonId).text.toString().toByte()
            changeFragment(HelloFragment5(), true)
        }
        activity!!.findViewById<View>(R.id.hello_next5).setOnClickListener {
            userRepository!!.save(
                User(
                    id = null,
                    name = name,
                    gender = gender,
                    weight = weight,
                    height = height,
                    startDayTime = startTime,
                    endDayTime = endTime,
                    foodNumber = foodCount,
                    rateWater = 0.03 * weight!!
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
