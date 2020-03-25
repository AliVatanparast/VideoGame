package vgame.ir.view.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import vgame.ir.R
import vgame.ir.data.local.entity.CourseEntity
import vgame.ir.data.remote.model.AllGamesResponse
import vgame.ir.databinding.FragmentHomeBinding
import vgame.ir.view.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    private lateinit var teacherPagingAdapter: AllGamesPagingAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        teacherPagingAdapter = AllGamesPagingAdapter(context, object : AllGamesPagingAdapter.CallBack {
            override fun onTeacherClick(teacher: AllGamesResponse.Game?, sharedView: View?) {

            }

            override fun onCourseClick(course: CourseEntity?, sharedView: View?) {

            }
        })
        dataBinding.rvTechers.adapter = teacherPagingAdapter

        viewModel.postsLiveData.observe(this , Observer {
            teacherPagingAdapter!!.submitList(it)
           // viewModel.networkState.observe(this, Observer { teacherPagingAdapter!!.setNetworkState(it) })
        })

        viewModel.networkState!!.observe(this, Observer { teacherPagingAdapter!!.setNetworkState(it) })

        return dataBinding.root
    }

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
