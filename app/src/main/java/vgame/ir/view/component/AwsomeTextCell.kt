package vgame.ir.view.component

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity

import vgame.ir.utils.Common


class AwsomeTextCell : AppCompatTextView {

    constructor(context: Context) : super(context) {
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomFont()
        gravity = Gravity.CENTER
    }

    fun setCustomFont() {
        val style = typeface.style
        if (style == Typeface.BOLD) {
            val myTypeface = Typeface.createFromAsset(context.assets, Common.FontsAddress.AWSOME_BOLD)
            typeface = myTypeface
        } else {
            val myTypeface = Typeface.createFromAsset(context.assets, Common.FontsAddress.AWSOME)
            typeface = myTypeface
        }

    }
}

