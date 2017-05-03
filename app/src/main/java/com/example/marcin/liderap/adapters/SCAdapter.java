package com.example.marcin.liderap.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.marcin.liderap.MainActivity;
import com.example.marcin.liderap.R;
import com.example.marcin.liderap.fragments.AddSchoolCoordinatorFragment;
import com.example.marcin.liderap.model.SchoolCoordinator;
import com.example.marcin.liderap.repository.SchoolCoordinatorRepository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class SCAdapter extends ArrayAdapter<SchoolCoordinator> {

    Context context;
    int layoutResourceId;
    List<SchoolCoordinator> data = null;
    SchoolCoordinator schoolCoordinator = null;

    public SCAdapter(Context context, int layoutResourceId, List<SchoolCoordinator> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SchoolCoordinatorHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SchoolCoordinatorHolder();
            holder.txtLastName = (TextView) row.findViewById(R.id.txtLastName);
            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtCollege = (TextView) row.findViewById(R.id.txtCollege);

            row.setTag(holder);
        } else {
            holder = (SchoolCoordinatorHolder) row.getTag();
        }

        schoolCoordinator = data.get(position);
        holder.txtName.setText(schoolCoordinator.getName());
        holder.txtLastName.setText(schoolCoordinator.getLastName());
        holder.txtCollege.setText(schoolCoordinator.getCollege());
        Log.e("schoolCoordinator", schoolCoordinator.getCollege()+" teasdas");

        Button addPlaceButton = (Button) row.findViewById(R.id.buttonEdit);
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),
                        AddSchoolCoordinatorFragment.class);
                intent.putExtra("schoolCoordinatorToEdit", (Serializable) schoolCoordinator);
                //getContext().startActivity(intent);

                FragmentManager ft =  ((MainActivity)context).getFragmentManager();
                AddSchoolCoordinatorFragment frag = new AddSchoolCoordinatorFragment();

                Bundle bundles = new Bundle();
                SchoolCoordinator schoolCoordinator = data.get(position);
                bundles.putSerializable("schoolCoordinatorToEdit", schoolCoordinator);

// ensure your object has not null
                if (schoolCoordinator != null) {
                    bundles.putSerializable("schoolCoordinator", schoolCoordinator);
                    Log.e("schoolCoordinator", "is valid");
                } else {
                    Log.e("schoolCoordinator", "is null");
                }
                frag.setArguments(bundles);
                ft.beginTransaction().replace(R.id.content_frame, frag).addToBackStack("fragBack").commit();


            }
        });

        Button deleteSchoolCoordinatorButton = (Button) row.findViewById(R.id.buttonDelete);
        deleteSchoolCoordinatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                try {
                    SchoolCoordinatorRepository.deleteSchoolCoordinator(getContext(), data.get(position));
                    data.remove(position); //or some other task
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                notifyDataSetChanged();
            }
        });

        return row;
    }

    static class SchoolCoordinatorHolder {
        TextView txtLastName;
        TextView txtName;
        TextView txtCollege;
    }
};