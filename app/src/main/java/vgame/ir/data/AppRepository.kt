package vgame.ir.data

import androidx.lifecycle.LiveData

import javax.inject.Inject

import retrofit2.Call
import vgame.ir.data.local.dao.AppDao
import vgame.ir.data.remote.RavasiDBService
import vgame.ir.data.remote.model.AllGamesResponse


class AppRepository @Inject
constructor(private val ravasiDao: AppDao, private val ravasiDBService: RavasiDBService) {

    fun getGames(page: String): LiveData<Resource<AllGamesResponse>> {
        return object : NetworkRequest<AllGamesResponse>() {
            override fun createCall(): Call<AllGamesResponse> {
                return ravasiDBService.getGames(
                        "31fe7e5d1fmshc60893bba43f309p1387c1jsnb55d512f479e",
                        page
                )
            }
        }.asLiveData
    }

    /*public LiveData<Resource<List<BannerEntity>>> getGames() {
        return new NetworkBoundResource<List<BannerEntity>, BannerResponse>() {

            @Override
            protected void saveCallResult(@NonNull BannerResponse item) {
                ravasiDao.saveBanner(ModelConvertor.getBannerEntity(item));
            }

            @NonNull
            @Override
            protected LiveData<List<BannerEntity>> loadFromDb() {
                return ravasiDao.loadBanners();
            }

            @NonNull
            @Override
            protected Call<BannerResponse> createCall() {
                return ravasiDBService.loadBanners(UserInfo.INSTANCE.getToken());
            }
        }.getAsLiveData();
    }*/

    /*public LiveData<List<DefaultNoteEntity>> getAllUserNotes() {
        return ravasiDao.getAllUserNotes();
    }

    public LiveData<Resource<SimpleResponse>> sendTiket(String title, String description) {
        return new NetworkRequest<SimpleResponse>() {
            @NonNull
            @Override
            protected Call<SimpleResponse> createCall() {
                return ravasiDBService.sendTiket(
                        title,
                        description,
                        UserInfo.INSTANCE.getToken()
                );
            }
        }.getAsLiveData();
    }*/
}
