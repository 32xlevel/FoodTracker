package com.s32xlevel.foodtracker.model

import java.sql.Time
import java.time.LocalTime

class User(var id: Int?,
           var name: String?,
           var gender: String?,
           var weight: Int?,
           var height: Int?,
           var rateWater: Int?,
           var foodNumber: Byte?,
           var startDayTime: Time?,
           var endDayTime: Time?)