package com.task.bettersleep.presentation.home.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.bettersleep.R
import com.task.bettersleep.presentation.models.HomeItem

class HomesAdapter(private val soundsList: List<HomeItem>
) : ListAdapter<HomeItem, HomesAdapter.ItemViewholder>(DiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_recyclerview_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(soundsList[position])
    }
    override fun getItemCount(): Int {
        return soundsList.size
    }
    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.titleTextView)
        fun bind(item: HomeItem) {
            imageView.setImageResource(item.imageResource)
            textView.text = item.title
            itemView.setOnClickListener {
                // Handle on click
                itemView.findNavController().navigate(item.nav)
            }
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem:HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }
    }
}

