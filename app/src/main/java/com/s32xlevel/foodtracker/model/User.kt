package com.s32xlevel.foodtracker.model

import java.time.LocalTime

class User(var id: Int?,
           var name: String?,
           var gender: String?,
           var weight: Int?,
           var height: Int?,
           var rateWater: Int?,
           var foodNumber: Byte?,
           var startDayTime: LocalTime?,
           var endDayTime: LocalTime?)