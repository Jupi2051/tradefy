package org.example.OOP;

public class Seller extends User{
    public final int AmountReceived;
    public final int productId;

    public Seller(int id, String name, String email, String phone, int Amount, int productId) {
        super(id, name, email, phone);
        this.AmountReceived = Amount;
        this.productId = productId;
    }
}
