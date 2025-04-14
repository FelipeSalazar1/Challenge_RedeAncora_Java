package com.redeAncoraUsers.fiap.validators;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotNull Long id,
        @Email String email,
        String password
) {
}
