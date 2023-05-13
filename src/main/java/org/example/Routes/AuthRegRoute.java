package org.example.Routes;
import static io.javalin.apibuilder.ApiBuilder.*;

import org.example.Database.DatabaseControl;
import org.example.OOP.ActiveSession;
import org.example.OOP.IRouteProvider;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.OOP.User;
import org.example.OOP.sessionsControl;

public class AuthRegRoute implements IRouteProvider {
	public static void ResponseToAuthenticatePost(Context authpost)
    {
        String Username = authpost.formParam("username");
        String Password = authpost.formParam("password");

        User foundUser = DatabaseControl.Database.getUser(Username, Password);

        if (foundUser != null) {
            ActiveSession sessionData = sessionsControl.InitiateSession(foundUser);
            System.out.println("Successfully Initiated session for user [" + foundUser.getID() + "] " + foundUser.getName());
            authpost.status(200).json(sessionData);
        } else {
            authpost.status(401);
        }
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
	
	public static void GetSessionData(Context ctx)
    {
        String sessionId = ctx.formParam("sessionId");
        System.out.println(sessionId);
        if (sessionsControl.isSessionActive(sessionId) == false) {
            ctx.result("session is inactive");
            return;
        }
        ActiveSession session = sessionsControl.getSessionFromId(sessionId);
        ctx.json(session);
    }

    public static void GetUserData(Context ctx)
    {
        String id = ctx.pathParam("id");
        int parsedId = Integer.parseInt(id);
        User user = DatabaseControl.Database.getUserByID(parsedId);
        ctx.json(user);
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
                path("/sessionData", () -> {
                   post(AuthRegRoute::GetSessionData);
                });
                path("/user-data/{id}", () -> {
                   get(AuthRegRoute::GetUserData);
                });
            });
       };
    }
}

