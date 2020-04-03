/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vgame.ir.exoplayer

import android.app.Notification

import com.google.android.exoplayer2.offline.Download
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.scheduler.PlatformScheduler
import com.google.android.exoplayer2.ui.DownloadNotificationHelper
import com.google.android.exoplayer2.util.NotificationUtil
import com.google.android.exoplayer2.util.Util

import vgame.ir.R

class DemoDownloadService : DownloadService(FOREGROUND_NOTIFICATION_ID, DownloadService.DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL, CHANNEL_ID, R.string.exo_download_notification_channel_name) {

    private var notificationHelper: DownloadNotificationHelper? = null

    init {
        nextNotificationId = FOREGROUND_NOTIFICATION_ID + 1
    }

    override fun onCreate() {
        super.onCreate()
        notificationHelper = DownloadNotificationHelper(this, CHANNEL_ID)
    }

    override fun getDownloadManager(): DownloadManager {
        return ExoUtils.getInstance(this).getDownloadManager()!!
    }

    override fun getScheduler(): PlatformScheduler? {
        return if (Util.SDK_INT >= 21) PlatformScheduler(this, JOB_ID) else
        /* contentIntent= */ null
    }

    override fun getForegroundNotification(downloads: List<Download>): Notification {
        if (downloads.size > 0) {
            val download = downloads[0]
            /*EventBus.getDefault().post(
                    new ExoEvent(
                            humanReadableByteCount(download.getBytesDownloaded(), true),
                            ("" + download.getPercentDownloaded()) + " % ",
                            download.request.uri.toString()));*/
        }
        return notificationHelper!!.buildProgressNotification(
                R.drawable.ic_add, null, null, downloads)/* contentIntent= *//* message= */
    }

    override fun onDownloadChanged(download: Download) {
        val notification: Notification
        if (download.state == Download.STATE_COMPLETED) {
            /*  EventBus.getDefault().post(
                    new ExoEvent(
                            humanReadableByteCount(download.getBytesDownloaded(), true),
                            ("" + download.getPercentDownloaded()) + " % ",
                            download.request.uri.toString()));*/
            notification = notificationHelper!!.buildDownloadCompletedNotification(
                    R.drawable.ic_add, null,
                    Util.fromUtf8Bytes(download.request.data))
        } else if (download.state == Download.STATE_FAILED) {
            /*EventBus.getDefault().post(
                    new ExoEvent(
                            humanReadableByteCount(download.getBytesDownloaded(), true),
                            ("" + download.getPercentDownloaded()) + " % ",
                            download.request.uri.toString()));*/
            notification = notificationHelper!!.buildDownloadFailedNotification(
                    R.drawable.ic_add, null,
                    Util.fromUtf8Bytes(download.request.data))
        } else {
            return
        }
        NotificationUtil.setNotification(this, nextNotificationId++, notification)
    }

    companion object {

        private val CHANNEL_ID = "download_channel"
        private val JOB_ID = 1
        private val FOREGROUND_NOTIFICATION_ID = 1

        private var nextNotificationId = FOREGROUND_NOTIFICATION_ID + 1

        fun humanReadableByteCount(bytes: Long, si: Boolean): String {
            val unit = if (si) 1000 else 1024
            if (bytes < unit) {
                return "$bytes B"
            }
            val exp = (Math.log(bytes.toDouble()) / Math.log(unit.toDouble())).toInt()
            val pre = (if (si) "kMGTPE" else "KMGTPE")[exp - 1] + if (si) "" else "i"
            return String.format("%.1f %sB", bytes / Math.pow(unit.toDouble(), exp.toDouble()), pre)
        }
    }
}
