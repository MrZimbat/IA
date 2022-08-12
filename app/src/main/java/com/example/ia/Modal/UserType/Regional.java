package com.example.ia.Modal.UserType;

public class Regional extends User{

    private String region;

    public Regional(String name, String email, String usertype, String uid, String region){
        super(name, email,usertype, uid);
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Regional{" +
                "region='" + region + '\'' +
                '}';
    }
}
