package com.online_marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.dto.OrderDetailsDto;
import com.online_marketplace.mapper.OrderDetailsMapper;
import com.online_marketplace.model.OrderDetails;
import com.online_marketplace.service.OrderDetailsService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService){
        this.orderDetailsService = orderDetailsService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDetailsDto> addOrderDerails(@RequestBody OrderDetailsDto orderDetails){
        OrderDetails newOrderDetails = orderDetailsService.addOrderDetails(orderDetails);
        OrderDetailsMapper.mappToDto(newOrderDetails);
        return new ResponseEntity<>(OrderDetailsMapper.mappToDto(newOrderDetails), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<OrderDetailsDto>> findAllOrderDetails(){
        List<OrderDetailsDto> orderDetails = orderDetailsService.findAllOrderDetails();
        if (orderDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<OrderDetailsDto> findOrderDetailsById(@PathVariable("id") Long id){
        OrderDetailsDto orderDetails = orderDetailsService.findOrderDetailsById(id);
        if (orderDetails == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@RequestBody OrderDetailsDto orderDetails){
        OrderDetailsDto updatedOrderDetails = orderDetailsService.updateOrderDetails(orderDetails);
        return new ResponseEntity<>(updatedOrderDetails, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderDetails(@PathVariable("id") Long id){
        orderDetailsService.deleteOrderDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
