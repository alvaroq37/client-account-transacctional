package transactional.rest;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import transactional.impl.TransactionImpl;

@ApplicationScoped
@Transactional
@Path("rest/transaction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionRest {

    @Inject
    TransactionImpl transactionImpl;

    @POST
    @Path("/list/all")
    public Response transactionListAll(){
        return Response.ok(transactionImpl.transactionListAll()).build();
    }

    @POST
    @Path("/find/by/id")
    public Response transactionFindById(JsonObject data){
        return Response.ok(transactionImpl.transactionFindById(data)).build();
    }

    @POST
    @Path("/save")
    public Response transactionalSave(JsonObject data){
        return Response.ok(transactionImpl.saveTransaction(data)).build();
    }

    @PUT
    @Path("/update")
    public Response transactionUpdate(JsonObject data){
        return Response.ok(transactionImpl.updateTransaction(data)).build();
    }

    @DELETE
    @Path("/delete")
    public Response transactionDelete(JsonObject data){
        return Response.ok(transactionImpl.deleteTransaction(data)).build();
    }

}
