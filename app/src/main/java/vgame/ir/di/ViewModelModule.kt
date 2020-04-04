package vgame.ir.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import vgame.ir.view.ui.main.MainViewModel
import vgame.ir.view.ui.main.home.HomeViewModel
import vgame.ir.viewmodel.RavasiViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsSearchViewModel(movieDetailViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(ravasiViewModelFactory: RavasiViewModelFactory): ViewModelProvider.Factory
}
