package account.impl;

import account.dao.data.Account;
import account.dao.repository.AccountRepository;
import branch.dao.data.Branch;
import branch.dao.repository.BranchRepository;
import client.dao.data.Client;
import client.dao.repository.ClientRepository;
import currency.dao.data.Currency;
import currency.dao.repository.CurrencyRepository;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import product.dao.data.Product;
import product.dao.repository.ProductRepository;
import util.Messages;
import java.util.List;

@ApplicationScoped
public class AccountImpl {
    Messages messages = new Messages();

    @Inject
    AccountRepository accountRepository;
    @Inject
    ClientRepository clientRepository;
    @Inject
    CurrencyRepository currencyRepository;
    @Inject
    BranchRepository branchRepository;
    @Inject
    ProductRepository productRepository;

    public JsonObject accountListAll(){
        try{
            List<Account> accounts = accountRepository.accountListAll();
            if(!accounts.isEmpty()){
                return new JsonObject().put("response", accounts);
            }
            return messages.messageListEmpty();
        }catch (Exception e){
            return messages.messageError();
        }
    }

    public JsonObject findAccountById(JsonObject data){
        try{
            Long idAccount = data.getLong("idAccount");
            if(idAccount > 0){
                Account account = accountRepository.accountFindById(idAccount);
                if(account.numberAccount > 0){
                    return new JsonObject().put("response", account);
                }
                return messages.messageListEmpty();
            }
            return messages.messageDataInput();
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject createAccount(JsonObject data){
        JsonObject clientData = data.getJsonObject("client");
        JsonObject productData = data.getJsonObject("product");
        JsonObject currencyData = data.getJsonObject("currency");
        JsonObject branchData = data.getJsonObject("branch");

        Client client = clientRepository.clientFindById(clientData.getLong("clientId"));
        Product product = productRepository.findProductById(productData.getLong("productId"));
        Currency currency = currencyRepository.currencyFindById(currencyData.getLong("currencyId"));
        Branch branch = branchRepository.agencyFindById(branchData.getLong("branchId"));

        if(client.idClient > 0){
            if(product.idProduct > 0){
                if(currency.idCurrency > 0){
                    if(branch.id > 0){

                    }
                }
            }

        }
        return null;
    }
}
