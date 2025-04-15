package com.redeAncoraUsers.fiap.validators;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientUpdateData(
        @NotNull Long id,
        String name,
        String phone,
        String address,
        String cpf_cnpj
) {
}
