package account.rest;

import account.impl.AccountImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountRest {

    @Inject
    AccountImpl accountImpl;

    @POST
    @Path("/list/all")
    public Response accountListAll() {
        return Response.ok(accountImpl.accountListAll()).build();
    }

    @POST
    @Path("/find/by/id")
    public Response accountFindById(JsonObject data) {
        return Response.ok(accountImpl.findAccountById(data)).build();
    }

    @POST
    @Path("/find/by/number")
    public Response accountFindByNumber(JsonObject data) {
        return Response.ok(accountImpl.findAccountByNumber(data)).build();
    }

    @POST
    @Path("/find/by/client/id")
    public Response accountFindByClientId(JsonObject data) {
        return Response.ok(accountImpl.findAccountByClientId(data)).build();
    }

    @POST
    @Path("/save")
    public Response accountSave(JsonObject data) {
        return Response.ok(accountImpl.createAccount(data)).build();
    }

    @POST
    @Path("/update")
    public Response accountUpdate(JsonObject data) {
        return Response.ok(accountImpl.updateAccount(data)).build();
    }

    @POST
    @Path("/delete")
    public Response accountDelete(JsonObject data) {
        return Response.ok(accountImpl.deleteAccount(data)).build();
    }
}
