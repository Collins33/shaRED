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

    public Request(String hospital,String bloodtype,String state){

        this.hospital=hospital;
        this.bloodtype=bloodtype;

        this.state=state;
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
}
