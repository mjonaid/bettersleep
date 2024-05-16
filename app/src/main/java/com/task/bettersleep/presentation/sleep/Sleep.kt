package com.task.bettersleep.presentation.sleep

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentSleepBinding
import com.task.bettersleep.databinding.FragmentSoundsBinding
import com.task.bettersleep.presentation.sounds.SoundAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask

class Sleep : Fragment(R.layout.fragment_sleep) {
    private var _binding: FragmentSleepBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSleepBinding.bind(view)
        binding.btnSetAlarm.setOnClickListener{
            findNavController().navigate(R.id.action_sleep_to_setAlarm)
        }
        startUpdatingTime()
    }
    @SuppressLint("SimpleDateFormat")
    private fun startUpdatingTime() {
        val timer = Timer()
        val timeFormat = SimpleDateFormat("HH:mm")
        val amPmMarkerformat = SimpleDateFormat("a")
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                // Update the TextView with the current time
                val currentTime = timeFormat.format(Date())
                val currentTimeZone=amPmMarkerformat.format(Date())
                requireActivity().runOnUiThread {
                    binding.apply {
                        tvCurrentTime.text = currentTime
                        tvamPmMarker.text=currentTimeZone
                    }
                }
            }
        }, 0, 1000)
    }
}