package com.example.restservice.repository;

import com.example.restservice.entities.RoleEntity;
import com.example.restservice.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleType name);
}
