package client.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="t_gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender")
    public long idGender;
    @Column(name = "description")
    public String description;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public int userCreate;
    @Column(name = "user_update")
    public int userUpdate;

}
