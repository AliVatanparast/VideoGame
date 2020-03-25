package vgame.ir.exoplayer;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;

import java.util.concurrent.ExecutionException;

public class DescriptionAdapter implements
        PlayerNotificationManager.MediaDescriptionAdapter {

    private String title, content, url;
    private Context context;
    private PendingIntent pendingIntent;

    public DescriptionAdapter(Context context, String title, String content, String url ,PendingIntent pendingIntent) {
        this.context = context;
        this.content = content;
        this.title = title;
        this.url = url;
        this.pendingIntent = pendingIntent;
    }

    @Override
    public String getCurrentContentTitle(Player player) {
        return title;
    }

    @Nullable
    @Override
    public String getCurrentContentText(Player player) {
        return content;
    }

    @Nullable
    @Override
    public Bitmap getCurrentLargeIcon(Player player,
                                      PlayerNotificationManager.BitmapCallback callback) {
        Uri uri = Uri.parse(url);
        Bitmap notification_artwork = null;

        try {
            notification_artwork = Glide.with(context)
                    .asBitmap()
                    .load(uri)
                    .submit(100, 100).get();
        } catch (Throwable t) {
            if (t instanceof ExecutionException) {
                t = t.getCause();
            }
        }
        return notification_artwork;
    }

    @Nullable
    @Override
    public PendingIntent createCurrentContentIntent(Player player) {
        return pendingIntent;
    }
}
