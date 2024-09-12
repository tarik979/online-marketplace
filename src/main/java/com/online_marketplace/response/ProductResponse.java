package com.online_marketplace.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Float price;
    private Float quntity;
    private Long categoryId;
    private Long sallerId;
    private String Imagename;
    private String Imageurl;
    private String Imagetype;
    private long ImageSize;
}
