package org.example.Routes;
import static io.javalin.apibuilder.ApiBuilder.*;
import org.example.OOP.IRouteProvider;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;

public class AuthRegRoute implements IRouteProvider {
	public static void ResponseToAuthenticatePost(Context authpost)
    {
        authpost.result("Auth post example");
    }
	
	public static void ResponseToRegisterPost(Context regpost)
	{
	    regpost.result("Reg post example");
    }
	
	
	public EndpointGroup GetRouteData()
    {
        return () -> {
            path("/api", () -> {
                path("/authenticate", () -> {
                    post(AuthRegRoute::ResponseToAuthenticatePost);
                });
                path("/register", () -> {
                    post(AuthRegRoute::ResponseToRegisterPost);
                });
            });
       };
    }
}

