package com.online_marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_marketplace.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{
    
}
