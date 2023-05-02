package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.OOP.IRouteProvider;


import static io.javalin.apibuilder.ApiBuilder.*;

public class TestRoute implements IRouteProvider {


    public static void GetResult(Context test)
    {
        test.result("Testing word");
    }

    public EndpointGroup GetRouteData()
    {
        return () -> path("/test", () -> get(TestRoute::GetResult));
    }
}
