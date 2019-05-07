package com.s32xlevel.foodtracker.controller

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.s32xlevel.foodtracker.R
import com.s32xlevel.foodtracker.repository.UserRepositoryImpl
import com.s32xlevel.foodtracker.util.TimeUtil
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


// TODO: FIX REPEATING: MainActivity - if getIntent != null (!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
class NotificationReceiverWater: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val repeatingIntent = Intent(context, MainActivity::class.java)
        repeatingIntent.putExtra("FRAGMENT", "Water")
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder: NotificationCompat.Builder?

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("WaterId", "Water", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = NotificationCompat.Builder(context, notificationChannel.id)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder = builder.setSmallIcon(R.drawable.cup300)
            .setContentTitle(context.getString(R.string.notify_water_title))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.cup300, "Отметить", pendingIntent)


        val userRepository = UserRepositoryImpl(context)
        val user = userRepository.findById(1)!!
        val currentTime = DateTime().toLocalTime()
        val startTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(user.startDayTime).toLocalTime()
        val endTime = DateTimeFormat.forPattern("HH:mm:ss").parseDateTime(user.endDayTime).toLocalTime()

        if (TimeUtil.isBetween(currentTime, startTime, endTime)) {
            notificationManager.notify(5453, builder.build())
        }
    }
}
