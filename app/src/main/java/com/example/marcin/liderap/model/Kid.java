package com.example.marcin.liderap.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Kid implements Serializable{


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String nameChanged;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private String lastNameChanged;
    @DatabaseField
    private String college;
    //@ForeignCollectionField
    //ForeignCollection<College> college;


    public Kid(String name, String nameChanged, String lastName, String lastNameChanged, String college) {
        this.name = name;
        this.nameChanged = nameChanged;
        this.lastName = lastName;
        this.lastNameChanged = lastNameChanged;
        this.college = college;
    }

    public Kid() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameChanged() {
        return nameChanged;
    }

    public void setNameChanged(String nameChanged) {
        this.nameChanged = nameChanged;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameChanged() {
        return lastNameChanged;
    }

    public void setLastNameChanged(String lastNameChanged) {
        this.lastNameChanged = lastNameChanged;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
