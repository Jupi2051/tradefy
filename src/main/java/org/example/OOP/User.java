package org.example.OOP;

public class User {
    private int id;
    private String name;
    private String Email;
    private String phone;

    public int GetID() {return this.id;}
    public String getName() {return this.name;}
    public String getEmail() {return this.Email;}
    public String getPhone() {return this.phone;}

    public User(int id, String name, String email, String phone)
    {
        this.id = id;
        this.name = name;
        this.Email = email;
        this.phone = phone;
    }
}
