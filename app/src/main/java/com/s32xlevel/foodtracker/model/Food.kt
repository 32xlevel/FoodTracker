package com.s32xlevel.foodtracker.model

import androidx.annotation.NonNull
import androidx.room.*
import java.time.LocalDateTime

@Entity(tableName = "foods", foreignKeys = arrayOf(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE)))
class Food(@PrimaryKey(autoGenerate = true) @NonNull var id: Int?,
           @ColumnInfo(name = "date_time") @NonNull var dateTime: LocalDateTime?,
           @Embedded @NonNull var type: FoodType?,
           @ColumnInfo(name = "user_id") @NonNull var userId: Int?)