package com.task.bettersleep.presentation.sounds


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.bettersleep.R
import com.task.bettersleep.databinding.FragmentSoundsBinding

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
binding.apply {
    // Initialize the adapter with default lists
    adapter = SoundAdapter(requireContext(), allFirstList, allSecondList)
    binding.recyclerView.layoutManager =
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
    binding.recyclerView.adapter = adapter

    binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
        adapter = when (checkedId) {
            allSounds -> SoundAdapter(requireContext(), allFirstList, allSecondList)
            popularSounds -> SoundAdapter(
                requireContext(),
                popularFirstList,
                popularSecondList
            )

            favouriteSounds -> SoundAdapter(
                requireContext(),
                favoriteFirstList,
                favoriteSecondList
            )

            newSounds -> SoundAdapter(requireContext(), newFirstList, newSecondList)
            natureSounds -> SoundAdapter(requireContext(), natureFirstList, natureSecondList)
            else -> SoundAdapter(requireContext(), allFirstList, allSecondList)
        }
        binding.recyclerView.adapter = adapter
    }
    }}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
