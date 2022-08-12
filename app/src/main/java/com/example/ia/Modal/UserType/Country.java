package com.example.ia.Modal.UserType;

public class Country extends User{

    private String country;

    public Country (String name, String email, String usertype, String uid, String country){
        super(name, email, usertype, uid);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Global{" +
                "country='" + country + '\'' +
                '}';
    }
}
