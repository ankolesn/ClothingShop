package com.example.clothingshop.services;

import com.example.clothingshop.models.Cart;
import com.example.clothingshop.models.Product;
import com.example.clothingshop.models.User;
import com.example.clothingshop.pojo.CartPair;
import com.example.clothingshop.repositories.CartRepository;
import com.example.clothingshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public void addProduct(Long productId, int amount, Long userId) {
        boolean contains = false;
        List<Cart> carts = cartRepository.findAll();
        Iterator iterator = carts.iterator();
        Cart cart = new Cart();
        while (iterator.hasNext()){
            cart = (Cart) iterator.next();
            if (cart.getProductId().equals(productId) && cart.getUserId().equals(userId)){
                cart.setAmount(cart.getAmount() + amount);
                if (productService.getProductById(productId).getAmount() < cart.getAmount())
                    cart.setAmount(productService.getProductById(productId).getAmount());
                contains = true;
                cartRepository.deleteById(cart.getId());
                break;
            }
        }
        if (!contains){
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setAmount(amount);
            cartRepository.save(cart);
        }
        cartRepository.save(cart);
    }

    public List<CartPair> getCartPairs(Long userId){
         List<CartPair> cartPairs = new ArrayList<>();
         List<Cart> carts = cartRepository.findAll();
         Iterator iterator = carts.iterator();

         while (iterator.hasNext()){
             Cart cart = (Cart) iterator.next();
             if(cart.getUserId().equals(userId)){
                 cartPairs.add(new CartPair(productService.getProductById(cart.getProductId()), cart.getAmount()));
             }
         }
         return cartPairs;
    }

    public void clearUserCart(Long userId) {
        List<Cart> carts = cartRepository.findAll();
        Iterator iterator = carts.iterator();
        while (iterator.hasNext()){
            Cart cart = (Cart) iterator.next();
            if (cart.getUserId().equals(userId)){
                cartRepository.deleteById(cart.getId());
            }
        }
    }

    public Long getTotalPrice(Long id) {
        long totalPrice = 0;
        List<CartPair> cartPairs = getCartPairs(id);
        Iterator iterator = cartPairs.iterator();
        while (iterator.hasNext()){
            CartPair cartPair = (CartPair) iterator.next();
            totalPrice += (long) cartPair.getProduct().getPrice() * cartPair.getAmount();
        }
        return totalPrice;
    }
}
