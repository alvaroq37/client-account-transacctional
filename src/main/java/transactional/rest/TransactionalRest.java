package transactional.rest;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import transactional.impl.TransactionalImpl;

@ApplicationScoped
@Transactional
@Path("rest/transactional")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionalRest {

    @Inject
    TransactionalImpl transactionalImpl;

    @POST
    @Path("/list/all")
    public Response transactionListAll(){
        return transactionalImpl.transactionListAll();
    }

    @POST
    @Path("/find/by/id")
    public Response transactionalFindById(JsonObject jsonDataTransactional){
        return transactionalImpl.transactionalFindById(jsonDataTransactional);
    }

    @POST
    @Path("/save")
    public Response transactionalSave(JsonObject jsonDataTransactional){
        return transactionalImpl.transactionalSave(jsonDataTransactional);
    }

    @POST
    @Path("/find/by/number/account")
    public Response transactionalFindByNumberAccount(JsonObject jsonDataTransactional){
        return transactionalImpl.transactionalFindByNumberAccount(jsonDataTransactional);
    }

    @PUT
    @Path("/update")
    public Response transactionalUpdate(JsonObject jsonDataTransactional){
        return transactionalImpl.transactionalUpdate(jsonDataTransactional);
    }

    @DELETE
    @Path("/delete")
    public Response transactionalDelete(JsonObject jsonDataTransactional){
        return transactionalImpl.transactionalDelete(jsonDataTransactional);
    }

}
