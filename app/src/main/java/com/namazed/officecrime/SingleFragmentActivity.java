package com.namazed.officecrime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_fragment;
    private static final int CONTAINER_FRAGMENT = R.id.fragment_container;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(CONTAINER_FRAGMENT);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(CONTAINER_FRAGMENT, fragment).commit();
        }
    }
}
