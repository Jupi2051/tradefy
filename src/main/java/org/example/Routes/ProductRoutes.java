package org.example.Routes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.Database.DatabaseControl;
import org.example.OOP.IRouteProvider;
import org.example.OOP.Product;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ProductRoutes implements IRouteProvider {
    public static void GetProductsRespond (Context GetProducts)
    {
        Product[] ProductsList = DatabaseControl.Database.getProducts();
        GetProducts.json(ProductsList);
    }

    public static void PostProductRespond (Context PostProduct)
    {
        String productName = PostProduct.formParam("name");
        String OwnerId = PostProduct.formParam("OwnerId");
        Integer.parseInt(OwnerId);
        DatabaseControl.Database.addProduct(productName, Integer.parseInt(OwnerId) );
        PostProduct.result(productName);
    }

    public static void GetProductRespond (Context GetProduct)
    {
        String id = GetProduct.pathParam("id");
        Product foundProduct = DatabaseControl.Database.getProductById(Integer.parseInt(id));
        GetProduct.json(foundProduct);
    }

    public EndpointGroup GetRouteData() {
        return () -> {
            path ("/api", () -> {
                path ("/product", () -> {
                    post(ProductRoutes::PostProductRespond);
                    get("{id}", ProductRoutes::GetProductRespond);
                });
                path ("/products", () -> get(ProductRoutes::GetProductsRespond));
            });
        };
    }
}
