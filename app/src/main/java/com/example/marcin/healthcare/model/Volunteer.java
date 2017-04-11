package com.example.marcin.healthcare.model;

import android.support.v7.graphics.drawable.DrawerArrowDrawable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Volunteer {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String mail;

    public Volunteer(String mail) {
        this.mail = mail;
    }

    public Volunteer() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
