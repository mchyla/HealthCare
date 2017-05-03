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
import com.example.marcin.liderap.fragments.AddKidFragment;
import com.example.marcin.liderap.model.Kid;
import com.example.marcin.liderap.repository.OrmLiteKidRepository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class KidAdapter extends ArrayAdapter<Kid> {

    Context context;
    int layoutResourceId;
    List<Kid> data = null;
    Kid kid = null;

    public KidAdapter(Context context, int layoutResourceId, List<Kid> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        KidHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new KidHolder();
            holder.txtLastName = (TextView) row.findViewById(R.id.txtLastName);
            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtCollege = (TextView) row.findViewById(R.id.txtCollege);

            row.setTag(holder);
        } else {
            holder = (KidHolder) row.getTag();
        }

        kid = data.get(position);
        holder.txtName.setText(kid.getName());
        holder.txtLastName.setText(kid.getLastName());
        holder.txtCollege.setText(kid.getCollege());

        final Button addPlaceButton = (Button) row.findViewById(R.id.buttonEdit);
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(),
                        AddKidFragment.class);
                intent.putExtra("kidToEdit", (Serializable) kid);
                //getContext().startActivity(intent);

                FragmentManager ft =  ((MainActivity)context).getFragmentManager();
                AddKidFragment frag = new AddKidFragment();

                Bundle bundles = new Bundle();
                Kid kid = data.get(position);
                bundles.putSerializable("kidToEdit", kid);

// ensure your object has not null
                if (kid != null) {
                    bundles.putSerializable("kid", kid);
                    Log.e("kid", "is valid");
                } else {
                    Log.e("kid", "is null");
                }
                frag.setArguments(bundles);
                ft.beginTransaction().replace(R.id.content_frame, frag).addToBackStack("fragBack").commit();


            }
        });

        Button deleteKidButton = (Button) row.findViewById(R.id.buttonDelete);
        deleteKidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                try {
                    OrmLiteKidRepository.deleteKid(getContext(), data.get(position));
                    data.remove(position); //or some other task
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                notifyDataSetChanged();
            }
        });

        return row;
    }

    static class KidHolder {
        TextView txtLastName;
        TextView txtName;
        TextView txtCollege;
    }
};