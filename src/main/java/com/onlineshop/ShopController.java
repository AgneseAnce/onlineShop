package com.onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import product.Product;

import java.util.UUID;


@Controller
public class ShopController {
    private ProductService productService;
    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String displayProductList(
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String error,
            Model model) {
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("productArray",
                this.productService.getAllProducts());

        return "productList";
    }

    @GetMapping("/add-product")
    public String displayAddProductPage() {
        return "addProduct"; // HTML
    }

    @GetMapping("/online-shop")
    public String displaySellPage(){
        return "shopTransactions";
    }

    @PostMapping("/add-product")
    public String createProduct (Product product) {
        try {
            this.productService.addProductItem(product);
            return "redirect:/?message=PRODUCT_CREATED_SUCCESSFULLY";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_CREATION_FAILED&errors"
                    + exception.getMessage();
        }
    }

//    @PostMapping("/sell-product")

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable UUID id, Model model) {
        try {
            Product product = this.productService.findProductByID(id);
            model.addAttribute("product", product);
            return "editProduct";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_EDIT_FAILED&error="
                    + exception.getMessage();
        }
    }
//
//    @GetMapping("/update-status/{id}/{status}")
//    public String updateTodo(@PathVariable() UUID id,
//                             @PathVariable String status){
//        try {
//            Product foundProduct = this.productService.findProductByID();
//            // Sets status to user input
//            foundProduct.setStatus(TodoStatus.valueOf(status));
//            this.productService.updateTodo(foundTodo);
//            return "redirect:/?message=TODO_UPDATED_SUCCESSFULLY";
//        } catch (Exception exception){
//            return "redirect:/?message=TODO_UPDATE_FAILED&error=" + exception.getMessage();
//        }
//    }
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable UUID id, Product product) {
        try {
            this.productService.findProductByID(id);
            product.setId(id);
            this.productService.updateProduct(product);
            return "redirect:/?message=PRODUCT_UPDATED_SUCCESS";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_EDIT_FAILED&error="
                    + exception.getMessage();
        }
    }

}
