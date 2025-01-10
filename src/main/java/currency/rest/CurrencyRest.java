package currency.rest;

import currency.impl.CurrencyImpl;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Transactional
@Path("rest/currency")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyRest {


    @Inject
    CurrencyImpl currencyImpl;

    @POST
    @Path("/list/all")
    public Response currencyListAll() {
        return currencyImpl.currencyListAll();
    }

    @POST
    @Path("/find/by/id")
    public Response currencyFindById(JsonObject data) {
        return currencyImpl.currencyFindById(data);
    }
    
    @POST
    @Path("/save")
    public Response currencySave(JsonObject data) {
        return currencyImpl.currencySave(data);
    }

    @PUT
    @Path("/update")
    public Response currencyUpdate(JsonObject data) {
        return currencyImpl.currencyUpdate(data);
    }

    @POST
    @Path("/delete")
    public Response currencyDelete(JsonObject data) {
        return currencyImpl.currencyDelete(data);
    }
}
