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
import com.task.bettersleep.presentation.models.NavigationItem

class HomesNavigationAdapter(private val itemList: List<NavigationItem>
) : ListAdapter<NavigationItem, HomesNavigationAdapter.ItemViewholder>(DiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_navigation_recyclerview_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(itemList[position])
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.titleTextView)
        fun bind(item: NavigationItem) {
            imageView.setImageResource(item.imageResource)
            textView.text = item.title
            itemView.setOnClickListener {
                // Handle on click
                itemView.findNavController().navigate(item.navigation)
            }
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<NavigationItem>() {
        override fun areItemsTheSame(oldItem: NavigationItem, newItem: NavigationItem): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem:NavigationItem, newItem: NavigationItem): Boolean {
            return oldItem == newItem
        }
    }
}

