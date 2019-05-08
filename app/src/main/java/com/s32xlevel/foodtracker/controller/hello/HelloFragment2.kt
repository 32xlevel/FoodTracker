package com.s32xlevel.foodtracker.controller.hello

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.Toast
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.controller.HelloFragment
import kotlinx.android.synthetic.main.fragment_hello.*
import kotlinx.android.synthetic.main.fragment_hello_2.*

class HelloFragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hello_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hello_next2.setOnClickListener {
            val p1 = helloEd_name.text.toString()
            val p2 = view.findViewById<RadioButton>(helloEd_gender.checkedRadioButtonId).text.toString()
            if (p1.isBlank() || p2.isBlank()) {
                showToast()
            } else {
                HelloFragment.name = p1
                HelloFragment.gender = p2
                changeFragment(HelloFragment3(), true)
            }
        }
    }

    private fun changeFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(activity!!.findViewById<FrameLayout>(R.id.frag_container_hello).id, fragment)
        if (isAddToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    private fun showToast() {
        Toast.makeText(context!!, "Заполните данные корректно", Toast.LENGTH_SHORT).show()
    }
}