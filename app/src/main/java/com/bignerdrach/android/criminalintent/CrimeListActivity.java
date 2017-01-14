package com.bignerdrach.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by sfcar on 14/01/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
