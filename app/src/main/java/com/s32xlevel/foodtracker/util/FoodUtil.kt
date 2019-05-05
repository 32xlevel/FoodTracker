package com.s32xlevel.foodtracker.util

import com.s32xlevel.foodtracker.model.FoodType
import com.s32xlevel.foodtracker.model.User
import com.s32xlevel.foodtracker.util.TimeUtil.Companion.convertStringTimeToDateTime

class FoodUtil {
    companion object {
        fun getFoodsForUser(user: User): Array<FoodType>? {
            if (user.foodNumber == 4.toByte()) {
                return arrayOf(
                    FoodType(id = 1, typeName = "Первый завтрак", timeReception = convertStringTimeToDateTime(user.startDayTime!!)),
                    FoodType(id = 2, typeName = "Второй завтрак", timeReception = convertStringTimeToDateTime("11:00:00")),
                    FoodType(id = 3, typeName = "Обед", timeReception = convertStringTimeToDateTime("14:30:00")),
                    FoodType(id = 4, typeName = "Ужин", timeReception = convertStringTimeToDateTime("19:30:00")))
            } else if (user.foodNumber == 5.toByte()) {
                return arrayOf(
                    FoodType(id = 1, typeName = "Первый завтрак", timeReception = convertStringTimeToDateTime(user.startDayTime!!)),
                    FoodType(id = 2, typeName = "Второй завтрак", timeReception = convertStringTimeToDateTime("11:00:00")),
                    FoodType(id = 3, typeName = "Обед", timeReception = convertStringTimeToDateTime("14:30:00")),
                    FoodType(id = 5, typeName = "Первый ужин", timeReception = convertStringTimeToDateTime("19:00:00")),
                    FoodType(id = 6, typeName = "Второй ужин", timeReception = convertStringTimeToDateTime("20:30:00")))
            }
            return null
        }
    }
}