package org.example.Routes;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.example.OOP.IRouteProvider;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ProductRoutes implements IRouteProvider {
    public static void GetProductsRespond (Context GetProducts)
    {
        GetProducts.result("Products list");
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
