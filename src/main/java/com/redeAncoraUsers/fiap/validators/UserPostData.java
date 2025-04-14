package com.redeAncoraUsers.fiap.validators;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserPostData(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotNull Long personId

) {
}
