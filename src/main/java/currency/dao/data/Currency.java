package currency.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_currency")
    public Long idCurrency;
    @Column(name = "description")
    public String description;
    @Column(name = "abbreviation")
    public String abbreviation;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;
}
