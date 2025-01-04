package currency.dao.repository;

import currency.dao.data.Currency;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CurrencyRepository implements PanacheRepository<Currency> {
    public List<Currency> currencyListAll(){
        return listAll();
    }
    public Currency currencyFindById(long id){
        return find("id", id).firstResult();
    }
    public void currencySave(Currency currency){
        persist(currency);
    }
    public Long currencyDelete(long id){
        return delete("idCurrency", id);
    }
}
