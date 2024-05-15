package com.task.bettersleep.presentation.home.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.bettersleep.presentation.models.NavigationItem
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentHomeBinding

class Home : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var navigationAdapter: HomesNavigationAdapter
    private lateinit var voiceAdapter: HomesVoiceAdapter
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val itemList = listOf(
            NavigationItem("Favourites", R.drawable.favourites, R.id.action_home2_to_favoritesHome),
            NavigationItem("SleepTales", R.drawable.sleeptales, R.id.action_home2_to_sleepTales),
            NavigationItem("Meditations", R.drawable.mediation, R.id.action_home2_to_mediations),
            NavigationItem("Music", R.drawable.music, R.id.action_home2_to_musicHome),
            NavigationItem("Mixes", R.drawable.mixes, R.id.action_home2_to_mixes),
            NavigationItem("SleepMoves", R.drawable.sleepmoves, R.id.action_home2_to_sleepMoves),
            NavigationItem("Breathe", R.drawable.breathe, R.id.action_home2_to_breathe),
        )
        navigationAdapter = HomesNavigationAdapter(itemList)
        binding.apply {
//we we only used the column in the virtical direction then we used the grid but when orientation needed then we used the staggeredgridlayout
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            recyclerView.adapter = navigationAdapter

        }
    }
}
