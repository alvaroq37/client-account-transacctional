package currency.dao.data;

import jakarta.persistence.*;

import java.util.Date;

public class ExchangeCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exchange")
    public Long idExchange;
    @Column(name = "purchase_exchange_rate")
    public Double purchaseExchangeRate;
    @Column(name = "exchange_rate_sale")
    public Double exchangeRateSale;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public int userCreate;
    @Column(name = "user_update")
    public int userUpdate;

    @ManyToOne(targetEntity = Currency.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    public Currency currency;
}
