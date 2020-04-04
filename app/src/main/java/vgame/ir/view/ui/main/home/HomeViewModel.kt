package vgame.ir.view.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import javax.inject.Inject

import vgame.ir.data.AppRepository
import vgame.ir.data.Status
import vgame.ir.data.datasource.chat.GamesDataSource
import vgame.ir.data.remote.model.AllGamesResponse

class HomeViewModel @Inject
constructor(private val repository: AppRepository) : ViewModel() {

    var postsLiveData: LiveData<PagedList<AllGamesResponse.Game>>
    lateinit var networkState: LiveData<Status?>

    init {
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
        postsLiveData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, AllGamesResponse.Game> {

        val ds = GamesDataSource(repository)

        val dataSourceFactory = object : DataSource.Factory<Int, AllGamesResponse.Game>() {
            override fun create(): DataSource<Int, AllGamesResponse.Game>? {
                return ds
            }
        }
/*
        netWorkState = Transformations.switchMap(da,
                { dataSource -> dataSource.getNetworkState() })*/

        var lds = MutableLiveData<GamesDataSource>()
        lds.postValue(ds)

        networkState = Transformations.switchMap(lds) {
            address -> lds.value!!.networkState
        }

        return LivePagedListBuilder<Int, AllGamesResponse.Game>(dataSourceFactory, config)
    }
}
