package com.example.marcin.liderap.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mchyl on 10/04/2017.
 */
@DatabaseTable
public class Leader {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    // private ArrayList<College> collegeList;
    private String collage;
    @DatabaseField
    private String name;
    @DatabaseField
    private String lastName;
    @DatabaseField
    private int pin;
    @DatabaseField
    private String email;
    @DatabaseField
    private String city;
    @DatabaseField
    private String cityChanged;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String securityQuestion;
    @DatabaseField
    private String securityAnswer;

    public Leader(String collage, String name, String lastName, int pin, String email, String city,
                  String cityChanged, String phone, String securityQuestion, String securityAnswer) {
        this.collage = collage;
        this.name = name;
        this.lastName = lastName;
        this.pin = pin;
        this.email = email;
        this.city = city;
        this.cityChanged = cityChanged;
        this.phone = phone;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public Leader(String collage, String name, String lastName, int pin, String email,
                  String city, String cityChanged, String phone) {
        this.collage = collage;
        this.name = name;
        this.lastName = lastName;
        this.pin = pin;
        this.email = email;
        this.city = city;
        this.cityChanged = cityChanged;
        this.phone = phone;
    }

    public Leader() {
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityChanged() {
        return cityChanged;
    }

    public void setCityChanged(String cityChanged) {
        this.cityChanged = cityChanged;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}