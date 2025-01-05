package client.impl;

import client.dao.data.Client;
import client.dao.repository.ClientRepository;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ClientImpl {

    @Inject
    ClientRepository clientRepository;

    public JsonObject clientListAll(){
        return null;
    }

    public JsonObject clientFindById(JsonObject data){
        return null;
    }

    public JsonObject clientFindByDocumentNumber(JsonObject data){
        return null;
    }

    public JsonObject clientFindByName(JsonObject data){
        return null;
    }

    public JsonObject clientSave(JsonObject data){
        return null;
    }

    public JsonObject clientUpdate(JsonObject data){
        return null;
    }

    public JsonObject clientDelete(JsonObject data){
        return null;
    }
}
