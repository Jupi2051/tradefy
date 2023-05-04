package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.Database.Database;
import org.example.OOP.IRouteProvider;
import org.example.OOP.Product;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ProductRoutes implements IRouteProvider {
    public static void GetProductsRespond (Context GetProducts)
    {
        Product[] ProductsList = Database.DatabaseControl.getProducts();
        GetProducts.json(ProductsList);
    }

    public static void PostProductRespond (Context PostProduct)
    {
        PostProduct.result("Product post");
    }

    public static void GetProductRespond (Context GetProduct)
    {
        GetProduct.result("The product");
    }

    public static void DeleteProductRespond (Context DeleteProduct)
    {
        DeleteProduct.result("Product deleted");
    }
    public EndpointGroup GetRouteData() {
        return () -> {
            path ("/api", () -> {
                path("/product", () -> {
                    post(ProductRoutes::PostProductRespond);
                    get(ProductRoutes::GetProductRespond);
                    delete(ProductRoutes::DeleteProductRespond);
                });
                path ("/products", () -> get(ProductRoutes::GetProductsRespond));
            });
        };
    }
}
