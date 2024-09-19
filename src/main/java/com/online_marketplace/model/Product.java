package com.online_marketplace.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@Transactional
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name", unique =  true,  nullable = false)
    private String name;

    @Column(name = "short_description", nullable = false, length = 60)
    private String shortDescription;

    @Column(name ="long_description", nullable = false, length = 300)
    private String longDescription;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "sold", nullable = false)
    private boolean sold;

    private boolean delete;

    @Column(name = "imageName", nullable = false, unique = true)
    private String imageName;

    @Column(name ="imageType", nullable =  false)
    private String imageType;

    @Column(name = "imageData")
    @Lob
    private byte[] imageData;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LocalUser user;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderDetails> orderDetails;
    
}
