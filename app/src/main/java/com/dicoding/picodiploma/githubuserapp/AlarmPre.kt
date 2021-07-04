package com.dicoding.picodiploma.githubuserapp

import android.content.Context
import com.dicoding.picodiploma.githubuserapp.data.model.AlarmModel

class AlarmPre(context: Context) {

    companion object {
        const val ALARM_PRE = "alarm_pre"
        private const val ALARM = "check_alarm"
    }

    private val model = AlarmModel()

    private val pref = context.getSharedPreferences(ALARM_PRE, Context.MODE_PRIVATE)

    fun setAlarm(alarmModel: AlarmModel) {
        val changePref = pref.edit()
        changePref.putBoolean(ALARM, alarmModel.checkAlarm)
        changePref.apply()
    }

    fun getAlarm(): AlarmModel {
        model.checkAlarm = pref.getBoolean(ALARM, false)
        return model
    }

}