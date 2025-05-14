package br.com.redeAncora.System.repository;

import br.com.redeAncora.System.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByActivatedTrue(Pageable pageable);

    Page<User> findAllByActivatedFalse(Pageable pageable);

    Optional<User> findByEmail(String username);
}
