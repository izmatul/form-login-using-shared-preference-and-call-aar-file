package com.example.izmatulfarihah.myapplication;

/**
 * Created by izmatul.farihah on 25/09/2014.
 */
public class Contact {

    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String password;

    public Contact() {
    }

    public Contact(int id, String name, String email, String phone_number, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public Contact(String name, String email, String phone_number, String password) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
