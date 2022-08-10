package com.example.ia.Modal.UserType;

public class User {

    private String name;
    private String email;
//    private String password;
    private String Usertype;
    private String uid;

    public User() {

    }

    public User(String name, String email, String usertype,String uid){
        this.name = name;
        this.email = email;
        this.uid = uid;
        Usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return Usertype;
    }

    public void setUsertype(String usertype) {
        Usertype = usertype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Usertype='" + Usertype + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
