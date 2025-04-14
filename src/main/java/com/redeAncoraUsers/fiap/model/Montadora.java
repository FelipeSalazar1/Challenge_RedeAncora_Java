package com.redeAncoraUsers.fiap.model;

import jakarta.persistence.GeneratedValue;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Montadora {
    private Long id;
    private String descricao;
}
