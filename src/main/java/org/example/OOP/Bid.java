package org.example.OOP;

public class Bid {
    public final int id;
    public final int productId;
    public final int userId;
    public final int Amount;

    public Bid(int id, int productId, int userId, int Amount)
    {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.Amount = Amount;
    }
}
