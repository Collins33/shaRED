package com.example.root.shared;

import org.parceler.Parcel;

/**
 * Created by root on 10/13/17.
 */
@Parcel
public class Request {

    private String hospital;
    private String bloodtype;
    private String state;
    private String contact;

    public Request(String hospital,String bloodtype,String state,String contact){

        this.hospital=hospital;
        this.bloodtype=bloodtype;

        this.state=state;
        this.contact=contact;
    }
    public Request(){}






    public String getHospital() {
        return hospital;
    }
    public String getBloodtype() {
        return bloodtype;
    }


    public String getState() {
        return state;
    }

    public String getContact() {
        return contact;
    }
}
