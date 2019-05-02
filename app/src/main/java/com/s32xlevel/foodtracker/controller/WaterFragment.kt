package com.s32xlevel.foodtracker.controller

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.WaterRepository
import com.s32xlevel.foodtracker.repository.WaterRepositoryImpl
import com.s32xlevel.foodtracker.util.RecyclerWaterAdapter
import kotlinx.android.synthetic.main.fragment_water.*
import org.joda.time.DateTime

class WaterFragment : Fragment() {

    var listenerChange: ChangeFragment? = null
    private var waterRepository: WaterRepository? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerChange = context as ChangeFragment
        waterRepository = WaterRepositoryImpl(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_water, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillCurrentDate()
        recyclerWork()
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

    private fun fillCurrentDate() {
        val dateTime = DateTime()
        current_date.text = "Сегодня ${dateTime.dayOfMonth} ${dateTime.monthOfYear().asText}"
    }

    private fun recyclerWork() {
        val layoutManager = LinearLayoutManager(context!!)
        water_recycler_view.layoutManager = layoutManager

        val recyclerAdapter = RecyclerWaterAdapter(context!!)
        recyclerAdapter.listener = object : RecyclerWaterAdapter.Listener {
            override fun onLongClick(id: Int): Boolean {
                val alert = AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.water_delete))
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.dialog_yes)) { dialog, which ->
                        waterRepository!!.delete(id = id, userId = AuthorizedUser.id)
                        recyclerAdapter.notifyDataSetChanged()
                    }
                    .setNegativeButton(R.string.dialog_no) { dialog, which -> dialog.cancel() }
                    .create()

                alert.show()
                return true
            }
        }

        water_recycler_view.adapter = recyclerAdapter
        water_recycler_view.addItemDecoration(DividerItemDecoration(context!!, layoutManager.orientation))
    }
}
