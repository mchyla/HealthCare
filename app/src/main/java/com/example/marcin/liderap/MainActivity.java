package com.example.marcin.liderap;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.marcin.liderap.fragments.DatePickerFragment;
import com.example.marcin.liderap.fragments.EndEventTimePickerFragment;
import com.example.marcin.liderap.fragments.EventFragment;
import com.example.marcin.liderap.fragments.KidManagementFragment;
import com.example.marcin.liderap.fragments.MainFragment;
import com.example.marcin.liderap.fragments.MeetingEndTimePickerFragment;
import com.example.marcin.liderap.fragments.MeetingStartTimePickerFragment;
import com.example.marcin.liderap.fragments.SCManagementFragment;
import com.example.marcin.liderap.fragments.StartEventTimePickerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new EventFragment()).addToBackStack("fragBack").commit();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).addToBackStack("main").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        drawer.destroyDrawingCache();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_sc_list) {
            fm.beginTransaction().replace(R.id.content_frame, new SCManagementFragment()).addToBackStack("fragBack").commit();
        } else if (id == R.id.nav_kids_list) {
            fm.beginTransaction().replace(R.id.content_frame, new KidManagementFragment()).addToBackStack("fragBack").commit();
        } else if (id == R.id.nav_generate) {
            fm.beginTransaction().replace(R.id.content_frame, new EventFragment()).addToBackStack("fragBack").commit();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showEventStartTimePickerDialog(View v) {
        DialogFragment newFragment = new StartEventTimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showEventEndTimePickerDialog(View v) {
        DialogFragment newFragment = new EndEventTimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showMeetingStartTimePickerDialog(View v) {
        DialogFragment newFragment = new MeetingStartTimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showMeetingEndTimePickerDialog(View v) {
        DialogFragment newFragment = new MeetingEndTimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}