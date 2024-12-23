package account.dao.repository;

import account.dao.data.Account;
import client.dao.data.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class AccountRepository implements PanacheRepository<Account> {
    public List<Account> accountListAll(){
        return listAll();
    }
    public Account accountFindById(long id) {
        return find("idAccount", id).firstResult();
    }
    public List<Account> accountFindByClient(long id) {
        return find("client.idClient", id).list();
    }
    public Account accountFindByNumberAccount(String numberAccount) {
        return find("numberAccount", numberAccount).firstResult();
    }
    public void accountSave(Account account) {
        persist(account);
    }
    public Long accountDelete(long id) {
        return delete("idAccount", id);
    }
    public void accountUpdate(Account account) {
        persist(account);
    }
}
