package com.example.fyp;

public class Admins {
    private String id,name,email, password;
    public Admins()
    {

    }

    public Admins(String id, String password,String name,String email) {
        this.id = id;
        this.password = password;
        this.email=email;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
