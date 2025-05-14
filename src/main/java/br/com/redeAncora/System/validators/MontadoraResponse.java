package br.com.redeAncora.System.validators;

import java.util.List;

public record MontadoraResponse(
        int count,
        List<Montadora> data
) {
    public record Montadora(int id, String descricao) {}
}
