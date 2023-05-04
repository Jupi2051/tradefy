package org.example;

import io.javalin.Javalin;
import org.example.Routes.AuthRegRoute;
import org.example.Routes.BidRoute;
import org.example.Routes.ProductRoutes;
import org.example.Routes.completePurchase;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(/*config*/);

        app.get("/", ctx -> ctx.result("Hello World"));


        app.routes(new AuthRegRoute().GetRouteData());
        app.routes(new BidRoute().GetRouteData());
        app.routes(new ProductRoutes().GetRouteData());
        app.routes(new completePurchase().GetRouteData());

        app.start(7070);
    }
}
