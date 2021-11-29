package com.example.bus_passengers_assigment;

public class User {
    String fName, Address, NationalID, MobileNumber;


    public User(String fName, String address, String idnum, String phoneNumber) {
        this.fName = fName;
        Address = address;
        this.NationalID = idnum;
        this.MobileNumber = phoneNumber;


    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }





    public String getPhoneNumber() {
        return MobileNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.MobileNumber = phoneNumber;
    }



    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public User() {


    }
}