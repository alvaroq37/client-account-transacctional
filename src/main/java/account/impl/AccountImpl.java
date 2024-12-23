package account.impl;

import account.dao.data.Account;
import account.dao.repository.AccountRepository;
import client.dao.data.Client;
import client.dao.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class AccountImpl {

    JsonObject jsonResponseFail = new JsonObject();
    ObjectMapper mapper = new ObjectMapper();
    @Inject
    AccountRepository accountRepository;

    @Inject
    ClientRepository clientRepository;

    public Response accountListAll() {
        try {
            List<Account> clientListAll = accountRepository.accountListAll();
            JsonArray jsonArrayClientAll = new JsonArray(clientListAll);
            Response response = Response.ok(jsonArrayClientAll).build();
            if (response.getStatus() == 200) {
                if (clientListAll.isEmpty()) {
                    jsonResponseFail.put("message", "LIST ACCOUNT EMPTY");
                    response = Response.ok(jsonResponseFail).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response accountFindByClient(JsonObject jsonDataAccount) {
        try {
            Response response = null;
            long id = Long.parseLong(jsonDataAccount.getString("id"));
            List<Account> accountFindById = accountRepository.accountFindByClient(id);
            JsonArray jsonArrayClientAll = new JsonArray(accountFindById);
            response = Response.ok(jsonArrayClientAll).build();
            if (response.getStatus() == 200) {
                if (accountFindById.isEmpty()) {
                    jsonResponseFail.put("message", "El cliente no se encuentra registrado");
                    response = Response.ok(jsonResponseFail).build();
                }
                return Response.ok(response.getEntity()).build();
            }

        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response accountFindById(JsonObject jsonDataAccount) {
        try {
            Response response = null;
            JsonObject jsonArrayClientById = new JsonObject();
            long id = Long.parseLong(jsonDataAccount.getString("id"));
            Account accountFindById = accountRepository.accountFindById(id);
            if(accountFindById != null){
                jsonArrayClientById = new JsonObject(mapper.writeValueAsString(accountFindById));
                response = Response.ok(jsonArrayClientById).build();
                if (response.getStatus() == 200) {
                    if (jsonArrayClientById.isEmpty()) {
                        jsonResponseFail.put("message", "El cliente con : " + id + " no se encuentra registrado");
                        return Response.ok(jsonResponseFail).build();
                    }
                    return Response.ok(response.getEntity()).build();
                }
            }else {
                jsonResponseFail.put("message", "El cliente con : " + id + " no se encuentra registrado");
                return Response.ok(jsonResponseFail).build();
            }

        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response accountFindByNumberAccount(JsonObject jsonDataAccount) {
        try {
            String numberAccount = jsonDataAccount.getString("numberAccount");
            Account accountFindByNumberAccount = accountRepository.accountFindByNumberAccount(numberAccount);
            JsonObject jsonArrayClient = new JsonObject(mapper.writeValueAsString(accountFindByNumberAccount));
            Response response = Response.ok(jsonArrayClient).build();
            if (response.getStatus() == 200) {
                if (jsonArrayClient.isEmpty()) {
                    jsonResponseFail.put("message", "Nro. de cuenta : " + numberAccount.toUpperCase() + " no registrado");
                    return Response.ok(jsonResponseFail).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response accountSave(JsonObject jsonDataAccount) {
        try {
            long client_id = Long.parseLong(jsonDataAccount.getString("client_id"));
            Client client = clientRepository.clientFindById(client_id);

            Account account = new Account();
            account.idAccount = jsonDataAccount.getLong("id");
            account.numberAccount = Long.parseLong(jsonDataAccount.getString("number_account"));
            account.amount = Long.parseLong(jsonDataAccount.getString("amount"));
            account.dateCreate = new Date();
            account.client = client;

            accountRepository.accountSave(account);
            JsonObject jsonResponseClientSave = new JsonObject();
            jsonResponseClientSave.put("message", "Cuenta : " + jsonDataAccount.getString("number_account").toUpperCase() + " creada correctamente");
            return Response.ok(jsonResponseClientSave).build();
        } catch (Exception e) {
            return Response.accepted(e.getMessage()).build();
        }
    }

    public Response accountDelete(JsonObject jsonDataAccount) {
        try {
            JsonObject jsonResponseAccountDelete = new JsonObject();
            long id = Long.parseLong(jsonDataAccount.getString("id"));
            long responseDelete = accountRepository.accountDelete(id);

            if (responseDelete <= 0) {
                jsonResponseFail.put("message", "Nro. de cuenta :" + jsonDataAccount.getString("number_account").toUpperCase() + " no registrada");
                return Response.ok(jsonResponseFail).build();
            }
            jsonResponseAccountDelete.put("message", "Nro. de cuenta " + jsonDataAccount.getString("number_account").toUpperCase() + " borrada correctamente");
            return Response.ok(jsonResponseAccountDelete).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }

    public Response accountUpdate(JsonObject jsonDataAccount) {
        try {
            JsonObject jsonResponseClientUpdate = new JsonObject();
            Account account = accountRepository.accountFindById(jsonDataAccount.getLong("id"));
            account.numberAccount = jsonDataAccount.getLong("numberAccount");
            account.amount = Long.parseLong(jsonDataAccount.getString("amount"));
            account.dateUpdate = new Date(jsonDataAccount.getLong("dateCreate"));
            accountRepository.accountUpdate(account);
            jsonResponseClientUpdate.put("message", "Cuenta actualizada correctamente");
            Response response = Response.ok(jsonResponseClientUpdate).build();
            return Response.ok(response.getEntity()).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }
}
