package vgame.ir.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager

import java.util.concurrent.ExecutionException

class DescriptionAdapter(private val context: Context, private val title: String, private val content: String, private val url: String, private val pendingIntent: PendingIntent) : PlayerNotificationManager.MediaDescriptionAdapter {

    override fun getCurrentContentTitle(player: Player): String {
        return title
    }

    override fun getCurrentContentText(player: Player): String? {
        return content
    }

    override fun getCurrentLargeIcon(player: Player,
                                     callback: PlayerNotificationManager.BitmapCallback): Bitmap? {
        val uri = Uri.parse(url)
        var notification_artwork: Bitmap? = null

        try {
            notification_artwork = Glide.with(context)
                    .asBitmap()
                    .load(uri)
                    .submit(100, 100).get()
        } catch (t: Throwable) {
            if (t is ExecutionException) {
                t = t.cause
            }
        }

        return notification_artwork
    }

    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        return pendingIntent
    }
}
