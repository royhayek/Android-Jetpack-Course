package com.example.androidjetpackcourse.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.androidjetpackcourse.Constants
import com.example.androidjetpackcourse.R
import com.example.androidjetpackcourse.view.activities.MainActivity
import com.example.androidjetpackcourse.view.activities.PagingActivity
import com.google.firebase.messaging.RemoteMessage

const val TAG = "Notification Service"
const val PAGINGACTIVITY = "PAGINGACTIVITY"

class NotificationService : Service() {
    private var mMediaPlayer: MediaPlayer? = null
    private var channelId = "channelId"
    private var playedAtLeastOnce = false
    private var remoteMessage: RemoteMessage? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intentExtra: RemoteMessage? = intent?.getParcelableExtra("remoteMessage")

        when {
            intent?.action.equals(Constants.STARTFOREGROUND_ACTION) -> {
                remoteMessage = intentExtra
                showNotification(remoteMessage)
                Log.i(TAG, "Service Started")
            }
            intent?.action.equals(Constants.PREV_ACTION) -> {
                Log.i(TAG, "Clicked Previous")
            }
            intent?.action.equals(Constants.PLAY_ACTION) -> {
                Log.i(TAG, "Clicked Play")

                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.song1)
                    mMediaPlayer!!.start()
                    playedAtLeastOnce = true
                } else if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) {
                    mMediaPlayer!!.pause()
                } else {
                    mMediaPlayer!!.start()
                }

                showNotification(remoteMessage)

            }
            intent?.action.equals(Constants.NEXT_ACTION) -> {
                Log.i(TAG, "Clicked Next")
            }
            intent?.action.equals(
                Constants.STOPFOREGROUND_ACTION
            ) -> {
                Log.i(TAG, "Received Stop Foreground Intent")

                if (mMediaPlayer != null) {
                    mMediaPlayer!!.stop()
                    mMediaPlayer!!.release()
                    mMediaPlayer = null
                }

                stopForeground(true)
                stopSelf()

            }
        }
        return START_STICKY
    }


    private fun showNotification(remoteMessage: RemoteMessage?) {

        val view = RemoteViews(packageName, R.layout.notification)
        val bigView = RemoteViews(packageName, R.layout.notification_music_player)

        val notificationIntent =
            if (remoteMessage?.data?.isNotEmpty()!! && remoteMessage.data["click_action"] == PAGINGACTIVITY)
                Intent(this, PagingActivity::class.java)
            else
                Intent(this, MainActivity::class.java)
        notificationIntent.action = Constants.MAIN_ACTION
        notificationIntent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, 0
        )

        val previousIntent = Intent(this, NotificationService::class.java)
        previousIntent.action = Constants.PREV_ACTION
        val pPreviousIntent: PendingIntent = PendingIntent.getService(
            this, 0,
            previousIntent, 0
        )

        val playIntent = Intent(this, NotificationService::class.java)
        playIntent.action = Constants.PLAY_ACTION
        val pPlayIntent: PendingIntent = PendingIntent.getService(
            this, 0,
            playIntent, 0
        )

        val nextIntent = Intent(this, NotificationService::class.java)
        nextIntent.action = Constants.NEXT_ACTION
        val pNextIntent: PendingIntent = PendingIntent.getService(
            this, 0,
            nextIntent, 0
        )

        view.setOnClickPendingIntent(R.id.iv_play_or_pause, pPlayIntent)
        bigView.setOnClickPendingIntent(R.id.iv_play_or_pause, pPlayIntent)
        view.setOnClickPendingIntent(R.id.iv_play_next, pNextIntent)
        bigView.setOnClickPendingIntent(R.id.iv_play_next, pNextIntent)
        view.setOnClickPendingIntent(R.id.iv_play_previous, pPreviousIntent)
        bigView.setOnClickPendingIntent(R.id.iv_play_previous, pPreviousIntent)

        view.setTextViewText(R.id.tv_title, remoteMessage.notification?.title)
        view.setTextViewText(R.id.tv_message, remoteMessage.notification?.body)
        view.setImageViewResource(R.id.iv_app_logo, R.drawable.jetpack_logo)

        bigView.setTextViewText(R.id.tv_title, remoteMessage.notification?.title)
        bigView.setTextViewText(R.id.tv_message, remoteMessage.notification?.body)
        bigView.setImageViewResource(R.id.iv_app_logo, R.drawable.jetpack_logo)
        bigView.setTextViewText(R.id.tv_song_name, "Song title")

        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying)
            bigView.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_baseline_pause)
        else
            bigView.setImageViewResource(R.id.iv_play_or_pause, R.drawable.ic_baseline_play)


        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(R.drawable.jetpack_logo)
                .setContent(view)
                .setCustomBigContentView(bigView)
                .setColor(Color.WHITE)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
