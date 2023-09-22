package com.onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import product.Product;


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

        return "addProduct";
    }

    @GetMapping("/add-product")
    public String displayAddProductPage() {
        return "addProduct"; // HTML
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






}
