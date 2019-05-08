package com.s32xlevel.foodtracker.controller


import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.model.User
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import com.s32xlevel.foodtracker.util.TimeUtil.Companion.convertTimeString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


class SettingsFragment : Fragment() {
    private var userRepository: UserRepository? = null

    var listenerChange: ChangeFragment? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerChange = context as ChangeFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userRepository = UserRepositoryImpl(context!!)
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        if (userRepository!!.findById(1) != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.title = "Настройки"
        } else {
            (activity as AppCompatActivity).supportActionBar?.title = "Food Tracker"
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillFields()
        timePickers()
        onClickButton()
    }

    private fun timePickers() {
        fill_start_date_time.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                TimePickerDialog(context!!, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    fill_start_date_time.setText(convertTimeString("$hourOfDay:$minute"))
                }, 7, 0, true).show()
            }
        }
        fill_end_date_time.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                TimePickerDialog(context!!, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    fill_end_date_time.setText(convertTimeString("$hourOfDay:$minute"))
                }, 23, 0, true).show()
            }
        }
    }

    private fun onClickButton() {
        button_start.setOnClickListener {
            val name = fill_name.text.toString()
            val gender = view!!.findViewById<RadioButton>(radio_group_gender.checkedRadioButtonId).text.toString()
            val weight = fill_weight.text.toString().toInt()
            val height = fill_height.text.toString().toInt()
            val startTime = fill_start_date_time.text.toString()
            val endTime = fill_end_date_time.text.toString()
            val foodCount = activity!!.findViewById<RadioButton>(fill_food_count.checkedRadioButtonId).text.toString().toByte()
            val rateWater = 0.03 * weight

            val user = User(id = null, name = name, gender = gender,
                weight = weight, height = height, startDayTime = startTime,
                endDayTime = endTime, foodNumber = foodCount, rateWater = rateWater)

            if (userRepository!!.findById(1) != null) {
                user.id = AuthorizedUser.id
            }

            if (name.isBlank() || weight < 10 || height < 60 || startTime.isBlank() || endTime.isBlank() || foodCount < 4 || foodCount > 5) {
                Toast.makeText(context!!, getString(R.string.settings_validation), Toast.LENGTH_SHORT).show()
            } else {
                bottomNavigationVisibility()
                userRepository!!.save(user)
                listenerChange!!.changeFromSetting()
            }
        }
    }

    private fun bottomNavigationVisibility() {
        val bottom = activity!!.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (bottom.visibility == View.INVISIBLE) {
            bottom.visibility = View.VISIBLE
        }
    }

    private fun fillFields() {
        val user = userRepository!!.findById(1)
        if (user != null) {
            fill_name.setText(user.name)
            fill_weight.setText(user.weight.toString())
            fill_height.setText(user.height.toString())
            fill_start_date_time.setText(user.startDayTime)
            fill_end_date_time.setText(user.endDayTime)
        }
    }
}
