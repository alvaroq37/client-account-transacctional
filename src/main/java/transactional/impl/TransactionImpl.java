package transactional.impl;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import product.dao.data.Product;
import transactional.dao.data.Transaction;
import transactional.dao.repository.TransactionRepository;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TransactionImpl {
    Messages messages = new Messages();
    @Inject
    TransactionRepository transactionRepository;

    public JsonObject transactionListAll() {
        try {
            List<Transaction> transactionList = transactionRepository.transactionListAll();
            if (!transactionList.isEmpty()) {
                return new JsonObject().put("response", transactionList);
            } else {
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject transactionFindById(JsonObject data) {
        try {
            Long idTransaction = data.getLong("idTransaction");
            if (idTransaction > 0) {
                Transaction transaction = transactionRepository.transactionFindById(idTransaction);
                if (transaction.idTransaction > 0) {
                    return new JsonObject().put("response", transaction);
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject saveTransaction(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Transaction transaction = new Transaction();
                transaction.description = data.getString("description");
                transaction.dateCreate = new Date();
                transaction.userCreate = data.getInteger("userCreate");
                transactionRepository.transactionSave(transaction);
                return new JsonObject().put("response", "Transacción registrada exitosamente");
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject updateTransaction(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Transaction transaction = new Transaction();
                transaction.idTransaction = data.getLong("idTransaction");
                transaction.description = data.getString("description");
                transaction.dateUpdate = new Date();
                transaction.userUpdate = data.getInteger("userCreate");
                transactionRepository.transactionSave(transaction);
                return new JsonObject().put("response", "Transacción actualizada exitosamente");
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject deleteTransaction(JsonObject data) {
        try {
            Long idTransaction = data.getLong("idTransaction");
            if (idTransaction > 0) {
                if (transactionRepository.transactionDelete(idTransaction) > 0) {
                    return new JsonObject().put("response", "Transacción eliminada correctamente");
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }
}
