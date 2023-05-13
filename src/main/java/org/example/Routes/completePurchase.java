package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.Database.DatabaseControl;
import org.example.OOP.IRouteProvider;
import org.example.OOP.PRODUCT_STATUS;
import org.example.OOP.Product;

import java.sql.Date;
import static io.javalin.apibuilder.ApiBuilder.*;
public class completePurchase implements IRouteProvider {

    //CP = Completed Purchase
    public static void PostCompletePurchase (Context ctx)
    {
        String product_ID = ctx.formParam("Product_ID");
        String Amount = ctx.formParam("Amount");
        String Buyer_ID = ctx.formParam("Buyer_ID");
        String Seller_ID = ctx.formParam("Seller_ID");

        int ProductId = Integer.parseInt(product_ID);
        Product productData = DatabaseControl.Database.getProductById(ProductId);

        if (productData.getProductStatus() == PRODUCT_STATUS.SOLD || productData.getProductStatus() == PRODUCT_STATUS.CANCELLED) {
            ctx.status(400);
            return;
        }

        Date CurrentTime = new Date(System.currentTimeMillis());;

        DatabaseControl.Database.updateProductStatus(ProductId, PRODUCT_STATUS.SOLD);
        DatabaseControl.Database.addSalesCommission(ProductId, CurrentTime,
                Double.parseDouble(Amount), Integer.parseInt(Buyer_ID), Integer.parseInt(Seller_ID));
        ctx.status(200);
    }

    public EndpointGroup GetRouteData() {
        return () -> {
            path ("/api", () -> {
                path("/complete-purchase", () -> {
                    post(completePurchase::PostCompletePurchase);
                });
            });
        };
    }
}