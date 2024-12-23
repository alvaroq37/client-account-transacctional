package client.impl;

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
public class ClientImpl {

    JsonObject jsonResponse = new JsonObject();
    ObjectMapper mapper = new ObjectMapper();
    @Inject
    ClientRepository clientRepository;

    public Response clientListAll() {
        try {
            List<Client> clientListAll = clientRepository.clientListAll();
            JsonArray jsonArrayClientAll = new JsonArray(clientListAll);
            Response response = Response.ok(jsonArrayClientAll).build();
            if (response.getStatus() == 200) {
                if (clientListAll.isEmpty()) {
                    jsonResponse.put("message", "No existen clientes registrados");
                    response = Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response clientFindById(JsonObject jsonDataClient) {
        try {
            long id = Long.parseLong(jsonDataClient.getString("id"));
            Client clientFindById = clientRepository.clientFindById(id);
            JsonObject jsonArrayClientById = new JsonObject(mapper.writeValueAsString(clientFindById));
            Response response = Response.ok(jsonArrayClientById).build();
            if (response.getStatus() == 200) {
                if (jsonArrayClientById.isEmpty()) {
                    jsonResponse.put("message", "El cliente con : " + id + " no se encuentra registrado");
                    return Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response clientFindByName(JsonObject jsonDataclient) {
        try {
            String name = jsonDataclient.getString("name");
            Client clientFindByName = clientRepository.clientFindByName(name);
            JsonObject jsonArrayClient = new JsonObject(mapper.writeValueAsString(clientFindByName));
            Response response = Response.ok(jsonArrayClient).build();
            if (response.getStatus() == 200) {
                if (jsonArrayClient.isEmpty()) {
                    jsonResponse.put("message", "CLIENT  BY NAME: " + name.toUpperCase() + " NOT EXISTS");
                    return Response.ok(jsonResponse).build();
                }
                return Response.ok(response.getEntity()).build();
            }
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
        return Response.serverError().build();
    }

    public Response citySave(JsonObject jsonDataClient) {
        try {
            Client client = new Client();
            client.idClient = jsonDataClient.getInteger("id");
            client.name = jsonDataClient.getString("nombre");
            client.paternalSurname = jsonDataClient.getString("paterno");
            client.maternalSurname = jsonDataClient.getString("materno");
            client.dateOfBirth = new Date(jsonDataClient.getLong("fechaNacimiento"));
            clientRepository.clientSave(client);
            JsonObject jsonResponseClientSave = new JsonObject();
            jsonResponseClientSave.put("message", "CLIENT " + jsonDataClient.getString("nombre").toUpperCase() + " CREATED");
            return Response.ok(jsonResponseClientSave).build();
        } catch (Exception e) {
            return Response.accepted(e.getMessage()).build();
        }
    }

    public Response clientDelete(JsonObject jsonDataClient) {
        try {
            JsonObject jsonResponseClientDelete = new JsonObject();
            long id = Long.parseLong(jsonDataClient.getString("id"));
            long responseDelete = clientRepository.clientDelete(id);

            if (responseDelete <= 0) {
                jsonResponse.put("message", "CLIENT " + jsonDataClient.getString("name").toUpperCase() + " NOT EXISTS");
                return Response.ok(jsonResponse).build();
            }
            jsonResponseClientDelete.put("message", "CLIENT " + jsonDataClient.getString("name").toUpperCase() + " DELETE");
            return Response.ok(jsonResponseClientDelete).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }

    public Response clientUpdate(JsonObject jsonDataClient) {
        try {
            JsonObject jsonResponseClientUpdate = new JsonObject();
            //long id = Long.parseLong(jsonDataClient.getString("id"));
//            String name = jsonDataClient.getString("names");
//            if (id <= 0 || name == null) {
//                jsonResponseFail.put("message", "CLIENT " + jsonDataClient.getString("names").toUpperCase() + " NOT EXISTS");
//                return Response.ok(jsonResponseFail).build();
//            }

            Client client = clientRepository.clientFindById(Long.parseLong(jsonDataClient.getString("id")));
            client.idClient = jsonDataClient.getInteger("id");
            client.name = jsonDataClient.getString("nombre");
            client.paternalSurname = jsonDataClient.getString("paterno");
            client.maternalSurname = jsonDataClient.getString("materno");
            client.dateOfBirth = new Date(jsonDataClient.getLong("fechaNacimiento"));

            clientRepository.clientUpdate(client);
            //jsonResponseClientUpdate.put("message", "CLIENT " + name.toUpperCase() + " HAS UPDATE");
            Response response = Response.ok(jsonResponseClientUpdate).build();
            return Response.ok(response.getEntity()).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).build();
        }
    }
}
