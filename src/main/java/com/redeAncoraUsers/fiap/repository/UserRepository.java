package com.redeAncoraUsers.fiap.repository;

import com.redeAncoraUsers.fiap.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByActivatedTrue(Pageable pageable);

    Page<User> findAllByActivatedFalse(Pageable pageable);
}
