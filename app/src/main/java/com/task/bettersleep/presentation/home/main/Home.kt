package com.task.bettersleep.presentation.home.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.bettersleep.presentation.models.HomeItem
import com.task.bettersleep.R

class Home : Fragment() {
    private lateinit var adapter: HomesAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val itemList = listOf(
            HomeItem("Favourites", R.drawable.favourites, R.id.action_home2_to_favoritesHome),
            HomeItem("SleepTales", R.drawable.sleeptales, R.id.action_home2_to_sleepTales),
            HomeItem("Meditations", R.drawable.mediation, R.id.action_home2_to_mediations),
            HomeItem("Music", R.drawable.music, R.id.action_home2_to_musicHome),
            HomeItem("Mixes", R.drawable.mixes, R.id.action_home2_to_mixes),
            HomeItem("SleepMoves", R.drawable.sleepmoves, R.id.action_home2_to_sleepMoves),
            HomeItem("Breathe", R.drawable.breathe, R.id.action_home2_to_breathe),
        )
        adapter = HomesAdapter(itemList)
//we we only used the column in the virtical direction then we used the grid but when orientation needed then we used the staggeredgridlayout
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.adapter = adapter
        return view
    }
}
