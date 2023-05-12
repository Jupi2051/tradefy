package org.example;

import io.javalin.Javalin;
import io.javalin.config.PrivateConfig;
import io.javalin.config.StaticFilesConfig;
import io.javalin.http.staticfiles.Location;
import org.example.Database.DatabaseControl;
import org.example.Routes.*;

import java.util.Date;

public class App {
    public static String FrontEndDirectory = "C:\\Users\\Jupii\\Desktop\\Frontend";

    public static void main(String[] args) {
        DatabaseControl.Database.InitilizeDatabase();

        Javalin server = Javalin.create(config -> {
            config.routing.ignoreTrailingSlashes = true;
            config.routing.treatMultipleSlashesAsSingleSlash = true;
            config.staticFiles.add((staticFiles) -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = FrontEndDirectory;
                staticFiles.location = Location.EXTERNAL;
                staticFiles.precompress = false;
            });
        });

        server.routes(new AuthRegRoute().GetRouteData());
        server.routes(new BidRoute().GetRouteData());
        server.routes(new ProductRoutes().GetRouteData());
        server.routes(new completePurchase().GetRouteData());
        server.routes(new MainRoute().GetRouteData());

        server.start(7070);
    }
}
