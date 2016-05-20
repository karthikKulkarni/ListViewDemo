package com.android.samplelistview.network;

import android.content.Context;

import com.android.samplelistview.model.Country;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * Created by Karthik_Kulkarni01 on 5/20/2016.
 * This is a handler to make a HTTP.GET request via Volley lib, to specified URL and get back the
 * response as string.
 * The response is then converted to a java pojo using Gson JSON parser lib
 * Data & control is sent back to UI using ResponseHandler interface.
 */
public class GetJSONDataHandler {

    private ResponseHandler handler;
    private Context context;
    private static final String JSON_URL = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    public GetJSONDataHandler(Context context, ResponseHandler responseHandler) {
        this.context = context;
        handler = responseHandler;

    }


    /**
     * Add the newly request to Volley que to be executed.
     * Resposne is handled in
     * SUCCESS - onResponse
     * ERROR - onErrorResponse
     */
    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            Country country = gson.fromJson(response, Country.class);
                            handler.onSucceess(country);
                        } catch (Exception e) {
                            handler.onError("Incorrect data received. Unable to load the view.");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onError("Unable to connect to Internet.Please check connectivity.");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
