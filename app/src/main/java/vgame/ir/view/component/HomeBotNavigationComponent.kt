package vgame.ir.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

import vgame.ir.R

class HomeBotNavigationComponent(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs, 0) {

    private var callback: Callback? = null

    private var btn_search: AwsomeTextCell? = null
    private var btn_profile: AwsomeTextCell? = null
    private var btn_home: AwsomeTextCell? = null

    interface Callback {
        fun onSearchClick()

        fun onHomeClick()

        fun onProfileClick()
    }

    init {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.component_home_btn_navigation, this, true)

        btn_search = view.findViewById(R.id.btn_search)
        btn_profile = view.findViewById(R.id.btn_profile)
        btn_home = view.findViewById(R.id.btn_home)

        btn_home!!.setOnClickListener { v ->
            select(1)
            if (callback != null) {
                callback!!.onHomeClick()
            }
        }

        btn_profile!!.setOnClickListener { v ->
            select(2)
            if (callback != null) {
                callback!!.onProfileClick()
            }
        }

        btn_search!!.setOnClickListener { v ->
            select(0)
            if (callback != null) {
                callback!!.onSearchClick()
            }
        }
    }

    fun select(position: Int) {
        btn_search!!.setBackgroundColor(resources.getColor(R.color.colorAccent))
        btn_profile!!.setBackgroundColor(resources.getColor(R.color.colorAccent))
        btn_home!!.background = resources.getDrawable(R.drawable.circle_fill_accent)

        btn_search!!.setTextColor(resources.getColor(R.color.md_grey_900))
        btn_profile!!.setTextColor(resources.getColor(R.color.md_grey_900))
        btn_home!!.setTextColor(resources.getColor(R.color.md_grey_900))

        val awsomeTextCell: AwsomeTextCell
        when (position) {
            0 -> awsomeTextCell = btn_search!!
            1 -> awsomeTextCell = btn_home!!
            2 -> awsomeTextCell = btn_profile!!
            else -> awsomeTextCell = btn_home!!
        }

        awsomeTextCell.setTextColor(resources.getColor(R.color.md_white_1000))

        if (awsomeTextCell.id == R.id.btn_home) {
            awsomeTextCell.background = resources.getDrawable(R.drawable.circle_fill_primary)
        } else {
            awsomeTextCell.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }
}
