package vgame.ir.view.component

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

import vgame.ir.R
import vgame.ir.view.component.round.RoundLinearLayout

class ButtonComponent : LinearLayout {

    private var callBack: CallBack? = null

    private val icon: String? = null
    private val text: String? = null
    private var awsomeTextCell: AwsomeTextCell? = null
    private var textviewCell: TextviewCell? = null

    interface CallBack {
        fun onClick()
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    constructor(context: Context) : super(context) {}

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : super(context, attrs, defStyle) {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.component_botton, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.ButtonComponent, defStyle, 0)

        val icon = a.getString(R.styleable.ButtonComponent_bc_icon)
        val text = a.getString(R.styleable.ButtonComponent_bc_text)
        val color = a.getInt(R.styleable.ButtonComponent_bc_color, 0)
        val txtColor = a.getInt(R.styleable.ButtonComponent_bc_txt_color, 0)

        awsomeTextCell = findViewById(R.id.btn_icon)
        textviewCell = findViewById(R.id.txt_title)
        val roundFrameLayout = findViewById<RoundLinearLayout>(R.id.roundFrameLayout)
        findViewById<View>(R.id.roundFrameLayout).setOnClickListener { v ->
            if (callBack != null) {
                callBack!!.onClick()
            }
        }

        if (icon != null) {
            awsomeTextCell!!.text = icon
        } else {
            awsomeTextCell!!.visibility = View.GONE
        }

        if (color != 0) {
            roundFrameLayout.delegate.backgroundColor = color
        }

        if (txtColor != 0) {
            textviewCell!!.setTextColor(txtColor)
            awsomeTextCell!!.setTextColor(txtColor)
        }

        if (text != null) {
            textviewCell!!.text = text
        }

        a.recycle()
    }
}