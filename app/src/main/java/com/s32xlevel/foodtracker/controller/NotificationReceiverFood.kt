package com.s32xlevel.foodtracker.controller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.s32xlevel.foodtracker.R

class NotificationReceiverFood: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val repeatingIntent = Intent(context, MainActivity::class.java)
        repeatingIntent.putExtra("FRAGMENT", "Food")
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder: NotificationCompat.Builder?

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("FoodId", "Food", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = NotificationCompat.Builder(context, notificationChannel.id)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder = builder.setSmallIcon(R.drawable.silverware)
            .setContentTitle("Прием пищи")
            .setAutoCancel(true)
            .addAction(R.drawable.silverware, "Отметить", pendingIntent)


        notificationManager.notify(5453, builder.build())

    }
}
