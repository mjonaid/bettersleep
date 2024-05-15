package com.task.bettersleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isMediaPlayerPlaying = MutableLiveData<Boolean>()
    val isMediaPlayerPlaying: LiveData<Boolean> = _isMediaPlayerPlaying

    init {
        // Initialize the initial state of media player playing from MediaPlayerManager
        _isMediaPlayerPlaying.value = MediaPlayerManager.isPlaying()
    }
}
