package org.example.OOP;

import java.util.Date;

public class SalesCommission {
    int id;
    Date date;
    float amount;
    int buyerId;
    int sellerId;

    public int getId()
    {
        return this.id;
    }

    public Date getDate()
    {
        return this.date;
    }

    public float getAmount()
    {
        return this.amount;
    }

    public int getBuyerID()
    {
        return this.buyerId;
    }

    public int getSellerId()
    {
        return this.sellerId;
    }

    public SalesCommission(int id, Date date, int amount, int buyerId, int sellerId)
    {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
    }
}
