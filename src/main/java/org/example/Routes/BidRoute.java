package org.example.Routes;
import static io.javalin.apibuilder.ApiBuilder.*;
import org.example.OOP.IRouteProvider;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;


public class BidRoute implements IRouteProvider {

	
	public static void ResponseToBidPost(Context bidpost)
    {
        bidpost.result("Bid post example");
    }
	
	public static void ResponseToBidDelete(Context biddlt)
	{
	    biddlt.result("Bid delete example");
    }
	
	public static void ResponseToBidPatch(Context bidpatch)
	{
		bidpatch.result("Bid patch example");
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
