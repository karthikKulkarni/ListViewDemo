package com.android.samplelistview.network;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 * This is an interface to link UI and the websercice handler
 * onSuccess - returns the success response back to UI
 * onError - returns error message back to UI
 */
public interface ResponseHandler {
    public void onSucceess(Object response);

    public void onError(String msg);
}
