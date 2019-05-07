package com.s32xlevel.foodtracker.repository

import android.content.Context
import com.s32xlevel.foodtracker.AuthorizedUser
import com.s32xlevel.foodtracker.model.Food
import org.jetbrains.anko.db.*
import java.util.*

class FoodRepositoryImpl(private val context: Context) : FoodRepository {

    private val Context.database: DBHelper
        get() = DBHelper.getInstance(context.applicationContext)

    // unique date + typeId
    override fun save(food: Food, userId: Int) {
        val db = context.database.writableDatabase
        if (userId == AuthorizedUser.id && food.id == null) {
            db.insert(
                DBHelper.FoodTable.TABLE_NAME,
                DBHelper.FoodTable.DATE to food.date,
                DBHelper.FoodTable.TYPE_ID to food.typeId,
                DBHelper.FoodTable.USER_ID to AuthorizedUser.id
            )
        }
    }

    override fun findAll(userId: Int, date: String): List<Food> = context.database.use {
//        val foodsDate = mutableListOf<Food>()
        val foodsDate = select(DBHelper.FoodTable.TABLE_NAME)
            .whereArgs("${DBHelper.FoodTable.DATE} = {date} AND ${DBHelper.FoodTable.USER_ID} = {userId}",
                "date" to date, "userId" to userId)
            .exec {
                /*parseList(object : MapRowParser<List<Food>> {
                    override fun parseRow(columns: Map<String, Any?>): List<Food> {
                        val id = columns.getValue(DBHelper.FoodTable.ID).toString().toInt()
                        val date = columns.getValue(DBHelper.FoodTable.DATE).toString() // yyyy-MM-dd
                        val typeId = columns.getValue(DBHelper.FoodTable.TYPE_ID).toString().toInt()
                        val userId = columns.getValue(DBHelper.FoodTable.ID).toString().toInt()
                        foodsDate.add(Food(id, date, typeId, userId))

                        return foodsDate
                    }
                })*/
                parseList(classParser<Food>())
            }
        if (foodsDate.isNullOrEmpty()) {
            Collections.emptyList<Food>()
        }
        foodsDate
    }

    override fun findByTypeId(typeId: Int, date: String): Food? = context.database.use {
        select(DBHelper.FoodTable.TABLE_NAME)
            .whereArgs("${DBHelper.FoodTable.DATE} = $date AND ${DBHelper.FoodTable.TYPE_ID} = $typeId")
            .exec { parseOpt(classParser()) }
    }
}