package com.online_marketplace.mapper;

import com.online_marketplace.dto.OrderDetailsDto;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.model.OrderDetails;
import com.online_marketplace.model.Product;

public class OrderDetailsMapper {
    public static OrderDetailsDto mappToDto(OrderDetails orderDetails){
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setOrderId(orderDetails.getOrderId());
        dto.setQuntity(orderDetails.getQuntity());
        dto.setStatus(orderDetails.getStatus());
        dto.setBuyer_id(orderDetails.getBuyer().getUserId());
        dto.setSeller_id(orderDetails.getSeller().getUserId());
        dto.setLocation(orderDetails.getLocation());
        dto.setCreateAt(orderDetails.getCreateAt());
        dto.setLocation(orderDetails.getLocation());
        dto.setProduct_id(orderDetails.getProduct().getProductId());
        return dto;
    }

    public static OrderDetails mapToEntity(OrderDetailsDto dto, LocalUser buyer, LocalUser seller, Product product, boolean setId){
        OrderDetails orderDetails = new OrderDetails();
        if (setId) {
            orderDetails.setOrderId(dto.getOrderId());
        }
        orderDetails.setQuntity(dto.getQuntity());
        orderDetails.setStatus(dto.getStatus());
        orderDetails.setBuyer(buyer);
        orderDetails.setSeller(seller);
        orderDetails.setLocation(dto.getLocation());
        orderDetails.setProduct(product);
        return orderDetails;
    }
}
