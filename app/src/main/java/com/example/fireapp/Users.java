package com.example.fireapp;

public class Users
{
    private String name;
    private String email;
    private String phone;

    public  Users(){

    }

    public Users(String name,String email,String phone){
        this.name=name;
        this.email=email;
        this.phone=email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
