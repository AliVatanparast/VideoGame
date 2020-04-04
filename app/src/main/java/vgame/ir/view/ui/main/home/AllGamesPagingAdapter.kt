package vgame.ir.view.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import vgame.ir.data.Status
import vgame.ir.data.local.entity.CourseEntity
import vgame.ir.data.remote.model.AllGamesResponse
import vgame.ir.databinding.ItemGamesListBinding
import vgame.ir.databinding.NetworkItemBinding

class AllGamesPagingAdapter constructor(private val context: Context, private val callBack: CallBack) : PagedListAdapter<AllGamesResponse.Game, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var networkState: Status? = null

    interface CallBack {
        fun onGameClick(game: AllGamesResponse.Game, sharedView: View)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        if (i == TYPE_PROGRESS) {
            val layoutInflater = LayoutInflater.from(viewGroup.context)
            val headerBinding = NetworkItemBinding.inflate(layoutInflater, viewGroup, false)
            return NetworkStateItemViewHolder(headerBinding)
        } else {
            return VGamesHolder.create(LayoutInflater.from(viewGroup.context), viewGroup, callBack)
        }
    }

    override fun onBindViewHolder(itemHolder: RecyclerView.ViewHolder, i: Int) {
        if (itemHolder is VGamesHolder) {
            val searchModel = getItem(i)
            itemHolder.onBind(searchModel)
        } else {
            (itemHolder as NetworkStateItemViewHolder).bindView(networkState)
        }
    }

    fun setNetworkState(newNetworkState: Status) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_GAME
        }
    }

    private fun hasExtraRow(): Boolean {
        return if (networkState != null && networkState !== Status.SUCCESS) {
            true
        } else {
            false
        }
    }

    inner class NetworkStateItemViewHolder(private val binding: NetworkItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(networkState: Status?) {
            if (networkState === Status.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

            if (networkState === Status.ERROR) {
                binding.errorMsg.visibility = View.VISIBLE
                // binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.visibility = View.GONE
            }
        }
    }

    class VGamesHolder(internal var binding: ItemGamesListBinding, callback: CallBack) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { v -> callback.onGameClick(binding.game!!, binding.imgAvatar) }
        }

        fun onBind(game: AllGamesResponse.Game?) {
            binding.game = game
            binding.executePendingBindings()
        }

        companion object {

            fun create(inflater: LayoutInflater, parent: ViewGroup, callback: CallBack): VGamesHolder {
                val inflate = ItemGamesListBinding.inflate(inflater, parent, false)
                return VGamesHolder(inflate, callback)
            }
        }
    }

    companion object {

        private val TYPE_PROGRESS = 0
        private val TYPE_GAME = 1

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllGamesResponse.Game>() {
            override fun areItemsTheSame(s1: AllGamesResponse.Game, s2: AllGamesResponse.Game): Boolean {
                return if (s1.id == s2.id) {
                    true
                } else false
            }

            override fun areContentsTheSame(s1: AllGamesResponse.Game, s2: AllGamesResponse.Game): Boolean {
                return if (s1.background_image == s2.background_image) {
                    true
                } else false
            }
        }
    }
}
