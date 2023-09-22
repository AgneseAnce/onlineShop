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

    public Product addProductItem(Product product)  {
//        if (product.getName().isBlank())
//            throw new Exception("Please provide product name");
////        if (product.getCategory().isEmpty())
////            throw new Exception("Please provide product name");
//        if (product.getPrice().is())
//            throw new Exception("Please provide product name");
//        if (product.getName().isBlank())
//            throw new Exception("Please provide product name");
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
