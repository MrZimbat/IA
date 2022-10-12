package com.example.ia.Modal.Medicine;

import java.io.Serializable;

public class Med implements Serializable {

    private String activeIngrediant;
    private String strength;
    private String type;
    private String country;
    private String maDate;
    private String maExpDate;
    private String maRewDate;
    private String maPrepDate;
    private String medID;
    private String medName;
    private String email;

    public Med(){

    }

    public Med(String activeIngrediant, String strength, String type, String country, String maDate, String maExpDate, String maRewDate, String maPrepDate, String medID, String medName, String email) {
        this.activeIngrediant = activeIngrediant;
        this.strength = strength;
        this.type = type;
        this.country = country;
        this.maDate = maDate;
        this.maExpDate = maExpDate;
        this.maRewDate = maRewDate;
        this.maPrepDate = maPrepDate;
        this.medID = medID;
        this.medName = medName;
        this.email = email;
    }


    public String getUserEmail() { return email; }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public String getActiveIngrediant() {
        return activeIngrediant;
    }

    public void setActiveIngrediant(String activeIngrediant) { this.activeIngrediant = activeIngrediant; }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMaDate() {
        return maDate;
    }

    public void setMaDate(String maDate) {
        this.maDate = maDate;
    }

    public String getMaExpDate() {
        return maExpDate;
    }

    public void setMaExpDate(String maExpDate) {
        this.maExpDate = maExpDate;
    }

    public String getMaRewDate() {
        return maRewDate;
    }

    public void setMaRewDate(String maRewDate) {
        this.maRewDate = maRewDate;
    }

    public String getMaPrepDate() {
        return maPrepDate;
    }

    public void setMaPrepDate(String maPrepDate) {
        this.maPrepDate = maPrepDate;
    }

    public String getMedID() {
        return medID;
    }

    public void setMedID(String medID) {
        this.medID = medID;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    @Override
    public String toString() {
        return "Med{" +
                "activeIngrediant='" + activeIngrediant + '\'' +
                ", strength='" + strength + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", maDate='" + maDate + '\'' +
                ", maExpDate='" + maExpDate + '\'' +
                ", maRewDate='" + maRewDate + '\'' +
                ", maPrepDate='" + maPrepDate + '\'' +
                ", medID='" + medID + '\'' +
                ", medName='" + medName + '\'' +
                '}';
    }
}

