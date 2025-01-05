package client.rest;

import client.impl.ClientImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientRest {

    @Inject
    ClientImpl clientImpl;

    @POST
    @Path("/list/all")
    public Response clientListAll() {
        return Response.ok(clientImpl.clientListAll()).build();
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

    @POST
    @Path("/update")
    public Response clientUpdate(JsonObject data) {
        return Response.ok( clientImpl.clientUpdate(data)).build();
    }

    @POST
    @Path("/delete")
    public Response clientDelete(JsonObject data) {
        return Response.ok(clientImpl.clientDelete(data)).build();
    }
}
