package customer.rest;

import customer.impl.ClientImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRest {

    @Inject
    ClientImpl clientImpl;

    @POST
    @Path("/list/all")
    public Response clientListAll() {
        //Response response = Response.ok(clientImpl.clientListAll()).build();
        return clientImpl.clientListAll();
    }

    @POST
    @Path("/find/by/id")
    public Response clientFindById(JsonObject data) {
        return Response.ok(clientImpl.clientFindById(data)).build();
    }

    @POST
    @Path("/find/by/name")
    public Response clientFindByNames(JsonObject data) {
        return Response.ok( clientImpl.clientFindByName(data)).build();
    }
    @POST
    @Path("/find/by/document/number")
    public Response clientFindByDocumentNumber(JsonObject data) {
        return Response.ok( clientImpl.clientFindByDocumentNumber(data)).build();
    }

    @POST
    @Path("/save")
    public Response clientSave(JsonObject data) {
        return Response.ok(clientImpl.clientSave(data)).build();
    }

    @PUT
    @Path("/update")
    public Response clientUpdate(JsonObject data) {
        return Response.ok( clientImpl.clientUpdate(data)).build();
    }

    @DELETE
    @Path("/delete")
    public Response clientDelete(JsonObject data) {
        return Response.ok(clientImpl.clientDelete(data)).build();
    }
}
