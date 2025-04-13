package com.redeAncoraUsers.fiap.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserPostData(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
