package com.example.marcin.healthcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mchyl on 27/04/2017.
 */
@DatabaseTable
public class Pin {

    @DatabaseField
    private int pin;
    @DatabaseField
    private String email;

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin() {
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }


}
