package customer.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long idDocument;
    @Column(name = "description")
    public String description;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;
}
