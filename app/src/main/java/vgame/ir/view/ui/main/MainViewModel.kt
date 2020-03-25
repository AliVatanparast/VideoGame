package vgame.ir.view.ui.main

import androidx.lifecycle.ViewModel
import vgame.ir.data.AppRepository
import vgame.ir.data.prefrence.UserInfo
import javax.inject.Inject

class MainViewModel @Inject
constructor(private val ravasiRepository: AppRepository) : ViewModel() {

    fun logout(): Boolean {
        UserInfo.token = ""
        return true
    }
}
