package vgame.ir.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class UtilMethods {

    //time is like 12:10:56
    public static long getConvertedTime(String time) {
        long sec = 0, min = 0, hour = 0;

        if (time.split(":").length == 3) {
            sec = Long.valueOf(time.split(":")[2]);
            min = Long.valueOf(time.split(":")[1]);
            hour = Long.valueOf(time.split(":")[0]);
        } else if (time.split(":").length == 2) {
            sec = Long.valueOf(time.split(":")[1]);
            min = Long.valueOf(time.split(":")[0]);
        }

        min = min * 60;
        hour = hour * 60 * 60;

        return sec + min + hour;
    }

    //return -> 14:10
    public static String getCurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();
        int h = currentTime.getHours();
        int m = currentTime.getMinutes();
        return h + ":" + m;
    }

    public static void openBrowser(Activity activity  , String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        activity.startActivity(browserIntent);
    }

    //input -> 1250000  , return -> 12دقیقه
    public static String converTime(int time) {
        int min = time / 60000;
        int sec = (time % 60000) / 1000;
        return min + " دقیقه " + sec + " ثانیه ";
    }

    public static boolean validateMobile(String mobile) {
        if (!Pattern.matches("[a-zA-Z]+", mobile)) {
            return mobile.length() > 6 && mobile.length() <= 13;
        }
        return false;
    }

    public static String getAppVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static HashMap<String, String> replaceOrAddItemMap(HashMap<String, String> hashMap, String key, String value) {
        if (hashMap.containsKey(key)) {
            hashMap.remove(key);
            hashMap.put(key, value);
        } else {
            hashMap.put(key, value);
        }

        return hashMap;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
