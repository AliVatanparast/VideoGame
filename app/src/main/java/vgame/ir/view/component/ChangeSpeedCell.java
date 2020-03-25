package vgame.ir.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import vgame.ir.R;
import vgame.ir.utils.Common;

public class ChangeSpeedCell extends AppCompatTextView {

    private String last_speed = "1x";

    public interface CallBack {
        void onChangeSpeed(float f);
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public ChangeSpeedCell(Context context) {
        super(context);
        init();
    }

    public ChangeSpeedCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        int style = getTypeface().getStyle();
        if (style == Typeface.BOLD) {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR_BOLD);
            setTypeface(myTypeface);
        } else {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Common.FontsAddress.VAZIR);
            setTypeface(myTypeface);
        }

        setTextSize(18);

        setTextColor(getResources().getColor(R.color.md_white_1000));

        setText(last_speed);

        setOnClickListener(view -> {
            switch (getText().toString()) {
                case "1x":
                    setText("2x");
                    if (callBack != null) {
                        callBack.onChangeSpeed(2f);
                    }
                    break;
                case "2x":
                    setText("4x");
                    if (callBack != null) {
                        callBack.onChangeSpeed(4f);
                    }
                    break;
                case "4x":
                    setText("1x");
                    if (callBack != null) {
                        callBack.onChangeSpeed(1f);
                    }
                    break;
            }
        });
    }

    public void setLastSpeed(float f) {
        switch (f + "") {
            case "1.0":
                setText("1x");
                break;
            case "2.0":
                setText("2x");
                break;
            case "4.0":
                setText("4x");
                break;
        }
    }
}

