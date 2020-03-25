package vgame.ir.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vgame.ir.view.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun mainActivity(): MainActivity
}
