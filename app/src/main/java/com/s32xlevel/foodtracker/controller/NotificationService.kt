package com.s32xlevel.foodtracker.controller

import android.app.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.s32xlevel.foodtracker.R

class NotificationService : IntentService("NotifyService") {

    override fun onHandleIntent(intent: Intent?) {
//        showWaterNotify()
    }

    private fun showWaterNotify() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder: NotificationCompat.Builder? = null

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

        notificationManager.notify(5453, builder.build())
    }

}
