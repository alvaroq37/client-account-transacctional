package transactional.impl;

import account.dao.data.Account;
import account.dao.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import transactional.dao.data.Transactional;
import transactional.dao.repository.TransactionalRepository;

import java.util.List;

@ApplicationScoped
public class TransactionalImpl {

    JsonObject jsonResponse = new JsonObject();
    ObjectMapper mapper = new ObjectMapper();

    @Inject
    TransactionalRepository transactionalRepository;

    @Inject
    AccountRepository accountRepository;

    public Response transactionListAll() {
        try {
            List<Transactional> transactionListAll = transactionalRepository.transactionalListAll();
            JsonArray jsonArrayClientAll = new JsonArray(transactionListAll);
            Response response = Response.ok(jsonArrayClientAll).build();
            if (response.getStatus() == 200) {
                if (transactionListAll.isEmpty()) {
                    jsonResponse.put("message", "No existen transacciones registradas");
                    response = Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response transactionalFindById(JsonObject jsonDataAccount) {
        try {
            long id = jsonDataAccount.getLong("id");
            Transactional transactionalById = transactionalRepository.transactionalFindById(id);
            JsonObject jsonArrayClientById = new JsonObject(mapper.writeValueAsString(transactionalById));
            Response response = Response.ok(jsonArrayClientById).build();
            if (response.getStatus() == 200) {
                if (jsonArrayClientById.isEmpty()) {
                    jsonResponse.put("message", "Transacción Nro. : " + id + " no está registrada");
                    return Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response transactionalFindByNumberAccount(JsonObject jsonDataAccount) {
        try {
            String numberAccount = jsonDataAccount.getString("numberAccount");
            Transactional transactionalFindByNumberAccount = transactionalRepository.transactionalFindByNumberAccount(numberAccount);
            JsonObject jsonArrayClient = new JsonObject(mapper.writeValueAsString(transactionalFindByNumberAccount));
            Response response = Response.ok(jsonArrayClient).build();
            if (response.getStatus() == 200) {
                if (jsonArrayClient.isEmpty()) {
                    jsonResponse.put("message", "Cuenta Nro. : " + numberAccount.toUpperCase() + " no está registrada");
                    return Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response transactionalSave(JsonObject jsonDataAccount) {
        try {
            long accountId_id = Long.parseLong(jsonDataAccount.getString("account_id"));
            Account account = accountRepository.accountFindById(accountId_id);

            Transactional transactional = new Transactional();
            transactional.id = jsonDataAccount.getLong("id");
            transactional.amount = Long.parseLong(jsonDataAccount.getString("amount"));
            transactional.date = jsonDataAccount.getString("date");
            transactional.hour = jsonDataAccount.getString("hour");
            transactional.account = account;

            transactionalRepository.transactionalSave(transactional);
            JsonObject jsonResponseClientSave = new JsonObject();
            jsonResponseClientSave.put("message", "Transacción Nro. " + jsonDataAccount.getString("id") + " registrada correctamente");
            return Response.ok(jsonResponseClientSave).build();
        } catch (Exception e) {
            return Response.accepted(e.getMessage()).build();
        }
    }

    public Response transactionalDelete(JsonObject jsonDataAccount) {
        try {
            JsonObject jsonResponseAccountDelete = new JsonObject();
            long id = Long.parseLong(jsonDataAccount.getString("id"));
            long responseDelete = transactionalRepository.transactionalDelete(id);

            if (responseDelete <= 0) {
                jsonResponse.put("message", "Transacción Nro. " + jsonDataAccount.getString("id") + " no existe");
                return Response.ok(jsonResponse).build();
            }
            jsonResponseAccountDelete.put("message", "Transacción Nro. " + jsonDataAccount.getString("id") + " eliminada correctamente");
            return Response.ok(jsonResponseAccountDelete).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }

    public Response transactionalUpdate(JsonObject jsonDataAccount) {
        try {
            long accountId_id = jsonDataAccount.getLong("account_id");
            Account account = accountRepository.accountFindById(accountId_id);

            JsonObject jsonResponseClientUpdate = new JsonObject();
            long id = Long.parseLong(jsonDataAccount.getString("id"));
            String name = jsonDataAccount.getString("number_account");
            if (id <= 0 || name == null) {
                jsonResponse.put("message", "Transacción Nro. " + jsonDataAccount.getString("id") + " no existe");
                return Response.ok(jsonResponse).build();
            }

            Transactional transacctional = new Transactional();
            transacctional.id = jsonDataAccount.getLong("id");
            transacctional.amount = jsonDataAccount.getLong("amount");
            transacctional.date = jsonDataAccount.getString("date_create");
            transacctional.hour = jsonDataAccount.getString("hour");
            transacctional.account = account;

            accountRepository.accountUpdate(account);
            jsonResponseClientUpdate.put("message", "Transacción Nro. " + id + " actualizada correctamente");
            Response response = Response.ok(jsonResponseClientUpdate).build();
            return Response.ok(response.getEntity()).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }
}
