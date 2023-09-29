package com.onlineshop;

import org.springframework.stereotype.Service;
import product.Product;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductService {

    private final ArrayList<Product> productArray = new ArrayList<>();
    public ArrayList<Product> getAllProducts() {
        return this.productArray;
    }

    public Product addProductItem(Product product) throws Exception  {
        if (product.getName().isBlank())
            throw new Exception("Please provide product name");
        this.productArray.add(product);
        return product;
    }

    public Product findProductByID(UUID id) throws Exception {
        for (Product product: this.productArray){
            if (product.getId().equals(id)) return product;
        }
        throw new Exception("Product not found");
    }

    public Product updateProduct(Product product) throws Exception{
        for(Product currentProduct: this.productArray) {
            if (currentProduct.getId().equals(product.getId())) {
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setQuantity(product.getQuantity());
                currentProduct.setCategory(product.getCategory());
                return currentProduct;
            }
        } throw new Exception("Product not found");
    }

    public void deleteProduct(UUID id) throws Exception {
        if (!this.productArray.removeIf((product) ->
                product.getId().equals(id)))
            throw new Exception("Product with this ID not found");
    }

    public Product sellProduct(Product product) throws Exception {
        double quantitySold = 0;
        for (Product foundProduct : this.productArray) {
            if (foundProduct.getId().equals(product.getId())) {
                this.updateProduct(product);
                foundProduct.setQuantity((product.getQuantity()) - quantitySold);
                return foundProduct;
            }
        }
        throw new Exception("Product not found");
    }

    /*
    * Create product =
    * Delete
    * Update =
    * Add information
    * Track stock
    * Search / sort
    * View transaction list */


}
