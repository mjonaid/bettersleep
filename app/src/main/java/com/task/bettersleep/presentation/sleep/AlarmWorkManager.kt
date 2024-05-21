package com.task.bettersleep.presentation.sleep

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.task.bettersleep.R
import java.util.*

class AlarmWorkManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("betterSleep", Context.MODE_PRIVATE)

    fun scheduleNextPrayerAudio() {
        val currentTime = Calendar.getInstance()
        val nextPrayer = getNextPrayer(currentTime)

        nextPrayer?.let {
            val prayerTime = getPrayerTime(it)
            val delay = prayerTime.timeInMillis - currentTime.timeInMillis

            if (delay >= 0) {
                scheduleAzaanWork(delay, it)
                Log.d("AlarmWorkManager", "Scheduled azaan work for $it with delay $delay")
            } else {
                Log.d("AlarmWorkManager", "Invalid delay $delay for $it")
            }
        } ?: run {
            Log.d("AlarmWorkManager", "No next prayer found")
        }
    }

    private fun scheduleAzaanWork(delay: Long, prayerName: String) {
        val audioResId = getAudioResourceForPrayer(prayerName)
        val azaanWorkRequest = buildAzaanWorkRequest(audioResId, delay, prayerName)

        WorkManager.getInstance(context).enqueue(azaanWorkRequest)
        Log.d("AlarmWorkManager", "Enqueued azaan work request for $prayerName with delay $delay")
    }

    fun getPrayerTime(prayerName: String): Calendar {
        val time = getAdjustedPrayerTimes()[prayerName]!!
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, time.first)
            set(Calendar.MINUTE, time.second)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    private fun buildAzaanWorkRequest(
        audioResId: Int,
        delay: Long,
        prayerName: String
    ) = OneTimeWorkRequestBuilder<AlarmWorker>()
        .addTag(prayerName)
        .setInputData(AlarmWorker.createInputData(audioResId))
        .setInitialDelay(delay, java.util.concurrent.TimeUnit.MILLISECONDS)
        .build()

    private fun getAdjustedPrayerTimes(): Map<String, Pair<Int, Int>> {
        // Retrieve prayer times from SharedPreferences
        val adjustedPrayerTimes = mutableMapOf<String, Pair<Int, Int>>()

        val alarmHour = sharedPreferences.getString("Alarm_HOUR", "0")?.toIntOrNull() ?: 0
        val alarmMinute = sharedPreferences.getString("Alarm_MINUTE", "0")?.toIntOrNull() ?: 0

        // Set the adjusted prayer times here (format: Hour, Minute)
        adjustedPrayerTimes["Fajr"] = Pair(alarmHour, alarmMinute)
        return adjustedPrayerTimes
    }

    fun getAudioResourceForPrayer(prayerName: String): Int {
        // Map prayer names to respective audio files
        return when (prayerName) {
            "Fajr" -> R.raw.audiojungle
            else -> R.raw.audiojungle
        }
    }

    fun getNextPrayer(currentTime: Calendar): String? {
        val adjustedPrayerTimes = getAdjustedPrayerTimes()
        val sortedTimes = adjustedPrayerTimes.entries
            .sortedBy { entry ->
                val prayerTime = getPrayerTime(entry.key)
                if (prayerTime.after(currentTime)) {
                    prayerTime.timeInMillis // Use prayerTime if the time is after the current time
                } else {
                    Long.MAX_VALUE // Return a high value for times before the current time to put them at the end
                }
            }
        return sortedTimes.firstOrNull()?.key
    }
}
