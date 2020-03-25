package vgame.ir.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import vgame.ir.R;
import vgame.ir.view.component.round.RoundLinearLayout;

public class ButtonComponent extends LinearLayout {

    public interface CallBack {
        void onClick();
    }

    private CallBack callBack;

    private String icon, text;
    private AwsomeTextCell awsomeTextCell;
    private TextviewCell textviewCell;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public ButtonComponent(Context context) {
        super(context);
    }

    public ButtonComponent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.component_botton, this, true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonComponent, defStyle, 0);

        String icon = a.getString(R.styleable.ButtonComponent_bc_icon);
        String text = a.getString(R.styleable.ButtonComponent_bc_text);
        int color = a.getInt(R.styleable.ButtonComponent_bc_color, 0);
        int txtColor = a.getInt(R.styleable.ButtonComponent_bc_txt_color, 0);

        awsomeTextCell = findViewById(R.id.btn_icon);
        textviewCell = findViewById(R.id.txt_title);
        RoundLinearLayout roundFrameLayout = findViewById(R.id.roundFrameLayout);
        findViewById(R.id.roundFrameLayout).setOnClickListener(v -> {
            if (callBack != null) {
                callBack.onClick();
            }
        });

        if (icon != null) {
            awsomeTextCell.setText(icon);
        } else {
            awsomeTextCell.setVisibility(GONE);
        }

        if (color != 0) {
            roundFrameLayout.getDelegate().setBackgroundColor(color);
        }

        if (txtColor != 0) {
            textviewCell.setTextColor(txtColor);
            awsomeTextCell.setTextColor(txtColor);
        }

        if (text != null) {
            textviewCell.setText(text);
        }

        a.recycle();
    }
}