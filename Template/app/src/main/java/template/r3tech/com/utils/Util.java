package template.r3tech.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zymr Inc. on 06-03-2017.
 */

public class Util {

//    /**
//     * Checking whether net connection is available or not.
//     *
//     * @param nContext
//     * @return true if net connection is avaible otherwise false
//     */
//    public static boolean isNetworkAvailable(Context nContext) {
//        boolean isNetAvailable = false;
//        if (nContext != null) {
//            ConnectivityManager mConnectivityManager = (ConnectivityManager) nContext
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (mConnectivityManager != null) {
//                boolean mobileNetwork = false;
//                boolean wifiNetwork = false;
//                boolean mobileNetworkConnecetd = false;
//                boolean wifiNetworkConnecetd = false;
//                NetworkInfo mobileInfo = mConnectivityManager
//                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//                NetworkInfo wifiInfo = mConnectivityManager
//                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//                if (mobileInfo != null)
//                    mobileNetwork = mobileInfo.isAvailable();
//                if (wifiInfo != null)
//                    wifiNetwork = wifiInfo.isAvailable();
//                if (wifiNetwork == true || mobileNetwork == true) {
//                    if (mobileInfo != null)
//                        mobileNetworkConnecetd = mobileInfo
//                                .isConnectedOrConnecting();
//                    wifiNetworkConnecetd = wifiInfo.isConnectedOrConnecting();
//                }
//                isNetAvailable = (mobileNetworkConnecetd || wifiNetworkConnecetd);
//            }
//        }
//        return isNetAvailable;
//
//    }

    public static void showSnackBar(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_LONG).show();
    }

    public static int getResId(Context c, String name, String type) {
        return c.getResources().getIdentifier(name, type, c.getPackageName());
    }

    public static String convertOneDigitToTwoDecimal(int number) {
        return new DecimalFormat("00").format(number);
    }


    public static long getMilliseconds2(int day, int month, int year, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String getDateTime(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String getTime(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(cal.getTime());
    }

    public static int getDate(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfYear(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfMonth(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal.get(Calendar.YEAR);
    }

    public static String updateDecimal(double number) {
        DecimalFormat format = new DecimalFormat("##.##");
        format.setDecimalSeparatorAlwaysShown(false);
        return format.format(number);
    }

    public static long getTimeForSelectionRange(long timeMilli, String choice) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMilli);
        long resultTimeMilli, resultTimeSec = 0;
        switch (choice) {
            case "+":
                calendar.add(Calendar.DATE, 6);
                break;
            case "-":
                int date = calendar.get(Calendar.DATE) - 6;
                calendar.set(Calendar.DATE, date);
                break;
        }
        resultTimeMilli = calendar.getTimeInMillis();
        return resultTimeMilli;
    }

    public static String getSelectedDateForHistory(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("d MMM");
        calendar.setTimeInMillis(milliseconds);
        return format.format(calendar.getTime());
    }

    public static void hideKeyboard(Context context, View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

//    public static long getStartTime(int period) {
//        DateTime lastWeek = new DateTime().minusDays(period);
//        return lastWeek.getMillis();
//    }
}
