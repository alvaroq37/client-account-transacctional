package branch.rest;

import branch.impl.BranchImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rest/branch")
@Transactional
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BranchRest {
    @Inject
    BranchImpl agencyImpl;

    @GET
    @Path("/find/all")
    public Response agencyFindAll() {
        return agencyImpl.agencyListAll();
    }

    @GET
    @Path("/find/by/id")
    public Response agencyFindById(JsonObject jsonDataAgency){
        return agencyImpl.agencyFindById(jsonDataAgency);
    }

    @GET
    @Path("/find/by/name")
    public Response agencyFindByName(JsonObject jsonDataAgency){
        return agencyImpl.agencyFindByName(jsonDataAgency);
    }

    @POST
    @Path("/save")
    public Response agencySave(JsonObject jsonDataAgency) {
        return agencyImpl.agencySave(jsonDataAgency);
    }

    @PUT
    @Path("/update")
    public Response agencyUpdate(JsonObject jsonDataAgency) {
        return agencyImpl.agencyUpdate(jsonDataAgency);
    }

    @DELETE
    @Path("/delete")
    public Response agencyDelete(JsonObject jsonDataAgency) {
        return agencyImpl.agencyDelete(jsonDataAgency);
    }
}
