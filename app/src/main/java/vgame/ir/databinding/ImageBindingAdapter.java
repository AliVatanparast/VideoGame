package vgame.ir.databinding;

import androidx.databinding.BindingAdapter;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vgame.ir.R;

public final class ImageBindingAdapter {

    @BindingAdapter(value = "url")
    public static void loadImageUrl(ImageView view, String url) {
        if (url != null && !url.equals(""))
            Picasso.with(view.getContext())
                    //  .load(ApiConstants.IMAGE_ENDPOINT_PREFIX + url)
                    .load(url)
                    .resize(360, 200)
                    .placeholder(R.drawable.placeholder)
                    .into(view);
    }

}
