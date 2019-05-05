package com.s32xlevel.foodtracker.controller


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.util.RecyclerFoodAdapter
import kotlinx.android.synthetic.main.fragment_food.*


class FoodFragment : Fragment() {

    var listenerChange: ChangeFragment? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerChange = context as ChangeFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_food, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun recyclerWork() {
        val layoutManager = LinearLayoutManager(context!!)
        food_recycler_id.layoutManager = layoutManager

        val recyclerAdapter = RecyclerFoodAdapter(context!!)
        recyclerAdapter.listener = object : RecyclerFoodAdapter.Listener {
            override fun onClick() {
                // TODO realize
            }
        }

        food_recycler_id.adapter = recyclerAdapter
        food_recycler_id.addItemDecoration(DividerItemDecoration(context!!, layoutManager.orientation))
    }
}
