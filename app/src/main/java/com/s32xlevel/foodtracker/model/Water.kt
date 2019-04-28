package com.s32xlevel.foodtracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = arrayOf(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = CASCADE)))
class Water(@PrimaryKey(autoGenerate = true) @NonNull var id: Int?,
            @NonNull @ColumnInfo(name = "date_time") var dateTime: LocalDateTime?,
            @NonNull var volume: Int?,
            @ColumnInfo(name = "user_id") @NonNull var userId: Int?)