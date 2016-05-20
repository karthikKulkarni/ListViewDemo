package com.android.samplelistview.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.samplelistview.R;
import com.android.samplelistview.adapter.CustomListAdapter;
import com.android.samplelistview.model.Country;
import com.android.samplelistview.network.GetJSONDataHandler;
import com.android.samplelistview.network.ResponseHandler;
import com.android.samplelistview.ui.LandingActivity;
import com.android.samplelistview.util.Utility;

/**
 * Created by karthik_kulkarni01 on 5/20/2016.
 * Fragment to show the list of items from Country List
 */
public class LandingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private Country countryDetails;
    private Activity parentActivity;
    private CustomListAdapter customListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.landing_fragment_layout, container, false);
        listView = (ListView) rootView.findViewById(R.id.landing_fragment_list);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.landing_fragment);
        swipeRefreshLayout.setOnRefreshListener(this);

        getCountryDetails();
        return rootView;
    }


    /**
     * get the context of parent activity as soon as fragment is attached to activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (LandingActivity) context;

    }

    /**
     * make GetJSONDataHandler WS call to get country details.
     * Check for network connectivity before making WS call
     */
    public void getCountryDetails() {

        if (Utility.isConnectedToInternet()) {
            if (!swipeRefreshLayout.isRefreshing()) {
                showSpinner();
            }
            GetJSONDataHandler getJSONDataHandler = new GetJSONDataHandler(parentActivity,
                    countryDataResponseHandler);
            getJSONDataHandler.sendRequest();
        } else {
            Toast.makeText(parentActivity, "Unable to connect to Internet.Please check " +
                            "connectivity",
                    Toast
                            .LENGTH_LONG)
                    .show();
            hideSpinner();
        }
    }

    /**
     * ResponseHandler object to get back the data from GetJSONDataHandler.
     * Since Network calls are never executed on main(UI) thread, on return it'll added back to
     * UI Thread using parentActivity.runOnUiThread
     */
    ResponseHandler countryDataResponseHandler = new ResponseHandler() {
        @Override
        public void onSucceess(Object response) {
            countryDetails = (Country) response;
            parentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideSpinner();
                    loadDataToList(countryDetails);
                }
            });
        }

        @Override
        public void onError(final String msg) {


            parentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(parentActivity, msg, Toast.LENGTH_LONG).show();
                    hideSpinner();
                }
            });
        }
    };


    /**
     * On receiving data from WS, load the content on the listview using custom Adapter.
     * Update the title of the application.
     */
    private void loadDataToList(Country country) {
        parentActivity.setTitle(country.getTitle());
        customListAdapter = new CustomListAdapter(parentActivity
                , country.getRows());
        listView.setAdapter(customListAdapter);
    }

    /**
     * Show spinner
     */
    private void showSpinner() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    /**
     * Hide spinner
     */
    private void hideSpinner() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * On start of spinner, call getCountryDetails()
     */
    @Override
    public void onRefresh() {
        getCountryDetails();
    }


}
