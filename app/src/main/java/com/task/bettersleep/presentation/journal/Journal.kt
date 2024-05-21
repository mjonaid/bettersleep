package com.task.bettersleep.presentation.journal

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentJournalBinding
import com.task.bettersleep.databinding.FragmentSoundsBinding
import com.task.bettersleep.presentation.sounds.SoundAdapter
import com.task.bettersleep.presentation.sounds.SoundsViewModel

class Journal : Fragment(R.layout.fragment_journal) {
    private var _binding: FragmentJournalBinding? = null
    private lateinit var sharedPreferences: SharedPreferences
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding =FragmentJournalBinding.bind(view)
        sharedPreferences= requireActivity().getSharedPreferences("betterSleep", Context.MODE_PRIVATE)
        val fajrminute = sharedPreferences.getString("Alarm_MINUTE", "0")
        val fajrhour = sharedPreferences.getString("Alarm_HOUR", "0")
        binding.asleeptv.text= fajrhour
        binding.asleepTimetv.text=fajrminute
    }
}