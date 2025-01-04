package currency.dao.repository;

import currency.dao.data.ExchangeRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ExchangeRateRepository implements PanacheRepository<ExchangeRate> {

    public List<ExchangeRate> listAllExchangeRates(){
        return listAll();
    }

    public List<ExchangeRate> findExchangeByCurrencyId(Long currencyId){
        return find("currency_id", currencyId).stream().toList();
    }

    public void createExchangeRate(ExchangeRate exchangeRate){
        persist(exchangeRate);
    }

    public Long deleteExchangeRate(Long exchangeRateId){
        return delete("idExchangeRate", exchangeRateId);
    }
}
