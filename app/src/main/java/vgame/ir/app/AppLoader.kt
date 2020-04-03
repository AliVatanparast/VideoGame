package vgame.ir.app

import android.app.Activity
import android.app.Application

import androidx.appcompat.app.AppCompatActivity
import com.chibatching.kotpref.Kotpref

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import vgame.ir.data.remote.model.UserResponse
import vgame.ir.di.DaggerAppComponent


class AppLoader : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initializeComponent()

        instance = this
        Kotpref.init(this)
    }

    private fun initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingInjector
    }

    companion object {
        lateinit var instance: AppLoader
        var currentActivity: AppCompatActivity? = null
    }
}
