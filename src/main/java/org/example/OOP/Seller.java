package org.example.OOP;

public class Seller extends User{
    int AmountReceived;
    int productId;

    public int getAmountReceived()
    {
        return this.AmountReceived;
    }

    public int getProductId()
    {
        return this.productId;
    }

    public Seller(int id, String name, String email, String phone, int Amount, int productId) {
        super(id, name, email, phone);
        this.AmountReceived = Amount;
        this.productId = productId;
    }
}
