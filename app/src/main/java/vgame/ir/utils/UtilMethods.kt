package vgame.ir.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager

import java.util.Calendar
import java.util.Date
import java.util.HashMap
import java.util.regex.Pattern

object UtilMethods {

    //return -> 14:10
    val currentTime: String
        get() {
            val currentTime = Calendar.getInstance().time
            val h = currentTime.hours
            val m = currentTime.minutes
            return "$h:$m"
        }

    //time is like 12:10:56
    fun getConvertedTime(time: String): Long {
        var sec: Long = 0
        var min: Long = 0
        var hour: Long = 0

        if (time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size == 3) {
            sec = java.lang.Long.valueOf(time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2])
            min = java.lang.Long.valueOf(time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
            hour = java.lang.Long.valueOf(time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
        } else if (time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size == 2) {
            sec = java.lang.Long.valueOf(time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
            min = java.lang.Long.valueOf(time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
        }

        min = min * 60
        hour = hour * 60 * 60

        return sec + min + hour
    }

    fun openBrowser(activity: Activity, link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        activity.startActivity(browserIntent)
    }

    //input -> 1250000  , return -> 12دقیقه
    fun converTime(time: Int): String {
        val min = time / 60000
        val sec = time % 60000 / 1000
        return "$min دقیقه $sec ثانیه "
    }

    fun validateMobile(mobile: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", mobile)) {
            mobile.length > 6 && mobile.length <= 13
        } else false
    }

    fun getAppVersion(context: Context): String {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }

    fun replaceOrAddItemMap(hashMap: HashMap<String, String>, key: String, value: String): HashMap<String, String> {
        if (hashMap.containsKey(key)) {
            hashMap.remove(key)
            hashMap[key] = value
        } else {
            hashMap[key] = value
        }

        return hashMap
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
