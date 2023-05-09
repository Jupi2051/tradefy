package org.example.OOP;

public class User {
    private int id;
    private String name;
    private String Email;
    private String phone;

    int GetID() {return this.id;}
    String getName() {return this.name;}
    String getEmail() {return this.Email;}
    String getPhone() {return this.phone;}

    public User(int id, String name, String email, String phone)
    {
        this.id = id;
        this.name = name;
        this.Email = email;
        this.phone = phone;
    }
}
