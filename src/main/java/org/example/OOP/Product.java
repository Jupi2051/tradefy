package org.example.OOP;

public class Product {
    private int productId;
    private String name;
    public PRODUCT_STATUS status;

    int getProductId()
    {
        return this.productId;
    }

    String getProductName()
    {
        return this.name;
    }

    Product (int id, String name, PRODUCT_STATUS status)
    {
        this.productId = id;
        this.name = name;
        this.status = status;
    }

    User GetOwner()
    {
        return new User(0, "sample", "sample@gmail.com", "32365623");
    }
}
