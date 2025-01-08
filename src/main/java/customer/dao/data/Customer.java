package customer.dao.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;
    @Column(name = "document_number")
    public String documentNumber;
    @Column(name = "name")
    public String name;
    @Column(name = "paternal_surname")
    public String paternalSurname;
    @Column(name = "maternal_surname")
    public String maternalSurname;
    @Column(name="date_of_birth")
    public Date dateOfBirth;
    @Column(name = "date_create")
    public Date dateCreate;
    @Column(name = "date_update")
    public Date dateUpdate;
    @Column(name = "user_create")
    public Integer userCreate;
    @Column(name = "user_update")
    public Integer userUpdate;

    @ManyToOne(targetEntity = Gender.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "gender_id")
    public Gender gender;
    @ManyToOne(targetEntity = Document.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "document_id")
    public Document document;
}
