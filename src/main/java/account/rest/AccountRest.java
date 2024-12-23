package account.rest;

import account.impl.AccountImpl;
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
@Path("rest/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountRest {

    @Inject
    AccountImpl accountImpl;

    @POST
    @Path("/list/all")
    public Response clientListAll() {
        return accountImpl.accountListAll();
    }

    @POST
    @Path("/find/by/id")
    public Response clientFindById(JsonObject jsonAccount) {
        return accountImpl.accountFindByClient(jsonAccount);
    }

    @POST
    @Path("/find/by/name")
    public Response clientFindByNames(JsonObject jsonAccount) {
        return accountImpl.accountFindByNumberAccount(jsonAccount);
    }

    @POST
    @Path("/save")
    public Response clientSave(JsonObject jsonAccount) {
        return accountImpl.accountSave(jsonAccount);
    }

    @POST
    @Path("/update")
    public Response clientUpdate(JsonObject jsonAccount) {
        return accountImpl.accountUpdate(jsonAccount);
    }

    @POST
    @Path("/delete")
    public Response clientDelete(JsonObject jsonAccount) {
        return accountImpl.accountDelete(jsonAccount);
    }
}
