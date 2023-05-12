package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.Database.DatabaseControl;
import org.example.OOP.IRouteProvider;

import java.sql.Date;

import static io.javalin.apibuilder.ApiBuilder.*;
public class completePurchase implements IRouteProvider {

    //CP = Completed Purchase
    public static void PostCompletePurchase (Context ctx)
    {
        ctx.result ("complete-purchase");

        String product_ID = ctx.formParam("Product ID");
        Integer.parseInt(product_ID);
        String date = ctx.formParam("date");
        Date CurrentTime = new Date(System.currentTimeMillis());;
        String Amount = ctx.formParam("Amount");
        Double.parseDouble(Amount);
        String Buyer_ID = ctx.formParam("Buyer_ID");
        Integer.parseInt(Buyer_ID);
        String Seller_ID = ctx.formParam("Seller_ID");
        Integer.parseInt(Seller_ID);

        DatabaseControl.Database.addSalesCommission(Integer.parseInt(product_ID), CurrentTime,
                Double.parseDouble(Amount), Integer.parseInt(Buyer_ID), Integer.parseInt(Seller_ID));
        ctx.result ("complete-purchase");
    }

    public EndpointGroup GetRouteData() {
        return () -> {
            path ("/api", () -> {
                path("/complete-purchase", () -> post(completePurchase::PostCompletePurchase));
            });
        };
    }
}