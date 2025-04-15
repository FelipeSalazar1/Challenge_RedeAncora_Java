package com.redeAncoraUsers.fiap.repository;

import com.redeAncoraUsers.fiap.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
