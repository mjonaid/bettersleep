package com.task.bettersleep.presentation.sounds

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.bettersleep.R

class SoundAdapter(
    private val context: Context,
    private val itemList: List<SoundsItem>,
    private val secondList: List<SoundsItem>
) : RecyclerView.Adapter<SoundAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.sounds_recyclerview, parent, false)
        return ItemViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        val secondList = secondList[position]
        holder.titleTextViewSecondList.text=secondList.title
        holder.imageViewSecondList.setImageResource(secondList.imageResource)
        holder.titleTextView.text = currentItem.title
        holder.imageView.setImageResource(currentItem.imageResource)

        holder.itemView.setOnClickListener {
            playSound(currentItem.voiceResource)
        }
    }
    override fun getItemCount(): Int {
        return maxOf(itemList.size, secondList.size)
    }

    private fun playSound(soundResource: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundResource)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextViewSecondList: TextView = itemView.findViewById(R.id.titleTextView_secondList)
        val imageViewSecondList: ImageView = itemView.findViewById(R.id.imageView_secondlist)
    }
}

