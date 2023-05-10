package org.example.OOP;

public class Bid {
    public int id;
    public int productId;
    public int userId;
    public double Amount;

    public int getId()
    {
        return this.id;
    }

    public int getProductId()
    {
        return this.productId;
    }

    public int getUserId()
    {
        return this.userId;
    }

    public double getAmount()
    {
        return this.Amount;
    }

    public Bid(int id, int productId, int userId, double Amount)
    {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.Amount = Amount;
    }
}
