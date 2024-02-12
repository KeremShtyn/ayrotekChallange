package com.example.ayrotekchallange.repository;

import com.example.ayrotekchallange.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    boolean existsByName(String name);
}
