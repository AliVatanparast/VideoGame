package vgame.ir.data;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import vgame.ir.data.local.dao.RavasiDao;
import vgame.ir.data.local.entity.BannerEntity;
import vgame.ir.data.local.entity.DefaultNoteEntity;
import vgame.ir.data.prefrence.UserInfo;
import vgame.ir.data.remote.RavasiDBService;
import vgame.ir.data.remote.model.AllGamesResponse;
import vgame.ir.data.remote.model.BannerResponse;
import vgame.ir.data.remote.model.SimpleResponse;


public class AppRepository {

    private final RavasiDao ravasiDao;
    private final RavasiDBService ravasiDBService;

    @Inject
    public AppRepository(RavasiDao ravasiDao, RavasiDBService ravasiDBService) {
        this.ravasiDao = ravasiDao;
        this.ravasiDBService = ravasiDBService;
    }

    public LiveData<Resource<AllGamesResponse>> getGames(String page) {
        return new NetworkRequest<AllGamesResponse>() {
            @NonNull
            @Override
            protected Call<AllGamesResponse> createCall() {
                return ravasiDBService.getGames(
                        "31fe7e5d1fmshc60893bba43f309p1387c1jsnb55d512f479e",
                        page
                );
            }
        }.getAsLiveData();
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
