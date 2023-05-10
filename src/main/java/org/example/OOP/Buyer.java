package org.example.OOP;

public class Buyer extends User {
    int amountPaid;
    int productId;

    public int getProductId()
    {
        return this.productId;
    }

    public int getAmountPaid()
    {
        return this.amountPaid;
    }

    public Buyer(int id, String name, String email, String phone, int AmountPaid, int productId) {
        super(id, name, email, phone);
        this.amountPaid = AmountPaid;
        this.productId = productId;
    }
}
