package com.example.clothingshop.models;

import com.example.clothingshop.emums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    /*@Column(name = "product_id")
    private Long productId;*/
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private String date;
    @Column(name = "price")
    private int price;

    @OneToMany
    @JoinColumn(name = "order_id")
    public List<Pair> pairList = new ArrayList<>();

    public void addProductToOrder(Pair pair){
        pairList.add(pair);
    }

    /*public void addAmount(int amount){
        amounts.add(amount);
    }*/

    @PostConstruct
    public void init(){
        Date date = new Date();
        this.date = date.toString();
    }

}

