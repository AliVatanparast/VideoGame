package vgame.ir.data.prefrence

import com.chibatching.kotpref.KotprefModel

object UserInfo : KotprefModel() {
    var name by stringPref()
    var token by stringPref()
    var avatar by stringPref()
    var mobile by stringPref()
    var id by intPref()
}