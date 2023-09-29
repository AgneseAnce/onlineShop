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
    @Autowired
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

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable UUID id, Product product) {
        try {
            this.productService.findProductByID(id);
            product.setId(id);
            this.productService.updateProduct(product);
            return "redirect:/?message=PRODUCT_UPDATED_SUCCESS";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_EDIT_FAILED&errors="
                    + exception.getMessage();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProducts(@PathVariable UUID id){
        try {
            this.productService.deleteProduct(id);
            return "redirect:/?message=PRODUCT_DELETED_SUCCESSFULLY";
        } catch (Exception exception){
            return "redirect:/?message=PRODUCT_DELETE_FAILED&error=" + exception.getMessage();
        }
    }

    @GetMapping("/online-shop")
    public String displayShopPage(
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String error,
            Model model) {
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("productArray",
                this.productService.getAllProducts());

        return "shopTransactions";
    }

 /*   @PostMapping("/enter-quantity")*/


    @PostMapping("/product_sold/{id}")
    public String sellProductPost(@PathVariable UUID id, Product product){
        try {
            Product foundProduct = this.productService.findProductByID(id);
            product.setId(id);
            this.productService.sellProduct(foundProduct);
            return "redirect:/?message=PRODUCT_SOLD_SUCCESSFULLY";
        } catch (Exception exception){
            return "redirect:/?message=PRODUCT_SELLING_FAILED&error=" + exception.getMessage();
        }
    }
}
