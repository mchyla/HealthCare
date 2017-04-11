package com.example.marcin.healthcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class SchoolCoordinator implements Serializable{

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private String mail;
    @DatabaseField
    private String college;

    //@DatabaseField(foreign = true)
    //private College college;


    public SchoolCoordinator(String name, String lastName, String mail, String college) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.college = college;
    }

    public SchoolCoordinator(String name, String lastName, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
    }

    //public SchoolCoordinator(String mail, College college) {
    //    this.mail = mail;
     //   this.college = college;
    //}

    public SchoolCoordinator() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    //public College getCollege() {
    //    return college;
    //}

    //public void setCollege(College college) {
    //    this.college = college;
    //}
}
