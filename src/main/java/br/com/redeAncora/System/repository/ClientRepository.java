package br.com.redeAncora.System.repository;

import br.com.redeAncora.System.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
