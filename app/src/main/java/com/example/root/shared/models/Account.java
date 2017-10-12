package com.example.root.shared.models;

/**
 * Created by mwas on 10/11/17.
 */

public class Account {
    String name;
    String dateOfBirth;
    String sex;
    String bloodType;
    String residence;
    String email;

    //empty constructor to be used by firebase
    public Account(){}

    public Account(String name, String dateOfBirth, String sex, String bloodType, String residence, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.bloodType = bloodType;
        this.residence = residence;
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

    public String getEmail() {
        return email;
    }

}
