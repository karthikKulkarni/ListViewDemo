package com.android.samplelistview.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.samplelistview.application.SampleListViewApplication;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 */
public class Utility {
    private static ConnectivityManager mConnMgr = (ConnectivityManager) SampleListViewApplication
            .getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

    /**
     * Check for network connection.
     *
     * @return true : if connected to Internet, else false.
     */
       public static boolean isConnectedToInternet() {
        NetworkInfo activeNetwork = mConnMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                isConnected = true;

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                isConnected = true;
            }
        }
        return isConnected;
    }
}
