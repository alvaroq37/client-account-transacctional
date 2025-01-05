package product.impl;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import product.dao.data.Product;
import product.dao.repository.ProductRepository;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ProductImpl {
    Messages messages = new Messages();
    @Inject
    ProductRepository productRepository;

    public JsonObject productListAll(){
        try{
            List<Product> productList = productRepository.productListAll();
            if(!productList.isEmpty()){
                return new JsonObject().put("response",productList);
            }else{
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject productFindById(JsonObject data){
        try{
            Long idProduct = data.getLong("idProduct");
            if(idProduct > 0){
                Product product = productRepository.findProductById(idProduct);
                if(product.idProduct > 0){
                    return new JsonObject().put("response", product);
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

    public JsonObject saveProduct(JsonObject data){
        try{
            if(!data.isEmpty()){
                Product product = new Product();
                product.annualInterest = data.getDouble("annualInterest");
                product.description = data.getString("description");
                product.dateCreate = new Date();
                product.userCreate = data.getInteger("userCreate");
                productRepository.createProduct(product);
                return new JsonObject().put("response", "Producto registrado exitosamente");
            }else{
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject updateProduct(JsonObject data){
        try{
            if(!data.isEmpty()){
                Product product = new Product();
                product.idProduct = data.getLong("idProduct");
                product.annualInterest = data.getDouble("annualInterest");
                product.description = data.getString("description");
                product.dateUpdate = new Date();
                product.userUpdate = data.getInteger("userUpdate");
                productRepository.createProduct(product);
                return new JsonObject().put("response", "Producto registrado exitosamente");
            }else{
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject deleteProduct(JsonObject data){
        try{
            Long idProduct = data.getLong("idProduct");
            if(idProduct > 0){
                if(productRepository.deleteProduct(idProduct) > 0){
                    return new JsonObject().put("response", "Producto eliminado correctamente");
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
