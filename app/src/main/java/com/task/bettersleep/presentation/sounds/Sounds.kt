package com.task.bettersleep.presentation.sounds

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentSoundsBinding
import com.task.bettersleep.presentation.models.VoiceItem

class Sounds : Fragment(R.layout.fragment_sounds) {
    private var _binding: FragmentSoundsBinding? = null
    private lateinit var adapter: SoundAdapter
    private lateinit var viewModel: SoundsViewModel
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSoundsBinding.bind(view)

        // Ensure viewModel is initialized
        viewModel = ViewModelProvider(this)[SoundsViewModel::class.java]

        val allFirstList = viewModel.itemList
        val allSecondList = viewModel.secondItemList
        val popularFirstList = viewModel.thirdItemlist
        val popularSecondList = viewModel.secondItemList
        val favoriteFirstList = viewModel.itemList
        val favoriteSecondList = viewModel.secondItemList
        val newFirstList = viewModel.itemList
        val newSecondList = viewModel.secondItemList
        val natureFirstList = viewModel.itemList
        val natureSecondList = viewModel.secondItemList

        // Initialize the adapter with default lists
        adapter = SoundAdapter(requireContext(), allFirstList, allSecondList)
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        binding.recyclerView.adapter = adapter

        // Set listener for chip group
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.allSounds -> {
                    updateAdapter(allFirstList, allSecondList)
                    showToast("All Sounds selected")
                }
                R.id.popularSounds -> {
                    updateAdapter(popularFirstList, popularSecondList)
                    showToast("Popular Sounds selected")
                }
                R.id.favouriteSounds -> {
                    updateAdapter(favoriteFirstList, favoriteSecondList)
                    showToast("Favourite Sounds selected")
                }
                R.id.newSounds -> {
                    updateAdapter(newFirstList, newSecondList)
                    showToast("New Sounds selected")
                }
                R.id.natureSounds -> {
                    updateAdapter(natureFirstList, natureSecondList)
                    showToast("Nature Sounds selected")
                }
                else -> {
                    updateAdapter(allFirstList, allSecondList)
                    showToast("All Sounds selected")
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(firstList: List<VoiceItem>, secondList: List<VoiceItem>) {
        adapter.updateData(firstList,secondList)
        binding.recyclerView.adapter = adapter

    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
