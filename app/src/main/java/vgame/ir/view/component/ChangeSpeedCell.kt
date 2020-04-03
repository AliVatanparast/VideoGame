package vgame.ir.view.component

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatTextView

import vgame.ir.R
import vgame.ir.utils.Common

class ChangeSpeedCell : AppCompatTextView {

    private val last_speed = "1x"

    private var callBack: CallBack? = null

    interface CallBack {
        fun onChangeSpeed(f: Float)
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        val style = typeface.style
        if (style == Typeface.BOLD) {
            val myTypeface = Typeface.createFromAsset(context.assets, Common.FontsAddress.VAZIR_BOLD)
            typeface = myTypeface
        } else {
            val myTypeface = Typeface.createFromAsset(context.assets, Common.FontsAddress.VAZIR)
            typeface = myTypeface
        }

        textSize = 18f

        setTextColor(resources.getColor(R.color.md_white_1000))

        text = last_speed

        setOnClickListener { view ->
            when (text.toString()) {
                "1x" -> {
                    text = "2x"
                    if (callBack != null) {
                        callBack!!.onChangeSpeed(2f)
                    }
                }
                "2x" -> {
                    text = "4x"
                    if (callBack != null) {
                        callBack!!.onChangeSpeed(4f)
                    }
                }
                "4x" -> {
                    text = "1x"
                    if (callBack != null) {
                        callBack!!.onChangeSpeed(1f)
                    }
                }
            }
        }
    }

    fun setLastSpeed(f: Float) {
        when (f.toString() + "") {
            "1.0" -> text = "1x"
            "2.0" -> text = "2x"
            "4.0" -> text = "4x"
        }
    }
}

