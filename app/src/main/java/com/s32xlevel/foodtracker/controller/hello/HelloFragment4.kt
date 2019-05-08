package com.s32xlevel.foodtracker.controller.hello

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.controller.HelloFragment
import kotlinx.android.synthetic.main.fragment_hello.*
import kotlinx.android.synthetic.main.fragment_hello_4.*

class HelloFragment4 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hello_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*hello_next4.setOnClickListener {
            HelloFragment.startTime = fillEd_start_date_time.text.toString()
            HelloFragment.endTime = fillEd_end_date_time.text.toString()
            HelloFragment.foodCount = view.findViewById<RadioButton>(helloEd_count.checkedRadioButtonId).text.toString().toByte()
            changeFragment(HelloFragment5(), true)
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