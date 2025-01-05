package transactional.dao.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import transactional.dao.data.Transaction;
import transactional.dao.data.Transactional;

import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
    public List<Transaction> transactionListAll() {
        return listAll();
    }

    public Transaction transactionFindById(Long idTransactional) {
        return find("idTransaction", idTransactional).firstResult();
    }

    public void transactionSave(Transaction transaction) {
        persist(transaction);
    }

    public Long transactionDelete(Long idTransaction) {
        return delete("idTransaction", idTransaction);
    }
}
