package com.bignerdrach.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sfcar on 14/01/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() { //Sobreescribimos el método onResume para que cada vez que el CrimeListFragment llegue a este estado recargue la lista
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged(); //Notifica los cambios que se hayan producido.
        }


    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //Creamos el ViewHolder

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        private Crime mCrime;


        public CrimeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); //Configuramos CrimeHolder como el receptor de los eventos táctiles

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrime.getTitle()+" cliked!", Toast.LENGTH_SHORT).show();

            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId()); //Llamamos al método newIntent para generar un intent explicito pasandole el contexto y el ID del Crime
            startActivity(intent); //Abre una nueva CrimeActivity

        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{ //Creamos el Adapter

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //Cargamos el "inflador"
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false); //Creamos una vista. Desplegamos un layout que contiene una sola vista
            return new CrimeHolder(view); //Devolvemos la vista encapsulada en el ViewHolder
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position); //Guarda en crime el crime del listado respecto a la posición que le pasamos
            holder.bindCrime(crime); //Enlazamos una vista de un ViewHolder a un objeto de Crime

        }

        @Override
        public int getItemCount() { //Devuelve el tamaño de la lista
            return mCrimes.size();
        }
    }


}
