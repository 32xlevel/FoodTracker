package com.s32xlevel.foodtracker.controller

import android.app.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.UserRepository
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class NotificationService : IntentService("NotifyService") {

    private var userRepository: UserRepository? = null

    override fun onHandleIntent(intent: Intent?) {
        userRepository = UserRepositoryImpl(applicationContext)
        showWaterNotify()
    }

    @Suppress("DEPRECATION")
    private fun showWaterNotify() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder: NotificationCompat.Builder?

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("WaterId", "Water", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = NotificationCompat.Builder(applicationContext, notificationChannel.id)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        //Click --> Activity
        val actionIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder = builder.setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentTitle(getString(R.string.notify_water_title))
            .setDefaults(Notification.DEFAULT_ALL)
            .setAutoCancel(true)
            .addAction(R.drawable.cup300, "Отметить", pendingIntent)


        builder.setContentIntent(pendingIntent)

        val user = userRepository!!.findById(1)!!
        val startTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(user.startDayTime).toLocalTime()
        val endTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(user.endDayTime).toLocalTime()
        val dateTime = DateTime()

        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, dateTime.millis, AlarmManager.INTERVAL_HOUR, pendingIntent)


        if (isBetween(dateTime.toLocalTime(), startTime, endTime)) {
            notificationManager.notify(5453, builder.build())
        }
    }

    private fun <T : Comparable<T>> isBetween(value: T, start: T?, end: T?): Boolean {
        return (start == null || value >= start) && (end == null || value <= end)
    }
}
