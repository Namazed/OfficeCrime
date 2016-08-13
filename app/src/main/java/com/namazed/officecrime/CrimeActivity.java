package com.namazed.officecrime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CrimeActivity extends FragmentActivity {

    private static final int LAYOUT = R.layout.activity_crime;
    private static final int CONTAINER_FRAGMENT = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(CONTAINER_FRAGMENT);

        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction().add(CONTAINER_FRAGMENT, fragment).commit();
        }
    }
}
