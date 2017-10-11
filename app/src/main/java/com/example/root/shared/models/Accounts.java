package com.example.root.shared.models;

/**
 * Created by mwas on 10/11/17.
 */

public class Accounts {
    String name;
    String dateOfBirth;
    String sex;
    String bloodType;
    String residence;
//    Boolean donated;
    String email;

    //empty constructor to be used by firebase
    public Accounts(){}

    public Accounts(String name, String dateOfBirth, String sex, String bloodType, String residence, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.bloodType = bloodType;
        this.residence = residence;
//        this.donated = donated;
        this.email = email;
    }

    //getter methods
    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getResidence() {
        return residence;
    }

//    public Boolean getDonated() {
//        return donated;
//    }

    public String getEmail() {
        return email;
    }

}
