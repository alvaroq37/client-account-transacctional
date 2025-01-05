package customer.dao.repository;

import customer.dao.data.Gender;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GenderRepository implements PanacheRepository<Gender> {
    public List<Gender> genderListAll() {
        return listAll();
    }

    public Gender genderFindById(long id) {
        return find("idGender", id).firstResult();
    }

    public Gender genderFindByDescription(String description) {
        return find("description", description).firstResult();
    }

    public void genderSave(Gender gender) {
        persist(gender);
    }

    public Long genderDelete(long id) {
        return delete("idGender", id);
    }

    public void genderUpdate(Gender gender) {
        persist(gender);
    }
}
