package com.example.marcin.healthcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class SchoolCoordinator {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String mail;

    @DatabaseField(foreign = true)
    private College college;

    public SchoolCoordinator(String mail, College college) {
        this.mail = mail;
        this.college = college;
    }

    public SchoolCoordinator() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
