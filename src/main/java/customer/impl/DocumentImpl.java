package customer.impl;

import customer.dao.data.Document;
import customer.dao.repository.DocumentRepository;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class DocumentImpl {
    Messages messages = new Messages();
    @Inject
    DocumentRepository documentRepository;

    public Response listAllDocument() {
        try {
            JsonArray documentList = new JsonArray(documentRepository.listAllDocument());
            if (!documentList.isEmpty()) {
                Response response = Response.ok(documentList).build();
                return Response.ok(response.getEntity()).build();
            } else {
                return Response.ok(messages.messageListEmpty()).build();
            }
        } catch (Exception e) {
            return Response.ok(messages.messageError()).build();
        }
    }

    public JsonObject findDocumentById(JsonObject data) {
        try {
            Long idDocument = data.getLong("idDocument");
            if (idDocument > 0) {
                Document document = documentRepository.findDocumentById(idDocument);
                if (document.idDocument > 0) {
                    return new JsonObject().put("response", document);
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

    public JsonObject saveDocument(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Document document = new Document();
                document.description = data.getString("description");
                document.userCreate = data.getInteger("userCreate");
                document.dateCreate = new Date();
                documentRepository.saveDocument(document);
                return new JsonObject().put("response", "Documento registrado correctamente");
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }
    public JsonObject updateDocument(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Document document = new Document();
                document.idDocument = data.getLong("idDocument");
                document.description = data.getString("description");
                document.userCreate = data.getInteger("userCreate");
                document.dateCreate = new Date();
                documentRepository.updateDocument(document);
                return new JsonObject().put("response", "Documento editado correctamente");
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject deleteDocument(JsonObject data){
        try{
            Long idDocument = data.getLong("idDocument");
            if(idDocument > 0){
                if(documentRepository.deleteDocument(idDocument)> 0){
                    return new JsonObject().put("response", "Documento eliminado correctamente");
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
