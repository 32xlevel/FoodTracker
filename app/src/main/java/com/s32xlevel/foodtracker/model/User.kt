package com.s32xlevel.foodtracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity
class User(@PrimaryKey(autoGenerate = true) @NonNull var id: Int?,
           @NonNull var name: String?,
           @NonNull var gender: String?,
           @NonNull var weight: Int?,
           @NonNull var height: Int?,
           @NonNull @ColumnInfo(name = "rate_water") var rateWater: Int?, /* норма воды: будет расчитываться! */
           @NonNull @ColumnInfo(name = "food_number") var foodNumber: Byte? /* количество приемов пищи (4 или 5) */,
           @NonNull @ColumnInfo(name = "start_day_time") var startDayTime: LocalTime?, /* время подъема */
           @NonNull @ColumnInfo(name = "end_day_time") var endDayTime: LocalTime?)