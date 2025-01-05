package client.impl;

import client.dao.data.Client;
import client.dao.data.Document;
import client.dao.data.Gender;
import client.dao.repository.ClientRepository;
import client.dao.repository.DocumentRepository;
import client.dao.repository.GenderRepository;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ClientImpl {
    Messages messages = new Messages();
    @Inject
    ClientRepository clientRepository;
    @Inject
    GenderRepository genderRepository;
    @Inject
    DocumentRepository documentRepository;

    public JsonObject clientListAll() {
        try {
            List<Client> clientList = clientRepository.clientListAll();
            if (!clientList.isEmpty()) {
                return new JsonObject().put("response", clientList);
            } else {
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject clientFindById(JsonObject data) {
        try {
            Long idClient = data.getLong("idClient");
            if (idClient > 0) {
                Client client = clientRepository.clientFindById(idClient);
                if (client.idClient > 0) {
                    return new JsonObject().put("response", client);
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
                Client client = clientRepository.clientFindByDocumentNumber(documentNumber);
                if (!client.documentNumber.isEmpty()) {
                    return new JsonObject().put("response", client);
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
                Client client = clientRepository.clientFindByDocumentNumber(name);
                if (client.idClient > 0) {
                    return new JsonObject().put("response", client);
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

            Client client = new Client();

            if (!genderData.isEmpty()) {
                Gender gender = genderRepository.genderFindById(genderData.getLong("genderId"));
                if (gender.idGender > 0) {
                    client.gender = gender;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            if (!documentData.isEmpty()) {
                Document document = documentRepository.findDocumentById(documentData.getLong("idDocument"));
                if (document.idDocument > 0) {
                    client.document = document;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            client.documentNumber = data.getString("documentNumber");
            client.dateOfBirth = new Date();
            client.name = data.getString("name");
            client.paternalSurname = data.getString("paternalSurname");
            client.maternalSurname = data.getString("maternalSurname");
            client.dateCreate = new Date();
            client.userCreate = data.getInteger("userCreate");
            clientRepository.clientSave(client);
            return new JsonObject().put("response", "El cliente ha sido registrado correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject clientUpdate(JsonObject data) {
        try {
            JsonObject genderData = data.getJsonObject("gender");
            JsonObject documentData = data.getJsonObject("document");

            Client client = new Client();

            if (!genderData.isEmpty()) {
                Gender gender = genderRepository.genderFindById(genderData.getLong("genderId"));
                if (gender.idGender > 0) {
                    client.gender = gender;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            if (!documentData.isEmpty()) {
                Document document = documentRepository.findDocumentById(documentData.getLong("idDocument"));
                if (document.idDocument > 0) {
                    client.document = document;
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
            client.idClient = data.getLong("idClient");
            client.documentNumber = data.getString("documentNumber");
            client.dateOfBirth = new Date();
            client.name = data.getString("name");
            client.paternalSurname = data.getString("paternalSurname");
            client.maternalSurname = data.getString("maternalSurname");
            client.dateCreate = new Date();
            client.userCreate = data.getInteger("userCreate");
            clientRepository.clientSave(client);
            return new JsonObject().put("response", "El cliente ha sido editado correctamente");
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject clientDelete(JsonObject data) {
        try {
            Long idClient = data.getLong("idClient");
            if (idClient > 0) {
                if (clientRepository.clientDelete(idClient) > 0) {
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
