package com.task.bettersleep.presentation.home.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.task.bettersleep.R

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesHome : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_home, container, false)
    }
}