package com.onlineshop;

import org.springframework.stereotype.Service;
import product.Product;

import java.util.ArrayList;

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

    /*
    * Create product
    * Delete
    * Update
    * Add information
    * Track stock
    * Search / sort
    * View transaction list */


}
