package com.s32xlevel.foodtracker.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.WaterRepository
import com.s32xlevel.foodtracker.repository.WaterRepositoryImpl
import kotlinx.android.synthetic.main.recycler_view_water.view.*
import org.joda.time.DateTime

class RecyclerWaterAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerWaterAdapter.ViewHolder>() {
    interface Listener {
        fun onLongClick(id: Int?): Boolean
    }

    var listener: Listener? = null

    private val waterRepository: WaterRepository = WaterRepositoryImpl(context)

    private val dateTime = DateTime().toLocalDate().toString() // yyyy-MM-dd

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sourceText = itemView.findViewById<TextView>(R.id.water_recycler_source)
        val volumeText = itemView.findViewById<TextView>(R.id.water_recycler_volume)
        val id = itemView.findViewById<TextView>(R.id.water_recycler_id)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_water, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val waters = waterRepository.findAllByDateTime(AuthorizedUser.id, dateTime)
        val water = waters[position]

        viewHolder.id.text = water.id.toString()
        viewHolder.sourceText.text = water.source
        viewHolder.volumeText.text = water.volume.toString()

        viewHolder.itemView.setOnLongClickListener { listener?.onLongClick(water.id) ?: false }
    }

    override fun getItemCount(): Int {
        return waterRepository.findAllByDateTime(AuthorizedUser.id, dateTime).size
    }
}