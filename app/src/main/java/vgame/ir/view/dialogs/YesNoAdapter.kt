package vgame.ir.view.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import vgame.ir.R

class YesNoAdapter(private val context: Context, private val strYes: String, private val strNo: String) : BaseAdapter() {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var view = view
        val layoutInflater = LayoutInflater.from(context)
        view = layoutInflater.inflate(R.layout.simple_list_item, null)

        val holder = Holder(view.findViewById(R.id.text_view), view.findViewById(R.id.image_view))

        if (i == 0) {
            holder.textView!!.text = strYes
        } else {
            holder.textView!!.text = strNo
        }

        return view
    }

    inner class Holder(var textView: TextView, private val imageView: ImageView)
}
