package vgame.ir.data.datasource.chat

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PageKeyedDataSource

import javax.inject.Inject

import vgame.ir.data.AppRepository
import vgame.ir.data.Status
import vgame.ir.data.remote.RavasiDBService
import vgame.ir.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vgame.ir.data.remote.model.AllGamesResponse

class GamesDataSource(var appRepository: AppRepository?) : PageKeyedDataSource<Int, AllGamesResponse.Game>() {

    val networkState: MutableLiveData<Status?>
    val initialLoading: MutableLiveData<Any>

   /* @Inject
    var appRepository: AppRepository? = null*/

    init {
        networkState = MutableLiveData()
        initialLoading = MutableLiveData()
    }

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, AllGamesResponse.Game>) {

        initialLoading.postValue(Status.LOADING)
        networkState.postValue(Status.LOADING)

        //appRepository.getGames(FIRST_PAGE+ "")

        /*Transformations.map(appRepository!!.getGames(FIRST_PAGE.toString() + "")) {
            if (it.status.equals(Status.SUCCESS)) {
                initialLoading.postValue(Status.SUCCESS)
                networkState.postValue(Status.SUCCESS)

                callback.onResult(it.data!!.results!!, null, FIRST_PAGE + 1)
            } else {
                initialLoading.postValue(Status.ERROR)
                networkState.postValue(Status.ERROR)
            }
        }*/

        val service = RetrofitClientInstance.retrofitInstance.create(RavasiDBService::class.java)
        val call = service.getGames("31fe7e5d1fmshc60893bba43f309p1387c1jsnb55d512f479e", FIRST_PAGE.toString() + "")
        call.enqueue(object : Callback<AllGamesResponse> {
            override fun onResponse(call: Call<AllGamesResponse>, response: Response<AllGamesResponse>) {
                if (response.body() != null) {
                    if (response.isSuccessful) {
                        initialLoading.postValue(Status.SUCCESS)
                        networkState.postValue(Status.SUCCESS)

                        callback.onResult(response.body()!!.results!!, null, FIRST_PAGE + 1)
                    } else {
                        initialLoading.postValue(Status.ERROR)
                        networkState.postValue(Status.ERROR)
                    }
                }
            }

            override fun onFailure(call: Call<AllGamesResponse>, t: Throwable) {
                networkState.postValue(Status.ERROR)
            }
        })
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, AllGamesResponse.Game>) {
        networkState.postValue(Status.LOADING)

        val service = RetrofitClientInstance.retrofitInstance.create(RavasiDBService::class.java)
        val call = service.getGames("31fe7e5d1fmshc60893bba43f309p1387c1jsnb55d512f479e", params.key.toString() + "")
        call.enqueue(object : Callback<AllGamesResponse> {
            override fun onResponse(call: Call<AllGamesResponse>, response: Response<AllGamesResponse>) {
                if (response.body() != null) {
                    if (response.isSuccessful) {
                        networkState.postValue(Status.SUCCESS)
                        val key = if (params.key > 1) params.key - 1 else null
                        callback.onResult(response.body()!!.results!!, key)
                    } else {
                        networkState.postValue(Status.ERROR)
                    }
                }
            }

            override fun onFailure(call: Call<AllGamesResponse>, t: Throwable) {
                networkState.postValue(Status.ERROR)
            }
        })
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, AllGamesResponse.Game>) {
        networkState.postValue(Status.LOADING)
        val service = RetrofitClientInstance.retrofitInstance.create(RavasiDBService::class.java)
        val call = service.getGames("31fe7e5d1fmshc60893bba43f309p1387c1jsnb55d512f479e", params.key.toString() + "")
        call.enqueue(object : Callback<AllGamesResponse> {
            override fun onResponse(call: Call<AllGamesResponse>, response: Response<AllGamesResponse>) {
                if (response.body() != null) {
                    if (response.isSuccessful) {
                        networkState.postValue(Status.SUCCESS)

                        val key = if (response.body()!!.next == null)
                            null
                        else
                            params.key + 1
                        callback.onResult(response.body()!!.results!!, key)
                    } else {
                        networkState.postValue(Status.ERROR)
                    }
                }
            }

            override fun onFailure(call: Call<AllGamesResponse>, t: Throwable) {
                networkState.postValue(Status.ERROR)
            }
        })
    }

    companion object {

        val PAGE_SIZE = 20
        val FIRST_PAGE = 1
    }
}
