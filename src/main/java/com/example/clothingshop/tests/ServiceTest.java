package com.example.clothingshop.tests;

import com.example.clothingshop.models.Product;
import com.example.clothingshop.services.CartService;
import com.example.clothingshop.services.OrderService;
import com.example.clothingshop.services.ProductService;
import com.example.clothingshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class ServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    public void getProducts(){
        try {
            productService.listProducts();
        } catch (Exception e){
            throw e;
        }
    }

    @Test
    public void getUsers(){
        try {
            userService.list();
        } catch (Exception e){
            throw e;
        }
    }

    @Test
    public void getOrders(){
        try {
            orderService.getOrders();
        } catch (Exception e){
            throw e;
        }
    }




}
