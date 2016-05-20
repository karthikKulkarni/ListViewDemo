package com.android.samplelistview.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.samplelistview.R;
import com.android.samplelistview.ui.fragment.LandingFragment;

/**
 * Created by karthik_kulkarni01 on 5/20/2016.
 * Parent Activity of LandingFragment, this is also the 1st activity to be loaded on the
 * application.
 */
public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        addFragment();
    }


    /**
     * Show the menu item on ActionBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * Handle action on menu item - Refresh
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refreshList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This is a generic method to add any Fragment on this Activity.
     */
    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LandingFragment landingFragment = new LandingFragment();
        fragmentManager.beginTransaction()
                .add(R.id.landing_activity_container, landingFragment)
                .commit();
    }

    /**
     * Fetch the current fragment using Id. Check if its instance of LandingFragment.
     * Call getCountryDetails() from LandingFragment, and populate the list on Refresh
     */
    private void refreshList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id
                .landing_activity_container);
        if (fragment != null && fragment instanceof LandingFragment) {
            ((LandingFragment) fragment).getCountryDetails();
        }

    }

}
