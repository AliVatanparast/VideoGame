package vgame.ir.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import vgame.ir.data.local.Migrations
import vgame.ir.data.local.RavasiDatabase
import vgame.ir.data.local.dao.RavasiDao
import vgame.ir.data.remote.ApiConstants
import vgame.ir.data.remote.RavasiDBService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vgame.ir.data.AppRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
        //  okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRavasiRetrofit(okHttpClient: OkHttpClient): RavasiDBService {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(RavasiDBService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRavasiDatabase(application: Application): RavasiDatabase {
        return Room.databaseBuilder(application, RavasiDatabase::class.java, "ravasi.db")
                .addMigrations(Migrations.MIGRATION_1_3)
                //.addMigrations(Migrations.MIGRATION_2_3)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRavasiDao(ravasiDatabase: RavasiDatabase): RavasiDao {
        return ravasiDatabase.ravasiDao()
    }
}
