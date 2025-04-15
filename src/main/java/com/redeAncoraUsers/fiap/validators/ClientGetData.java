package com.redeAncoraUsers.fiap.validators;

import com.redeAncoraUsers.fiap.model.Client;

public record ClientGetData(Long id, String name, String cpf_cnpj, String phone, String address) {
    public ClientGetData(Client client){
        this(client.getId(), client.getName(), client.getCpf_cnpj(), client.getPhone(), client.getAddress());
    }
}
