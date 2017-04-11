package com.example.marcin.healthcare.model;

import android.os.Parcelable;
import android.support.annotation.IdRes;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.xlsx4j.sml.Col;

import java.io.Serializable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Kid implements Serializable{


    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String name;
    @DatabaseField
    String lastName;
    @DatabaseField
    String college;
    //@ForeignCollectionField
    //ForeignCollection<College> college;


    public Kid(String name, String lastName, String college) {
        this.name = name;
        this.lastName = lastName;
        this.college = college;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
