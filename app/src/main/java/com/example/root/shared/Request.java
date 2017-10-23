package com.example.root.shared;

import org.parceler.Parcel;

/**
 * Created by root on 10/13/17.
 */
@Parcel
public class Request {
    private String name;
    private String hospital;
    private String bloodtype;
    private String contact;
    private String state;

    public Request(String name,String hospital,String bloodtype,String contact,String state){
        this.name=name;
        this.hospital=hospital;
        this.bloodtype=bloodtype;
        this.contact=contact;
        this.state=state;
    }
    public Request(){}

    public String getName() {
        return name;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public String getContact() {
        return contact;
    }

    public String getHospital() {
        return hospital;
    }

    public String getState() {
        return state;
    }
}
