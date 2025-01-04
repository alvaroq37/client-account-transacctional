package currency.impl;

import currency.dao.data.Currency;
import currency.dao.data.ExchangeRate;
import currency.dao.repository.CurrencyRepository;
import currency.dao.repository.ExchangeRateRepository;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ExchangeRateImpl {
    Messages messages = new Messages();
    @Inject
    ExchangeRateRepository exchangeRateRepository;
    @Inject
    CurrencyRepository currencyRepository;

    public JsonObject listAllExchangeRate() {
        try {
            List<ExchangeRate> exchangeRates = exchangeRateRepository.listAllExchangeRates();
            if (!exchangeRates.isEmpty()) {
                return new JsonObject().put("response", exchangeRates);
            }
            return new JsonObject().put("response", "No se encontraron tipo de cambio");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject findByCurrencyId(JsonObject data) {
        try {
            Long currencyId = data.getLong("currencyId");
            List<ExchangeRate> exchangeRates = exchangeRateRepository.findExchangeByCurrencyId(currencyId);
            if (!exchangeRates.isEmpty()) {
                return new JsonObject().put("response", exchangeRates);
            }
            return new JsonObject().put("response", "No se encontraron datos para la moneda ingresada");
        } catch (Exception e) {
            return messages.messageError();
        }

    }

    public JsonObject createExchangeRate(JsonObject data){
        try{
            JsonObject currencyData = data.getJsonObject("currency");
            Currency currency = currencyRepository.currencyFindById(currencyData.getLong("currencyId"));
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.exchangeRateSale = data.getDouble("exchangeRateSale");
            exchangeRate.purchaseExchangeRate = data.getDouble("purchaseExchangeRate");
            exchangeRate.userCreate = data.getInteger("userCreate");
            exchangeRate.dateCreate = new Date();
            exchangeRate.currency = currency;
            exchangeRateRepository.createExchangeRate(exchangeRate);
            return new JsonObject().put("response", "Tipo de cambio registrado correctamente");
        }catch (Exception e){
            return messages.messageError();
        }
    }

    public JsonObject deleteExchangeRate(JsonObject data){
        try{
            Long exchangeRateId = data.getLong("exchangeRateId");
            if(exchangeRateId > 0){
                if(exchangeRateRepository.deleteExchangeRate(exchangeRateId) > 0){
                    return new JsonObject().put("response", "Tipo de cambio eliminado correctamente");
                }
                return new JsonObject().put("response", "No se puede eliminar el tipo de cambio enviado");
            }
            return new JsonObject().put("response", "La información enviada no correspon a un Tipo de Cambio valido");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject updateExchangeRate(JsonObject data){
        try{
            JsonObject currencyData = data.getJsonObject("currency");
            Currency currency = currencyRepository.currencyFindById(currencyData.getLong("currencyId"));
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.idExchangeRate = data.getLong("idExchangeRate");
            exchangeRate.exchangeRateSale = data.getDouble("exchangeRateSale");
            exchangeRate.purchaseExchangeRate = data.getDouble("purchaseExchangeRate");
            exchangeRate.userCreate = data.getInteger("userCreate");
            exchangeRate.dateUpdate = new Date();
            exchangeRate.currency = currency;
            exchangeRateRepository.createExchangeRate(exchangeRate);
            return new JsonObject().put("response", "Tipo de cambio actualizado correctamente");
        }catch (Exception e){
            return messages.messageError();
        }
    }


}
