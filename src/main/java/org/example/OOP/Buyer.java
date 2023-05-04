package org.example.OOP;

public class Buyer extends User {
    private int amountPaid;
    private int productId;

    Buyer(int id, String name, String email, String phone, int AmountPaid, int productId) {
        super(id, name, email, phone);
        this.amountPaid = AmountPaid;
        this.productId = productId;
    }
}
