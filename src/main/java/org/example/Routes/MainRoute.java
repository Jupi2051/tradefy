package org.example.Routes;

import com.sun.tools.javac.Main;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import org.example.App;
import org.example.OOP.IRouteProvider;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.patch;

public class MainRoute implements IRouteProvider
{
    public static String getHTMLFileData(String fileName)
    {
        try {
            String htmlData = Files.readString(Paths.get(App.FrontEndDirectory + "\\HTML\\"+fileName), Charset.defaultCharset());
            return htmlData;
        } catch (Exception e)
        {
            System.out.println("Error occured while fetching page.");
        }
        return "<body>Error occured while getting page.</body>";

    }

    public static void HomePage(Context ctx)
    {
        ctx.html(getHTMLFileData("HOME.html"));
    }

    public static void ProductPage(Context ctx)
    {
        ctx.html(getHTMLFileData("PRODUCTPAGE.html"));
    }

    public static void GuidelinesPage(Context ctx)
    {
        ctx.html(getHTMLFileData("GUIDELINES.html"));
    }

    public static void LoginPage(Context ctx)
    {
        ctx.html(getHTMLFileData("LOGIN.html"));
    }

    public static void RegisterPage(Context ctx)
    {
        ctx.html(getHTMLFileData("REGISTER.html"));
    }

    public static void SellPage(Context ctx)
    {
        ctx.html(getHTMLFileData("SELL.html"));
    }

    public static void AboutPage(Context ctx)
    {
        ctx.html(getHTMLFileData("ABOUT.html"));
    }

    public EndpointGroup GetRouteData()
    {
        return () -> {
            get("/", MainRoute::HomePage);
            get("/about", MainRoute::AboutPage);
            get("/guidelines", MainRoute::GuidelinesPage);
            get("/login", MainRoute::LoginPage);
            get("/register", MainRoute::RegisterPage);
            get("/product/{id}", MainRoute::ProductPage);
            get("/sell", MainRoute::SellPage);
        };
    }
}
