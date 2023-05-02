package org.example;

import io.javalin.Javalin;
import org.example.Routes.TestRoute;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(/*config*/);

        app.get("/", ctx -> ctx.result("Hello World"));

        app.routes(new TestRoute().GetRouteData());

        app.start(7070);

    }
}
