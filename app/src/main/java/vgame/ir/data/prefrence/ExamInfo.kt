package vgame.ir.data.prefrence

import com.chibatching.kotpref.KotprefModel

object ExamInfo : KotprefModel() {
    var list by stringPref("[]")
}