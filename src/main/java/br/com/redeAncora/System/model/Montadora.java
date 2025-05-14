package br.com.redeAncora.System.model;

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
