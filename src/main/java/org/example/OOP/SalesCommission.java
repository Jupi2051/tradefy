package org.example.OOP;

import java.util.Date;

public class SalesCommission {
    public final int id;
    public final Date date;
    public final float amount;
    public final int buyerId;
    public final int sellerId;

    SalesCommission(int id, Date date, int amount, int buyerId, int sellerId)
    {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
    }
}
