package vgame.ir.view.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*
import vgame.ir.R
import vgame.ir.data.remote.model.AllGamesResponse
import vgame.ir.databinding.FragmentHomeBinding
import vgame.ir.view.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), AllGamesPagingAdapter.CallBack {
    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    private lateinit var gamesPagingAdapter: AllGamesPagingAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initGamesRecyclerView()

        return dataBinding.root
    }

    fun initGamesRecyclerView() {
        gamesPagingAdapter = AllGamesPagingAdapter(this)
        dataBinding.rvGames.adapter = gamesPagingAdapter

        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            gamesPagingAdapter.submitList(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer { gamesPagingAdapter.setNetworkState(it!!) })
    }

    override fun onGameClick(game: AllGamesResponse.Game, sharedView: View) {

    }
}
