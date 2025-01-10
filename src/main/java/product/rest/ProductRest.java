package product.rest;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import product.impl.ProductImpl;

@Transactional
@Path("rest/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRest {
    @Inject
    ProductImpl productImpl;

    @POST
    @Path("/list/all")
    public Response productListAll() {
        return Response.ok(productImpl.productListAll()).build();
    }

    @POST
    @Path("/find/by/id")
    public Response productFindById(JsonObject data){
        return Response.ok(productImpl.productFindById(data)).build();
    }
    @POST
    @Path("/save")
    public Response saveProduct(JsonObject data){
        return Response.ok(productImpl.saveProduct(data)).build();
    }
    @POST
    @Path("/update")
    public Response updateProduct(JsonObject data){
        return Response.ok(productImpl.updateProduct(data)).build();
    }
    @POST
    @Path("/delete")
    public Response deleteProduct(JsonObject data){
        return Response.ok(productImpl.deleteProduct(data)).build();
    }
}
