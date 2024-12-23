package transactional.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="t_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    public long idTransaction;
    @Column(name = "description")
    public String description;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "userCreate")
    public int userCreate;
    @Column(name = "user_update")
    public int userUpdate;

}
