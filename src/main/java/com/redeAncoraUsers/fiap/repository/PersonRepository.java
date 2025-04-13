package com.redeAncoraUsers.fiap.repository;

import com.redeAncoraUsers.fiap.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
