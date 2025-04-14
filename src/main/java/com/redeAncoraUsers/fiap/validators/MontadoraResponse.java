package com.redeAncoraUsers.fiap.validators;

import java.util.List;

public record MontadoraResponse(
        int count,
        List<Montadora> data
) {
    public record Montadora(int id, String descricao) {}
}
