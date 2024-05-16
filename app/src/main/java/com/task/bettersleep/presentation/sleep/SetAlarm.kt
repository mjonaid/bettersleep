package com.task.bettersleep.presentation.sleep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentSetAlarmBinding

class SetAlarm : Fragment(R.layout.fragment_set_alarm) {
    private var _binding: FragmentSetAlarmBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSetAlarmBinding.bind(view)

        // Initialize the MaterialTimePicker
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        // Show the MaterialTimePicker when TextInputLayout is clicked
        binding.tvAlarmTime.setOnClickListener {
            timePicker.show(requireActivity().supportFragmentManager, "TIME_PICKER")
        }

        // Set selected time to TextInputEditText when a time is selected in the picker
        timePicker.addOnPositiveButtonClickListener {
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute
            val selectedTime = String.format("$selectedHour:$selectedMinute")
            binding.tvAlarmTime.text = selectedTime
        }
        timePicker.addOnCancelListener{
            val tvalaramtime= binding.tvAlarmTime.text
            binding.tvAlarmTime.text=tvalaramtime
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
