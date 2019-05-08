package com.s32xlevel.foodtracker.util

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.model.Food
import com.s32xlevel.foodtracker.repository.FoodRepositoryImpl
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import org.joda.time.DateTime

class RecyclerFoodAdapter(val context: Context) : RecyclerView.Adapter<RecyclerFoodAdapter.ViewHolder>() {

    companion object {
        private val map = mutableMapOf<Int, Boolean>()
    }

    interface Listener {
        fun onClick(position: Int): Boolean?
    }

    var listener: Listener? = null

    private val user = UserRepositoryImpl(context).findById(1)
    private val foodRepository = FoodRepositoryImpl(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var typeName = itemView.findViewById<TextView>(R.id.food_recycler_typeName)
        var time = itemView.findViewById<TextView>(R.id.food_recycler_time)
        var check = itemView.findViewById<CheckBox>(R.id.food_check)
        var checkTime = itemView.findViewById<CheckBox>(R.id.food_check_time)
        var typeFoodId = itemView.findViewById<TextView>(R.id.type_food_id)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_food, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dishesType = FoodUtil.getFoodsForUser(user!!)
        val dishType = dishesType!![position]

        val addedTime = dishType.timeReception!!.plusMinutes(30)
        val time = "${dishType.timeReception!!.toLocalTime().toString("HH:mm")} - ${addedTime.toLocalTime().toString("HH:mm")}"
        viewHolder.typeFoodId.text = "${dishType.id}"

        val date = DateTime().toLocalDate().toString()
        val foods = foodRepository.findAll(1, date)

        viewHolder.typeName.text = dishType.typeName
        viewHolder.time.text = time

        viewHolder.check.isChecked = !(foods.isEmpty() || !checkTypeInFoods(foods, viewHolder.typeFoodId.text.toString().toInt()))
        if (map[position] != null) {
            viewHolder.checkTime.isChecked = map[position]!!
        }

        if (viewHolder.check.isChecked) {
            if (viewHolder.checkTime.isChecked) {
                viewHolder.typeName.setTextColor(Color.RED)
                viewHolder.time.setTextColor(Color.RED)
            }
            if (!viewHolder.checkTime.isChecked) {
                viewHolder.typeName.setTextColor(Color.GREEN)
                viewHolder.time.setTextColor(Color.GREEN)
            }
        }

        viewHolder.itemView.setOnClickListener {
            val bol = listener!!.onClick(position)
            if (bol != null) {
                if (!checkTypeInFoods(foods, viewHolder.typeFoodId.text.toString().toInt())) {
                    map[position] = bol
                    foodRepository.save(Food(null, date, viewHolder.typeFoodId.text.toString().toInt(), 1), 1)
                    notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Вы уже отметили этот прием пищи", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, context.getString(R.string.time_no_come), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return FoodUtil.getFoodsForUser(user!!)!!.size
    }

    private fun checkTypeInFoods(foods: List<Food>, typeId: Int): Boolean {
        for (food in foods) {
            if (food.typeId == typeId)
                return true
        }
        return false
    }
}