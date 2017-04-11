package com.example.marcin.healthcare.model;

import android.support.annotation.IdRes;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.xlsx4j.sml.Col;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Kid {


    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String name;
    @DatabaseField
    String lastName;
    //@ForeignCollectionField
    //ForeignCollection<College> college;

    public Kid(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Kid() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
