package com.bignerdrach.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerdrach.criminalintent.crime_id"; //

    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);//Recuperamos el extra (ID).
        return CrimeFragment.newInstance(crimeId); ////Recuperado, creamos una nueva instancia de CrimeFragment
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimeActivity.class); //Creamos un intent explícito
        intent.putExtra(EXTRA_CRIME_ID, crimeId);//Le añadimos la clave string y el valor de la clave que es el UUID del crime.
        return intent;
    }

}
