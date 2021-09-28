package com.example.androidjetpackcourse.utils

import android.media.MediaPlayer
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.base.App

object MPSingleton {

    val mediaPlayer: MediaPlayer

    init{
        val mp = MediaPlayer.create(
            App.context,
            R.raw.song1
        )
        mediaPlayer = mp
    }
}
