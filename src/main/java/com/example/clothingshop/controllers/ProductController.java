package com.example.clothingshop.controllers;


import com.example.clothingshop.models.Product;
import com.example.clothingshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller

@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name="gender", required = false) String gender, @RequestParam(name = "category", required = false) String category , Model model, Principal principal){
        model.addAttribute("products", productService.listProducts(gender, category));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }



    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, @RequestParam("gender") boolean gender,
                                @RequestParam("category") String category, Product product) throws IOException {
        productService.saveProduct(product, file1, file2, file3, gender, category);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/product/add")
    public String addProduct(Principal principal, Model model){
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "add-product";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        //TODO: delete product from a cart
        return "redirect:/admin/products";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/product/amount")
    public String changeAmount(@RequestParam("id") Long productId, @RequestParam("amount") int amount){
        productService.changeProductAmount(productId, amount);
        System.out.println("amount" + amount + " productId" + productId);
        return "redirect:/admin/products";
    }


}
