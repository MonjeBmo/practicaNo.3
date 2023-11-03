package com.bmo.practica5.models;

public class Persona {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String Email;

    public Persona() {
    }

    public Persona(String name, String address, String phone, String email, int id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.Email = email;
        this.id = id;
    }

    public Persona(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.Email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
