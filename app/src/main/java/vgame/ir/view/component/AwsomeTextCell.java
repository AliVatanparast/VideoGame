package vgame.ir.view.component;
import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import vgame.ir.utils.Common;


public class AwsomeTextCell extends AppCompatTextView {

    public AwsomeTextCell(Context context) {
        super(context);
        setCustomFont();
    }

    public AwsomeTextCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
        setGravity(Gravity.CENTER);
    }

    public void setCustomFont() {
        int style = getTypeface().getStyle();
        if(style == Typeface.BOLD){
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.AWSOME_BOLD);
            setTypeface(myTypeface);
        }else {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.AWSOME);
            setTypeface(myTypeface);
        }

    }
}

