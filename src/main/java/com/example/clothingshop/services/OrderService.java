package com.example.clothingshop.services;

import com.example.clothingshop.models.Order;
import com.example.clothingshop.models.Pair;
import com.example.clothingshop.models.Product;
import com.example.clothingshop.models.User;
import com.example.clothingshop.pojo.CartPair;
import com.example.clothingshop.repositories.OrderRepository;
import com.example.clothingshop.repositories.PairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final PairRepository pairRepository;


    public List<Order> getOrders(User user) {
        return orderRepository.findAllByUserId(user.getId());
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void createOrder(User user) {
        List<CartPair> cartPairs = cartService.getCartPairs(user.getId());
        Iterator iterator = cartPairs.iterator();
        Long orderId = createId();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(user.getId());
        order.setStatus("Processing");
        Date date = new Date();
        order.setDate(date.toString());

        int price = 0;
        while (iterator.hasNext()) {
            CartPair cartPair = (CartPair) iterator.next();
            Product product = cartPair.getProduct();

            productService.changeProductAmount(product.getId(), cartPair.getAmount() * -1);
            Pair pair = new Pair();
            pair.setProductId(product.getId());
            pair.setAmount(cartPair.getAmount());
            order.addProductToOrder(pairRepository.findById(pairRepository.save(pair).getId()).orElse(null));
            price += product.getPrice() * cartPair.getAmount();
        }
        order.setPrice(price);
        orderRepository.save(order);

        cartService.clearUserCart(user.getId());
    }

    public Long createId() {
        Long id;
        Random random = new Random();
        while (true) {
            id = (long) random.nextInt(1000);
            if (orderRepository != null)
                break;
            if (!orderRepository.existsById(id))
                break;
        }
        return id;
    }


    public void deleteOrder(Long id) {
        Order order = getOrder(id);
        List<Pair> pairList = order.getPairList();
        Iterator iterator = pairList.iterator();
        while (iterator.hasNext()){
            Pair pair = (Pair) iterator.next();
            productService.changeProductAmount(pair.getProductId(), pair.getAmount());
        }
        orderRepository.deleteById(id);
    }

    public void deleteOrder(Long id, User user) {
        if (getOrder(id).getUserId().equals(user.getId()))
            deleteOrder(id);
    }



    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }


    public void changeStatus(Long id) {
        Order order = getOrder(id);
        String status = order.getStatus();

        if (status.equals("Processing")) {
            status = "Delivering";
        } else if (status.equals("Delivering")) {
            status = "Delivered";
        } else if (status.equals("Delivered")) {
            status = "Processing";
        } else {
            status = "Processing";
        }
        order.setStatus(status);
        orderRepository.save(order);

    }

    public List<CartPair> getOrderCartPairs(Long id) {
        List<CartPair> cartPairs = new ArrayList<>();
        List<Pair> pairList = getOrder(id).getPairList();
        for (int i = 0; i < pairList.size(); i++) {
            Pair pair = pairList.get(i);
            cartPairs.add(new CartPair(productService.getProductById(pair.getProductId()), pair.getAmount()));
        }
        return cartPairs;
    }
}
