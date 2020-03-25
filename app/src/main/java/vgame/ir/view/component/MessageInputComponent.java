package vgame.ir.view.component;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

import vgame.ir.R;

public class MessageInputComponent extends LinearLayout {

    public interface Callback {
        void onSendClick(String text, boolean forReply, String time);
    }

    private EditTextCell edt_text;
    private AwsomeTextCell btn_close;
    private TextviewCell txt_reply_message;
    private TextviewCell txt_time_pick;
    private ConstraintLayout lyt_reply_container;
    private CheckBox checkbox;

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public MessageInputComponent(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.component_message_input, this, true);

        edt_text = view.findViewById(R.id.edt_input);
        btn_close = view.findViewById(R.id.btn_close);
        lyt_reply_container = view.findViewById(R.id.lyt_reply_container);
        txt_reply_message = view.findViewById(R.id.txt_reply_message);
        btn_close = view.findViewById(R.id.btn_close);
        txt_time_pick = view.findViewById(R.id.txt_time_pick);
        checkbox = view.findViewById(R.id.checkbox);

        lyt_reply_container.setVisibility(GONE);

        view.findViewById(R.id.btn_send).setOnClickListener(v -> {
            if (callback != null) {

                String forTime = txt_time_pick.getText().toString();
                if (forTime.equals("00:00") || !checkbox.isChecked()) {
                    forTime = "";
                }

                callback.onSendClick(edt_text.getText().toString(), lyt_reply_container.getVisibility() == VISIBLE, forTime);
            }
            reset();
        });

        txt_time_pick.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(context, (timePicker, selectedHour, selectedMinute) ->
                    txt_time_pick.setText(selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

        btn_close.setOnClickListener(v -> reset());

    }

    public void showReply(String message) {
        lyt_reply_container.setVisibility(VISIBLE);
        txt_reply_message.setText(message);

       /* InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt_text, InputMethodManager.SHOW_IMPLICIT);*/

        edt_text.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void reset() {
        edt_text.setText("");
        lyt_reply_container.setVisibility(GONE);
    }
}
