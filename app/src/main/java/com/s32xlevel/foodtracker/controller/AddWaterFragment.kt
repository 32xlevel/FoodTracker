package com.s32xlevel.foodtracker.controller


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.RadioButton
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.model.Water
import com.s32xlevel.foodtracker.repository.WaterRepository
import com.s32xlevel.foodtracker.repository.WaterRepositoryImpl
import kotlinx.android.synthetic.main.fragment_add_water.*
import kotlinx.android.synthetic.main.fragment_settings.*
import org.joda.time.DateTime


class AddWaterFragment : Fragment() {

    var listenerChange: ChangeFragment? = null
    var waterRepository: WaterRepository? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerChange = context as ChangeFragment
        waterRepository = WaterRepositoryImpl(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_water, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Добавить воду"
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkMyButtonSource()
        onClickButton()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_settings -> listenerChange!!.changeToSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkMyButtonSource() {
        val button = view!!.findViewById<RadioButton>(radio_group_add_water.checkedRadioButtonId)
        if (button.id == radio_button_mysource.id) {
            fill_water_my_source.visibility = View.VISIBLE
            fill_water_my_volume.visibility = View.VISIBLE
        } else {
            fill_water_my_source.visibility = View.INVISIBLE
            fill_water_my_volume.visibility = View.INVISIBLE
        }
    }

    private fun onClickButton() {
        button_add_water.setOnClickListener {
            val button = view!!.findViewById<RadioButton>(radio_group_add_water.checkedRadioButtonId)
            when (button.id) {
                radio_button_cup.id -> {
                    addWater("Стакан", 200)
                }
                radio_button_mug.id -> {
                    addWater("Кружка", 300)
                }
                radio_button_mysource.id -> {
                    addWater(fill_water_my_source.text.toString(), fill_water_my_volume.text.toString().toInt())
                }
            }
        }
    }

    private fun addWater(source: String, volume: Int) {
        val water = Water(id = null, dateTime = DateTime().toString("yyyy-MM-ddTHH:mm"),
            volume = volume, source = source, userId = AuthorizedUser.id)
        waterRepository!!.create(water, AuthorizedUser.id)
    }


}
