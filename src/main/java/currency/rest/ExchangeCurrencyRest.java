package currency.rest;

import currency.impl.ExchangeRateImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/currency/exchange")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExchangeCurrencyRest {
    @Inject
    ExchangeRateImpl exchangeRateImpl;

    @GET
    @Path("/list/all")
    public Response currencyListAll() {
        return Response.ok(exchangeRateImpl.listAllExchangeRate()).build();
    }

    @GET
    @Path("/find/currency/id")
    public Response findCurrencyId(JsonObject data) {
        return Response.ok(exchangeRateImpl.findByCurrencyId(data)).build();
    }

    @POST
    @Path("/save")
    public Response currencySave(JsonObject data) {
        return Response.ok(exchangeRateImpl.createExchangeRate(data)).build();
    }

    @PUT
    @Path("/update")
    public Response currencyUpdate(JsonObject data) {
        return Response.ok(exchangeRateImpl.updateExchangeRate(data)).build();
    }

    @POST
    @Path("/delete")
    public Response currencyDelete(JsonObject data) {
        return Response.ok(exchangeRateImpl.deleteExchangeRate(data)).build();
    }
}
