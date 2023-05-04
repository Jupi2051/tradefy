package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.OOP.IRouteProvider;
import static io.javalin.apibuilder.ApiBuilder.*;
public class completePurchase implements IRouteProvider {

    //CP = Completed Purchase
    public static void PostCompletePurchase (Context ctx)
    {
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