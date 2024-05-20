package com.task.bettersleep.presentation.sleep

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentSetAlarmBinding
import java.util.Calendar

class SetAlarm : Fragment(R.layout.fragment_set_alarm) {
    private var _binding: FragmentSetAlarmBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSetAlarmBinding.bind(view)
        // Initialize the MaterialTimePicker
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(23)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        binding.switchOnOf.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Optionally handle switch on state
            } else {
                // Optionally handle switch off state
            }
        }

        // Show the MaterialTimePicker when TextView is clicked
        binding.tvAlarmTime.setOnClickListener {
            timePicker.show(requireActivity().supportFragmentManager, "TIME_PICKER")
        }
        // Set selected time to TextView when a time is selected in the picker
        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.tvAlarmTime.text = selectedTime
            setAlarm(selectedHour, selectedMinute)
        }
        timePicker.addOnCancelListener {
            // Handle cancellation if needed
        }
    }
    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        val context = requireContext().applicationContext
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
