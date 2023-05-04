package org.example;

import io.javalin.Javalin;
import org.example.Database.Database;
import org.example.Routes.AuthRegRoute;
import org.example.Routes.BidRoute;
import org.example.Routes.ProductRoutes;
import org.example.Routes.completePurchase;

public class App {
    public static void main(String[] args) {
        Database.DatabaseControl.InitilizeDatabase();

        Javalin server = Javalin.create(/*config*/);

        server.get("/", ctx -> ctx.result("Hello World"));


        server.routes(new AuthRegRoute().GetRouteData());
        server.routes(new BidRoute().GetRouteData());
        server.routes(new ProductRoutes().GetRouteData());
        server.routes(new completePurchase().GetRouteData());
        server.start(7070);
    }
}
