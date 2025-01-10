package account.impl;

import account.dao.data.Account;
import account.dao.repository.AccountRepository;
import branch.dao.data.Branch;
import branch.dao.repository.BranchRepository;
import customer.dao.data.Customer;
import customer.dao.repository.CustomerRepository;
import currency.dao.data.Currency;
import currency.dao.repository.CurrencyRepository;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import product.dao.data.Product;
import product.dao.repository.ProductRepository;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class AccountImpl {
    Messages messages = new Messages();

    @Inject
    AccountRepository accountRepository;
    @Inject
    CustomerRepository customerRepository;
    @Inject
    CurrencyRepository currencyRepository;
    @Inject
    BranchRepository branchRepository;
    @Inject
    ProductRepository productRepository;

    public Response accountListAll() {
        try {
            JsonArray accounts = new JsonArray(accountRepository.accountListAll());
            if (!accounts.isEmpty()) {
                Response response = Response.ok(accounts).build();
                return Response.ok(response.getEntity()).build();
            }
            return Response.ok(messages.messageListEmpty()).build();
        } catch (Exception e) {
            return Response.ok(messages.messageError()).build();
        }
    }

    public JsonObject findAccountById(JsonObject data) {
        try {
            Long idAccount = data.getLong("idAccount");
            if (idAccount > 0) {
                Account account = accountRepository.accountFindById(idAccount);
                if (account.numberAccount > 0) {
                    return new JsonObject().put("response", account);
                }
                return messages.messageListEmpty();
            }
            return messages.messageDataInput();
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject findAccountByNumber(JsonObject data) {
        try {
            Long numberAccount = data.getLong("numberAccount");
            if (numberAccount > 0) {
                Account account = accountRepository.accountFindByNumberAccount(numberAccount);
                if (account.numberAccount > 0) {
                    return new JsonObject().put("response", account);
                }
                return messages.messageListEmpty();
            }
            return messages.messageDataInput();
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject findAccountByClientId(JsonObject data) {
        try {
            Long idClient = data.getLong("idClient");
            if (idClient > 0) {
                List<Account> accounts = accountRepository.accountFindByClientId(idClient);
                if (!accounts.isEmpty()) {
                    return new JsonObject().put("response", accounts);
                }
                return messages.messageListEmpty();
            }
            return messages.messageDataInput();
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject createAccount(JsonObject data) {
        try {
            JsonObject clientData = data.getJsonObject("customer");
            JsonObject productData = data.getJsonObject("product");
            JsonObject currencyData = data.getJsonObject("currency");
            JsonObject branchData = data.getJsonObject("branch");

            Account account = new Account();

            if (!clientData.isEmpty()) {
                Customer customer = customerRepository.clientFindById(clientData.getLong("clientId"));
                if (customer.id > 0) {
                    account.customer = customer;
                } else {
                    return messages.messageListEmpty();
                }
                if (!productData.isEmpty()) {
                    Product product = productRepository.findProductById(productData.getLong("productId"));
                    if (product.idProduct > 0) {
                        account.product = product;
                    } else {
                        return messages.messageListEmpty();
                    }
                    if (!currencyData.isEmpty()) {
                        Currency currency = currencyRepository.currencyFindById(currencyData.getLong("currencyId"));
                        if (currency.idCurrency > 0) {
                            account.currency = currency;
                        } else {
                            return messages.messageListEmpty();
                        }
                        if (!branchData.isEmpty()) {
                            Branch branch = branchRepository.agencyFindById(branchData.getLong("branchId"));
                            if (branch.id > 0) {
                                account.branch = branch;
                            } else {
                                return messages.messageListEmpty();
                            }
                        } else {
                            return messages.messageDataInput();
                        }
                    } else {
                        return messages.messageDataInput();
                    }
                } else {
                    return messages.messageDataInput();
                }
            } else {
                return messages.messageDataInput();
            }

            account.numberAccount = data.getLong("numberAccount");
            account.amount = data.getLong("amount");
            account.dateCreate = new Date();
            account.userCreate = data.getInteger("userCreate");
            accountRepository.accountSave(account);
            return new JsonObject().put("response", "La cuenta ha sido registrada correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject deleteAccount(JsonObject data) {
        try {
            Long accountId = data.getLong("accountId");
            if (accountId > 0) {
                if (accountRepository.accountDelete(accountId) > 0) {
                    return new JsonObject().put("response", "La cuenta ha sido eliminada");
                } else{
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject updateAccount(JsonObject data) {
        try {
            JsonObject clientData = data.getJsonObject("customer");
            JsonObject productData = data.getJsonObject("product");
            JsonObject currencyData = data.getJsonObject("currency");
            JsonObject branchData = data.getJsonObject("branch");

            Account account = new Account();

            if (!clientData.isEmpty()) {
                Customer customer = customerRepository.clientFindById(clientData.getLong("clientId"));
                if (customer.id > 0) {
                    account.customer = customer;
                } else {
                    return messages.messageListEmpty();
                }
                if (!productData.isEmpty()) {
                    Product product = productRepository.findProductById(productData.getLong("productId"));
                    if (product.idProduct > 0) {
                        account.product = product;
                    } else {
                        return messages.messageListEmpty();
                    }
                    if (!currencyData.isEmpty()) {
                        Currency currency = currencyRepository.currencyFindById(currencyData.getLong("currencyId"));
                        if (currency.idCurrency > 0) {
                            account.currency = currency;
                        } else {
                            return messages.messageListEmpty();
                        }
                        if (!branchData.isEmpty()) {
                            Branch branch = branchRepository.agencyFindById(branchData.getLong("branchId"));
                            if (branch.id > 0) {
                                account.branch = branch;
                            } else {
                                return messages.messageListEmpty();
                            }
                        } else {
                            return messages.messageDataInput();
                        }
                    } else {
                        return messages.messageDataInput();
                    }
                } else {
                    return messages.messageDataInput();
                }
            } else {
                return messages.messageDataInput();
            }
            account.idAccount = data.getLong("idAccount");
            account.numberAccount = data.getLong("numberAccount");
            account.amount = data.getLong("amount");
            account.dateCreate = new Date();
            account.userCreate = data.getInteger("userCreate");
            accountRepository.accountSave(account);
            return new JsonObject().put("response", "La cuenta ha sido registrada correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }
}
