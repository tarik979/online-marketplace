package com.online_marketplace.model;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orderDetails")
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_generator")
    @SequenceGenerator(name = "order_details_generator", sequenceName = "order_details_generator", allocationSize = 1)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "quntity", nullable = false)
    private Float quntity;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "buyer_id", nullable =  false)
    private LocalUser buyer;

    @Column(name = "seller_id", nullable = false)
    private LocalUser seller;

    @Column(name = "created_at", nullable = false)
    private Date createAt = new Date();

    @Column(name = "location",nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderDetails(Float quntity, String status, LocalUser buyer, LocalUser seller, String location,
            Product product) {
        this.quntity = quntity;
        this.status = status;
        this.buyer = buyer;
        this.seller = seller;
        this.location = location;
        this.product = product;
    }

    
    
}
