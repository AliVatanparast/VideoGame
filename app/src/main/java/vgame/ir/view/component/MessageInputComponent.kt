package vgame.ir.view.component

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.LinearLayout

import androidx.constraintlayout.widget.ConstraintLayout

import java.util.Calendar

import vgame.ir.R

class MessageInputComponent(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs, 0) {

    private var edt_text: EditTextCell? = null
    private var btn_close: AwsomeTextCell? = null
    private var txt_reply_message: TextviewCell? = null
    private var txt_time_pick: TextviewCell? = null
    private var lyt_reply_container: ConstraintLayout? = null
    private var checkbox: CheckBox? = null

    private var callback: Callback? = null

    interface Callback {
        fun onSendClick(text: String, forReply: Boolean, time: String)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    init {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.component_message_input, this, true)

        edt_text = view.findViewById(R.id.edt_input)
        btn_close = view.findViewById(R.id.btn_close)
        lyt_reply_container = view.findViewById(R.id.lyt_reply_container)
        txt_reply_message = view.findViewById(R.id.txt_reply_message)
        btn_close = view.findViewById(R.id.btn_close)
        txt_time_pick = view.findViewById(R.id.txt_time_pick)
        checkbox = view.findViewById(R.id.checkbox)

        lyt_reply_container!!.visibility = View.GONE

        view.findViewById<View>(R.id.btn_send).setOnClickListener { v ->
            if (callback != null) {

                var forTime = txt_time_pick!!.text.toString()
                if (forTime == "00:00" || !checkbox!!.isChecked) {
                    forTime = ""
                }

                callback!!.onSendClick(edt_text!!.text!!.toString(), lyt_reply_container!!.visibility == View.VISIBLE, forTime)
            }
            reset()
        }

        txt_time_pick!!.setOnClickListener { v ->
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(context, { timePicker, selectedHour, selectedMinute -> txt_time_pick!!.text = "$selectedHour:$selectedMinute" }, hour, minute, true)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        btn_close!!.setOnClickListener { v -> reset() }

    }

    fun showReply(message: String) {
        lyt_reply_container!!.visibility = View.VISIBLE
        txt_reply_message!!.text = message

        /* InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt_text, InputMethodManager.SHOW_IMPLICIT);*/

        edt_text!!.requestFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun reset() {
        edt_text!!.setText("")
        lyt_reply_container!!.visibility = View.GONE
    }
}
