package vgame.ir.view.component;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatEditText;

import android.util.AttributeSet;

import vgame.ir.utils.Common;


public class EditTextCell extends AppCompatEditText {

    public EditTextCell(Context context) {
        super(context);
        setCustomFont();
    }

    public EditTextCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    private void setCustomFont() {
        int style = getTypeface().getStyle();
        if (style == Typeface.BOLD) {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR_BOLD);
            setTypeface(myTypeface);
        } else {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR);
            setTypeface(myTypeface);
        }

    }
}
