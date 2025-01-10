package product.dao.data;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    public Long idProduct;
    @Column(name = "description")
    public String description;
    @Column(name = "annual_interest")
    public Double annualInterest;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "userCreate")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;
}
