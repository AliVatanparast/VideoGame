package vgame.ir.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vgame.ir.view.ui.main.search.HomeFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): HomeFragment

}
