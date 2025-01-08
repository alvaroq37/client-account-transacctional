package customer.impl;

import customer.dao.data.Customer;
import customer.dao.data.Document;
import customer.dao.data.Gender;
import customer.dao.repository.CustomerRepository;
import customer.dao.repository.DocumentRepository;
import customer.dao.repository.GenderRepository;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import util.Messages;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class ClientImpl {
    Messages messages = new Messages();
    @Inject
    CustomerRepository customerRepository;
    @Inject
    GenderRepository genderRepository;
    @Inject
    DocumentRepository documentRepository;

    public Response clientListAll() {
        try {
            JsonArray customerList = new JsonArray(customerRepository.clientListAll());

            if (!customerList.isEmpty()) {
                Response response = Response.ok(customerList).build();
                return Response.ok(response.getEntity()).build();
                //return new JsonObject().put("response", customerList);
            } else {
                return Response.ok(messages.messageListEmpty()).build();
            }
        } catch (Exception e) {
            return Response.ok(messages.messageError()).build();
        }
    }

    public JsonObject clientFindById(JsonObject data) {
        try {
            Long idClient = data.getLong("idClient");
            if (idClient > 0) {
                Customer customer = customerRepository.clientFindById(idClient);
                if (customer.id > 0) {
                    return new JsonObject().put("response", customer);
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

    public JsonObject clientFindByDocumentNumber(JsonObject data) {
        try {
            String documentNumber = data.getString("documentNumber");
            if (!documentNumber.isEmpty()) {
                Customer customer = customerRepository.clientFindByDocumentNumber(documentNumber);
                if (!customer.documentNumber.isEmpty()) {
                    return new JsonObject().put("response", customer);
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

    public JsonObject clientFindByName(JsonObject data) {
        try {
            String name = data.getString("name");
            if (!name.isEmpty()) {
                Customer customer = customerRepository.clientFindByDocumentNumber(name);
                if (customer.id > 0) {
                    return new JsonObject().put("response", customer);
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

    public JsonObject clientSave(JsonObject data) {
        try {
            JsonObject genderData = data.getJsonObject("gender");
            JsonObject documentData = data.getJsonObject("document");

            Customer customer = new Customer();

            if (!genderData.isEmpty()) {
                Gender gender = genderRepository.genderFindById(genderData.getLong("idGender"));
                if (gender.idGender > 0) {
                    customer.gender = gender;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            if (!documentData.isEmpty()) {
                Document document = documentRepository.findDocumentById(documentData.getLong("idDocument"));
                if (document.idDocument > 0) {
                    customer.document = document;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            String birth = data.getString("dateOfBirth");
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date dateOfBirth = format.parse(birth);
            customer.documentNumber = data.getString("documentNumber");
            customer.dateOfBirth = dateOfBirth;
            customer.name = data.getString("name");
            customer.paternalSurname = data.getString("paternalSurname");
            customer.maternalSurname = data.getString("maternalSurname");
            customer.dateCreate = new Date();
            //customer.userCreate = data.getInteger("userCreate");
            customerRepository.clientSave(customer);
            return new JsonObject().put("response", "El cliente ha sido registrado correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject clientUpdate(JsonObject data) {
        try {
            JsonObject genderData = data.getJsonObject("gender");
            JsonObject documentData = data.getJsonObject("document");
            Customer customer = customerRepository.clientFindById(data.getLong("id"));

            if (!genderData.isEmpty()) {
                Gender gender = genderRepository.genderFindById(genderData.getLong("idGender"));
                if (gender.idGender > 0) {
                    customer.gender = gender;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            if (!documentData.isEmpty()) {
                Document document = documentRepository.findDocumentById(documentData.getLong("idDocument"));
                if (document.idDocument > 0) {
                    customer.document = document;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            customer.id = data.getLong("id");
            customer.documentNumber = data.getString("documentNumber");
            customer.dateOfBirth = new Date();
            customer.name = data.getString("name");
            customer.paternalSurname = data.getString("paternalSurname");
            customer.maternalSurname = data.getString("maternalSurname");
            customer.dateUpdate = new Date();
            //customer.userCreate = data.getInteger("userCreate");
            customerRepository.persistAndFlush(customer);
            return new JsonObject().put("response", "El cliente ha sido editado correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject clientDelete(JsonObject data) {
        try {
            Long idClient = data.getLong("id");
            if (idClient > 0) {
                if (customerRepository.clientDelete(idClient) > 0) {
                    return new JsonObject().put("response", "El cliente ha sido eliminado correctamente");
                }else{
                    return messages.messageListEmpty();
                }
            }else{
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }
}
