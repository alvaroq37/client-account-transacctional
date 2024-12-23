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
        return clientImpl.clientListAll();
    }

    @POST
    @Path("/find/by/id")
    public Response clientFindById(JsonObject jsonClient) {
        return clientImpl.clientFindById(jsonClient);
    }

    @POST
    @Path("/find/by/name")
    public Response clientFindByNames(JsonObject jsonClient) {
        return clientImpl.clientFindByName(jsonClient);
    }

    @POST
    @Path("/save")
    public Response clientSave(JsonObject jsonClient) {
        return clientImpl.citySave(jsonClient);
    }

    @POST
    @Path("/update")
    public Response clientUpdate(JsonObject jsonClient) {
        return clientImpl.clientUpdate(jsonClient);
    }

    @POST
    @Path("/delete")
    public Response clientDelete(JsonObject jsonClient) {
        return clientImpl.clientDelete(jsonClient);
    }
}
