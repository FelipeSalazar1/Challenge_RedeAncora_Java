package com.redeAncoraUsers.fiap.validators;

import com.redeAncoraUsers.fiap.model.PersonEntity;

public record PersonGetData(Long id, String name, String cpf_cnpj, String phone, String address) {
    public PersonGetData(PersonEntity person){
        this(person.getId(), person.getName(), person.getCpf_cnpj(), person.getPhone(), person.getAddress());
    }
}
