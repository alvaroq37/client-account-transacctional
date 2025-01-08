package customer.rest;


import customer.impl.DocumentImpl;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
@Path("rest/document")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentRest {

    @Inject
    DocumentImpl documentImpl;

    @POST
    @Path("/list/all")
    public Response documentListAll() {
        return documentImpl.listAllDocument();
    }
    @POST
    @Path("/find/by/id")
    public Response findDocumentById(JsonObject data) {
        return Response.ok(documentImpl.findDocumentById(data)).build();
    }
    @POST
    @Path("/save")
    public Response documentSave(JsonObject data) {
        return Response.ok(documentImpl.saveDocument(data)).build();
    }
    @POST
    @Path("/update")
    public Response documentUpdate(JsonObject data) {
        return Response.ok(documentImpl.updateDocument(data)).build();
    }
    @POST
    @Path("/delete")
    public Response documentDelete(JsonObject data) {
        return Response.ok(documentImpl.deleteDocument(data)).build();
    }

}
