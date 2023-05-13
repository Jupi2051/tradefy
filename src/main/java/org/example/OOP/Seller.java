package org.example.OOP;

public class Seller extends User{
    int amountReceived;
    int productId;

    public int getProductId()
    {
        return this.productId;
    }
    public int getAmountReceived() {return this.amountReceived;}

    public Seller(int id, String name, String email, String phone, int Amount, int productId) {
        super(id, name, email, phone);
        this.amountReceived = Amount;
        this.productId = productId;
    }
}
