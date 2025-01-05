package customer.rest;

import customer.impl.GenderImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/gender")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GenderRest {

    @Inject
    GenderImpl genderImpl;

    @POST
    @Path("/list/all")
    public Response genderListAll() {
        return Response.ok(genderImpl.genderListAll()).build();
    }
    @POST
    @Path("/find/by/id")
    public Response genderFindById(JsonObject data) {
        return Response.ok(genderImpl.findGenderById(data)).build();
    }
    @POST
    @Path("/save")
    public Response genderCreate(JsonObject data) {
        return Response.ok(genderImpl.createGender(data)).build();
    }
    @POST
    @Path("/update")
    public Response genderUpdate(JsonObject data) {
        return Response.ok(genderImpl.updateGender(data)).build();
    }
    @POST
    @Path("/delete")
    public Response genderDelete(JsonObject data) {
        return Response.ok(genderImpl.deleteGender(data)).build();
    }

}
