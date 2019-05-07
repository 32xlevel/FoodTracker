package com.s32xlevel.foodtracker.controller


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.s32xlevel.foodtracker.R
import kotlinx.android.synthetic.main.fragment_help.*
import org.joda.time.DateTime


class HelpFragment : Fragment() {

    var listenerChange: ChangeFragment? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listenerChange = context as ChangeFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_help, container, false)
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonActivateNotify()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_settings -> listenerChange!!.changeToSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buttonActivateNotify() {
        activate_notification.setOnClickListener {
            activateNotifyWaterService()
            activateNotifyFoodService()
        }
    }

    private fun activateNotifyFoodService() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun activateNotifyWaterService() {
        val dateTime = DateTime()
        val intent = Intent(activity!!, NotificationReceiverWater::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity!!, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            dateTime.millis,
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
    }
}
