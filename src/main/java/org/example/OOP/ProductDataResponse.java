package org.example.OOP;

public class ProductDataResponse {
    public final Bid[] allBids;
    public final Product product;
    public final User owner;

    public ProductDataResponse(Bid[] bids, Product product, User user)
    {
        this.allBids = bids;
        this.product = product;
        this.owner = user;
    }
}
