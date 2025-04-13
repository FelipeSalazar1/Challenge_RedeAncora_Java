package com.redeAncoraUsers.fiap.validators;

import jakarta.validation.constraints.NotBlank;

public record PersonPostData (

    @NotBlank
    String name,

    @NotBlank
    String cpf_cnpj,

    @NotBlank
    String phone,

    @NotBlank
    String address

){
}

