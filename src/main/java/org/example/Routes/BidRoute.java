package org.example.Routes;
import static io.javalin.apibuilder.ApiBuilder.*;

import org.example.Database.DatabaseControl;
import org.example.OOP.IRouteProvider;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;


public class BidRoute implements IRouteProvider {
	public static void ResponseToBidPost(Context bidpost)
    {
        String newProductID = bidpost.formParam("product_id");
        String newUserID = bidpost.formParam("user_id");
        String newAmount = bidpost.formParam("amount");

        DatabaseControl.Database.addBids(newProductID, Integer.parseInt(newUserID), Double.parseDouble(newAmount));
        bidpost.status(200);
    }
	
	public static void ResponseToBidDelete(Context biddlt)
	{

        String ProductID = biddlt.pathParam("product_id");
        String UserID = biddlt.pathParam("user_id");

        DatabaseControl.Database.DeleteBid(ProductID, Integer.parseInt(UserID));
        biddlt.status(200);
    }
	
	public static void ResponseToBidPatch(Context bidpatch)
	{
        String ProductID = bidpatch.formParam("product_id");
        String UserID = bidpatch.formParam("user_id");
        String Amount = bidpatch.formParam("amount");

        DatabaseControl.Database.UpdateBid(ProductID, Integer.parseInt(UserID), Double.parseDouble(Amount));
        bidpatch.status(200);
	}
	

    public EndpointGroup GetRouteData()
    {
        return () -> {
            path("/api", () -> {
                path("/bid", () -> {
                    post(BidRoute::ResponseToBidPost);
                    delete("{id}", BidRoute::ResponseToBidDelete);
                    patch(BidRoute::ResponseToBidPatch);
                });
            });
       };
    }
}
