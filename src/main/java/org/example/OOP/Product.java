package org.example.OOP;

import org.example.Database.DatabaseControl;

public class Product {
    int productId;
    String name;
    int ownerId;
    PRODUCT_STATUS status;

    public int getProductId()
    {
        return this.productId;
    }

    public String getName()
    {
        return this.name;
    }

    public int Owner()
    {
        return this.ownerId;
    }

    public Product(int id, int ownerId, String name, PRODUCT_STATUS status)
    {
        this.productId = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = status;
    }
}
