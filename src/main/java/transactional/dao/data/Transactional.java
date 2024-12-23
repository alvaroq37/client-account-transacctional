package transactional.dao.data;

import account.dao.data.Account;
import currency.dao.data.Currency;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="t_transactional")
public class Transactional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name = "date")
    public String date;
    @Column(name = "hour")
    public String hour;
    @Column(name = "amount")
    public long amount;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "userCreate")
    public int userCreate;
    @Column(name = "user_update")
    public int userUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    public Account account;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Currency.class)
    @JoinColumn(name = "currency_id")
    public Currency currency;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Transaction.class)
    @JoinColumn(name = "transaction_id")
    public Transaction transaction;
}
