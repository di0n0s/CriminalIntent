package com.bignerdrach.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sfcar on 14/01/2017.
 */

public class CrimeLab { //Singleton

    private static CrimeLab sCrimeLab;  //prefijo s para indicar que es una variable estática
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        } return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for (int i = 0; i <100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" +i);
            crime.setSolved(i % 2 == 0); //Uno sí y otro no (pares)
            mCrimes.add(crime);
        }

    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        } return null;

    }
}
