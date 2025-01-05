package client.dao.repository;

import client.dao.data.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ClientRepository implements PanacheRepository<Client> {

    public List<Client> clientListAll() {
        return listAll();
    }

    public Client clientFindById(long id) {
        return find("idClient", id).firstResult();
    }

    public Client clientFindByDocumentNumber(String ci) {
        return find("documentNumber", ci).firstResult();
    }

    public Client clientFindByName(String names) {
        return find("name", names).firstResult();
    }

    public void clientSave(Client client) {
        persist(client);
    }

    public Long clientDelete(long id) {
        return delete("idClient", id);
    }

    public void clientUpdate(Client client) {
        persist(client);
    }
}
