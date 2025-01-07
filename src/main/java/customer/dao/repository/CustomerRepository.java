package customer.dao.repository;

import customer.dao.data.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> clientListAll() {
        return listAll();
    }

    public Customer clientFindById(long id) {
        return find("id", id).firstResult();
    }

    public Customer clientFindByDocumentNumber(String ci) {
        return find("documentNumber", ci).firstResult();
    }

    public Customer clientFindByName(String names) {
        return find("name", names).firstResult();
    }

    public void clientSave(Customer customer) {
        persist(customer);
    }

    public Long clientDelete(long id) {
        return delete("id", id);
    }

    public void clientUpdate(Customer customer) {
        persist(customer);
    }
}
