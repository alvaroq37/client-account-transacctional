package branch.dao.data;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="t_branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name="name")
    public String name;
    @Column(name = "address")
    public String address;
    @Column(name = "phone")
    public Integer phone;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;
    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    public City city;
    @ManyToOne(targetEntity = Enterprise.class, fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "enterprise_id")
    public Enterprise enterprise;
}

