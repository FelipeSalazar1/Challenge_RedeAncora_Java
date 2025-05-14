package br.com.redeAncora.System.validators;

import br.com.redeAncora.System.model.Client;

public record ClientGetData(Long id, String name, String cpf_cnpj, String phone, String address, Long user_id) {
    public ClientGetData(Client client){
        this(client.getId(), client.getName(), client.getCpf_cnpj(), client.getPhone(), client.getAddress(), client.getUser().getId());
    }
}
