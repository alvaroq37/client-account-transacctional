package client.dao.repository;

import client.dao.data.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DocumentRepository implements PanacheRepository<Document> {

    public List<Document> listAllDocument() {
        return listAll().stream().toList();
    }

    public Document findDocumentById(Long id) {
        return find("idDocument", id).firstResult();
    }

    public void saveDocument(Document document) {
        persist(document);
    }

    public Long deleteDocument(Long id) {
        return delete("idDocument", id);
    }

    public void updateDocument(Document document) {
        persist(document);
    }
}
