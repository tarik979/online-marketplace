package com.online_marketplace.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.online_marketplace.dto.OrderDetailsDto;
import com.online_marketplace.exception.OrderDetailsNotFoundException;
import com.online_marketplace.mapper.OrderDetailsMapper;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.model.OrderDetails;
import com.online_marketplace.model.Product;
import com.online_marketplace.repository.OrderDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserService userService;
    private final ProductService productService;

    public OrderDetails addOrderDetails(OrderDetailsDto orderDetails){
        if (userService.findUserById(orderDetails.getBuyer_id()) != null && userService.findUserById(orderDetails.getSeller_id()) != null) {
            LocalUser buyer = userService.findUserById(orderDetails.getBuyer_id());
            LocalUser seller = userService.findUserById(orderDetails.getSeller_id());
            if (productService.findProductById(orderDetails.getProduct_id()) != null) {
                Product product = productService.findProductById(orderDetails.getProduct_id());
                OrderDetails newOrderDetails =  OrderDetailsMapper.mapToEntity(orderDetails, buyer, seller, product, false);
                return orderDetailsRepository.save(newOrderDetails);
            }
        }

        return null;
       
    }

    public List<OrderDetailsDto> findAllOrderDetails(){
        return orderDetailsRepository.findAll().stream().map(orederDetail ->OrderDetailsMapper.mappToDto(orederDetail)
        ).collect(Collectors.toList());
    }

    public OrderDetailsDto findOrderDetailsById(Long id){
       Optional<OrderDetails> orderDetails =  orderDetailsRepository.findById(id);
        if (orderDetails.isPresent()) {
            return OrderDetailsMapper.mappToDto(orderDetails.get());
        }else throw new OrderDetailsNotFoundException("Order Details by id " + id + "was not found");
    }

    public OrderDetailsDto updateOrderDetails(OrderDetailsDto orderDetails){
        if (userService.findUserById(orderDetails.getBuyer_id()) != null && userService.findUserById(orderDetails.getSeller_id()) != null) {
            LocalUser buyer = userService.findUserById(orderDetails.getBuyer_id());
            LocalUser seller = userService.findUserById(orderDetails.getSeller_id());
            if (productService.findProductById(orderDetails.getProduct_id()) != null) {
                Product product = productService.findProductById(orderDetails.getProduct_id());
                OrderDetails newOrderDetails =  OrderDetailsMapper.mapToEntity(orderDetails, buyer, seller, product, true);
                return OrderDetailsMapper.mappToDto(orderDetailsRepository.save(newOrderDetails));
            }
        }

        return null;
    }

    public void deleteOrderDetails(Long id){
        orderDetailsRepository.deleteById(id);
    }
}
