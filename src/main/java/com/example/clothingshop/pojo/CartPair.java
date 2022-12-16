package com.example.clothingshop.pojo;

import com.example.clothingshop.models.Cart;
import com.example.clothingshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartPair {
    private Product product;
    private int amount;
}
