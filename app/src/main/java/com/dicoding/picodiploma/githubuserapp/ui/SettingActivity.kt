package com.dicoding.picodiploma.githubuserapp.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.githubuserapp.AlarmPre
import com.dicoding.picodiploma.githubuserapp.broadcast.AlarmBroadcastReceiver
import com.dicoding.picodiploma.githubuserapp.data.model.AlarmModel
import com.dicoding.picodiploma.githubuserapp.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var alarmBroadcastReceiver: AlarmBroadcastReceiver

    companion object {
        const val TYPE_REPEATING = "RepeatingAlarm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alarmPre = AlarmPre(this)
        binding.switchAlarm.isChecked = alarmPre.getAlarm().checkAlarm

        alarmBroadcastReceiver = AlarmBroadcastReceiver()
        binding.switchAlarm.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                true.setAlarm()
                alarmBroadcastReceiver.setRepeatingAlarm(this, "09:00", "Dicoding Github Alarm")
            } else {
                false.setAlarm()
                alarmBroadcastReceiver.cancelAlarm(this)
            }
        }

        editLanguage()
    }

    private fun Boolean.setAlarm() {
        val alarmPre = AlarmPre(this@SettingActivity)
        val alarmModel = AlarmModel()
        alarmModel.checkAlarm = this
        alarmPre.setAlarm(alarmModel)
    }

    private fun editLanguage() {
        binding.btnLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }
}