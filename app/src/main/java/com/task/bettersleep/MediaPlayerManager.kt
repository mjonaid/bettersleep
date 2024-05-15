package com.task.bettersleep

import android.media.MediaPlayer
import android.content.Context

object MediaPlayerManager {
    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context, resId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.applicationContext, resId)
        }
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.pause()
    }
    fun restart() {
        mediaPlayer?.start()
    }
    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }
}
