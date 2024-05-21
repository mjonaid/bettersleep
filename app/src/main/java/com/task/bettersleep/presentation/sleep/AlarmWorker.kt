package com.task.bettersleep.presentation.sleep

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.task.bettersleep.NotificationUtils
import java.util.Calendar

class AlarmWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("AlarmWorker", "Worker started")

        val prayerTimeManager = AlarmWorkManager(applicationContext)
        val nextPrayer = prayerTimeManager.getNextPrayer(Calendar.getInstance())

        nextPrayer?.let {
            val audioResId = prayerTimeManager.getAudioResourceForPrayer(it)
            playAzaanSound(applicationContext, audioResId)
            showNotification(applicationContext, it)
            Log.d("AlarmWorker", "Azaan sound played and notification shown for $it")
        } ?: run {
            Log.d("AlarmWorker", "No next prayer found")
        }

        return Result.success()
    }

    private fun playAzaanSound(context: Context, audioResId: Int) {
        val mediaPlayer = MediaPlayer.create(context, audioResId)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }

    private fun showNotification(context: Context, prayerName: String) {
        val notificationUtils = NotificationUtils(context)
        val notification = notificationUtils.getNotificationBuilder()
            .setContentTitle("Prayer Time")
            .setContentText("Time for $prayerName prayer.")
            .setSound(android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI)
            .build()

        notificationUtils.getManager().notify(1, notification)
    }

    companion object {
        fun createInputData(audioResId: Int): Data {
            return Data.Builder()
                .putInt(KEY_AUDIO_RES_ID, audioResId)
                .build()
        }

        private const val KEY_AUDIO_RES_ID = "audioResId"
    }
}



