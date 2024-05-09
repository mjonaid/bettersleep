package com.task.bettersleep.presentation.sounds

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.bettersleep.R

class Sounds : Fragment() {
    private lateinit var adapter: SoundAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sounds, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val itemList = listOf(
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 4", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 5", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 7", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 8", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 9", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 10", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 11", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 12", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Ite1m 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 14", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 15", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar)
        )
        val secondItemList = listOf(
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar),
            SoundsItem("Item 1", R.raw.naturesound1, R.drawable.phnumber),
            SoundsItem("Item 2", R.raw.naturesound2, R.drawable.densitypx),
            SoundsItem("Item 3", R.raw.naturesound3, R.drawable.bottomappbar)
        )
        adapter = SoundAdapter(requireContext(),itemList,secondItemList)

//we we only used the column in the virtical direction then we used the grid but when orientation needed then we used the staggeredgridlayout
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.adapter = adapter
        return view
    }


}