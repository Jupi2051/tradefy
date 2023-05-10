package org.example.Routes;
import static io.javalin.apibuilder.ApiBuilder.*;

import org.example.Database.DatabaseControl;
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
        String newUserName = regpost.formParam("name");
        String newUserPhoneNumber = regpost.formParam("phone");
        String newUserEmail = regpost.formParam("email");
        String newUserPassword = regpost.formParam("password");

        DatabaseControl.Database.addUser(newUserName, newUserPhoneNumber, newUserPassword, newUserEmail);
        regpost.status(200);
    }
	
	
	public EndpointGroup GetRouteData()
    { /*tradefy.com/api/register*/
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

