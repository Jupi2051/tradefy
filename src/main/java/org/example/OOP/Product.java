package org.example.OOP;

public class Product {
    public final int productId;
    public final String name;
    public final int ownerId;
    public PRODUCT_STATUS status;

    public Product(int id, int ownerId, String name, PRODUCT_STATUS status)
    {
        this.productId = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = status;
    }

    User GetOwner()
    {
        return new User(0, "sample", "sample@gmail.com", "32365623");
    }
}
