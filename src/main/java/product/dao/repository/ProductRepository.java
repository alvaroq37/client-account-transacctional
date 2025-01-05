package product.dao.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import product.dao.data.Product;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public List<Product> productListAll() {
        return listAll();
    }

    public Product findProductById(Long idProduct) {
        return find("idProduct", idProduct).firstResult();
    }

    public void createProduct(Product product){
        persist(product);
    }

    public Long deleteProduct(Long idProduct){
        return delete("idProduct", idProduct);
    }
}
