package com.online_marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_marketplace.model.ERole;
import com.online_marketplace.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(ERole name);
}
