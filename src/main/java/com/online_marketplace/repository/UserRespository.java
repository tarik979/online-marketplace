package com.online_marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_marketplace.model.LocalUser;


@Repository
public interface UserRespository extends JpaRepository<LocalUser, Long>{
    Optional<LocalUser> findByEmailIgnoreCase(String Email);
    boolean existsByEmail(String email);
}
