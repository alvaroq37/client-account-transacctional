package currency.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exchange_rate")
    public Long idExchangeRate;
    @Column(name = "purchase_exchange_rate")
    public Double purchaseExchangeRate;
    @Column(name = "exchange_rate_sale")
    public Double exchangeRateSale;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;

    @ManyToOne(targetEntity = Currency.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    public Currency currency;
}
