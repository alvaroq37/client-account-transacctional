package account.dao.data;

import branch.dao.data.Branch;
import customer.dao.data.Customer;
import currency.dao.data.Currency;
import jakarta.persistence.*;
import product.dao.data.Product;
import java.util.Date;

@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    public long idAccount;
    @Column(name = "number_account")
    public Long numberAccount;
    @Column(name = "amount")
    public double amount;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public int userCreate;
    @Column(name = "user_update")
    public int userUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "client_id")
    public Customer customer;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    public Product product;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Currency.class)
    @JoinColumn(name = "currency_id")
    public Currency currency;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "branch_id")
    public Branch branch;

}
