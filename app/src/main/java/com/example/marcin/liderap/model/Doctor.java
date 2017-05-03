package com.example.marcin.liderap.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Marcin on 15.03.2017.
 */
@DatabaseTable(tableName = "doctors")
public class Doctor {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String lastName;

    @DatabaseField
    private String speciality;

    @DatabaseField(canBeNull = false, foreign = true)
    private Place place;


    public Doctor() {
    }

    public Doctor(Integer id, String name, String lastName, String speciality, Place place) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
        this.place = place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
