package com.online_marketplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long Id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Float price;
    private Float quntity;
    @JsonIgnore
    private String imageName;
    @JsonIgnore
    private String imageType;
    @JsonIgnore
    private byte[] imageData;
    private Long categoryId;
    private Long sellerId;
}
