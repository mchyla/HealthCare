package com.example.marcin.liderap.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.marcin.liderap.R;
import com.example.marcin.liderap.adapters.KidAdapter;
import com.example.marcin.liderap.model.Kid;
import com.example.marcin.liderap.repository.OrmLiteKidRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mchyl on 11/04/2017.
 */

public class KidManagementFragment extends Fragment {
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.kid_management_fragment, container, false);
        lv = (ListView) rootView.findViewById(R.id.kidListView);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Dzieci Akademii");

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        List<Kid> kidArrayList = new ArrayList<>();
        try {
            kidArrayList = OrmLiteKidRepository.findAll(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onActivityCreated(savedInstanceState);
        KidAdapter adapter = new KidAdapter(getContext(),
                R.layout.listitem,
                kidArrayList);
        lv.setAdapter(adapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.kid_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.addKidMenuButton) {
            fm.beginTransaction().replace(R.id.content_frame, new AddKidFragment()).addToBackStack("fragBack").commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
