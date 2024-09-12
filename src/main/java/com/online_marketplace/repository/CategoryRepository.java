package com.online_marketplace.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_marketplace.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByName(String name);
}
