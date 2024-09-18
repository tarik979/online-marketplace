package com.online_marketplace.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDto {
    private long orderId;
    private String status;
    private long buyer_id;
    private long seller_id;
    private Date createAt;
    private String location;
    private long product_id;
}
