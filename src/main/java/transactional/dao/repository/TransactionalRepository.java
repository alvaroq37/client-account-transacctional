package transactional.dao.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import transactional.dao.data.Transactional;
import java.util.List;

@ApplicationScoped
public class TransactionalRepository implements PanacheRepository<Transactional> {

    public List<Transactional> transactionalListAll() {
        return listAll();
    }

    public Transactional transactionalFindById(long id) {

        return find("id", id).firstResult();

    }

    public Transactional transactionalFindByNumberAccount(String account_id) {

        return find("account_id", account_id).firstResult();
    }
    public void transactionalSave(Transactional transactional) {
        persist(transactional);
    }

    public Long transactionalDelete(long id) {
        return delete("id", id);
    }

    public void transactionalUpdate(Transactional transactional) {
        persist(transactional);
    }
}
