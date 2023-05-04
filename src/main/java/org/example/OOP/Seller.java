package org.example.OOP;

public class Seller extends User{
    private int AmountReceived;
    private int productId;

    Seller(int id, String name, String email, String phone, int Amount, int productId) {
        super(id, name, email, phone);
        this.AmountReceived = Amount;
        this.productId = productId;
    }
}
