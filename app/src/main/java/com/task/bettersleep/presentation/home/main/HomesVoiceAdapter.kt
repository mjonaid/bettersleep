package com.task.bettersleep.presentation.home.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.bettersleep.MediaPlayerManager
import com.task.bettersleep.R
import com.task.bettersleep.presentation.models.VoiceItem

class HomesVoiceAdapter(
    private val itemList: List<VoiceItem>,
    private val context: Context
) : ListAdapter<VoiceItem, HomesVoiceAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_navigation_recyclerview_layout, parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewholder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(item: VoiceItem) {
            imageView.setImageResource(item.imageResource)
            textView.text = item.title
            itemView.setOnClickListener {
                playSound(item.voiceResource)
            }
        }

        private fun playSound(voiceResource: Int) {
            MediaPlayerManager.start(context, voiceResource)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VoiceItem>() {
        override fun areItemsTheSame(oldItem: VoiceItem, newItem: VoiceItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: VoiceItem, newItem: VoiceItem): Boolean {
            return oldItem == newItem
        }
    }
}
