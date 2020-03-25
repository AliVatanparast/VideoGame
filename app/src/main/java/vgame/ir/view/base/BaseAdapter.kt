package vgame.ir.view.base

import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Type : RecyclerView.ViewHolder, Data> : RecyclerView.Adapter<Type>() {

    protected lateinit var mRecyclerView: RecyclerView
    var on_attach = true
    abstract fun setData(data: List<Data>)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
                on_attach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        mRecyclerView = recyclerView

        super.onAttachedToRecyclerView(recyclerView)
    }
}
