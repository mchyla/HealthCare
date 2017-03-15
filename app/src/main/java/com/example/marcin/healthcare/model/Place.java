package com.example.marcin.healthcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Marcin on 15.03.2017.
 */
@DatabaseTable(tableName = "place")
public class Place {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String address;


    public Place() {
    }

    // getters, setters and other constructors


    public Place(Integer id, String name, String doctorName, Date date) {
        this.id = id;
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
