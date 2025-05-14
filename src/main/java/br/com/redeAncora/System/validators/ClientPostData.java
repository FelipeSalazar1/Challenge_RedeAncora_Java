package br.com.redeAncora.System.validators;

import jakarta.validation.constraints.NotBlank;

public record ClientPostData (

    @NotBlank
    String name,

    @NotBlank
    String cpf_cnpj,

    @NotBlank
    String phone,

    @NotBlank
    String address,

    Long user_id

){
}

