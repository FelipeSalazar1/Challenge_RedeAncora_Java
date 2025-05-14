package br.com.redeAncora.System.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import br.com.redeAncora.System.services.AutoCompleteService;

import java.util.*;

public class AutocompleteUtils {

    private final String[] carParts = {
            "Alternador", "Amortecedor", "Ar Condicionado", "Bateria", "Bico Injetor",
            "Câmbio", "Calota", "Catalisador", "Comando de Válvulas", "Correia Dentada",
            "Disco de Freio", "Embreagem", "Farol", "Filtro de Ar", "Filtro de Combustível",
            "Filtro de Óleo", "Mola", "Pistão", "Radiador", "Roda",
            "Rolamento", "Sensor de Temperatura", "Sensor de Velocidade", "Tampa de Válvula",
            "Tanque de Combustível", "Trem de Força", "Trambulador", "Vela de Ignição",
            "Ventilador", "Bico de Pulverização", "Boia de Combustível", "Braço de Suspensão",
            "Cabeçote", "Cárter", "Carburador", "Coxim de Motor", "Correia Poly-V",
            "Cruzeiro", "Cubo de Roda", "Curva de Escape", "Disco de Embreagem",
            "Espelho Retrovisor", "Fiação Elétrica", "Filtro de Cabine", "Jogo de Juntas",
            "Lâmpada de Farol", "Lona de Freio", "Módulo de Injeção", "Mola de Suspensão",
            "Motor de Arranque", "Muffler", "Nível de Óleo", "Oleo de Câmbio",
            "Pastilha de Freio", "Pneus", "Polia", "Radiador de Óleo",
            "Reator de Farol", "Régua de Câmbio", "Revestimento de Banco",
            "Sensor de Pressão", "Sensor de Rotações", "Suporte de Motor", "Suspensão",
            "Tampa de Óleo", "Tensão de Correia", "Termostato", "Tornilho de Roda",
            "Trava de Segurança", "Tubo de Escape", "Vidro Elétrico", "Volante",
            "Bomba de Combustível", "Bucha de Suspensão", "Capa de Distribuidor",
            "Caixa de Direção", "Compressor de Ar", "Conector de Cabos", "Controles de Freio",
            "Corta-fogo", "Desempenho", "Distribuidor", "Estribo", "Estrutura de Caixa",
            "Gás de Ar", "Injeção Eletrônica", "Lavagem de Motor", "Limpador de Parabrisa",
            "Manta de Asfalto", "Modelo de Carro", "Motor de Ventilação",
            "Mundo Automotivo", "Painel de Controle", "Pistão de Comando",
            "Relé de Farol", "Reservatório de Água", "Rodas", "Sensor de Direção",
            "Suspensão Dianteira", "Traseira", "Tubo de Combustível",
            "Válvula de Escape", "Ventoinha", "Visor de Combustível", "Volante de Comando"
    };

    public List<String> suggest(String prefix) {
        Bst bst = new Bst();

        for (String part : carParts) {
            String[] words = part.toUpperCase().split("\\s+");
            for (String word : words) {
                bst.insert(word);
            }
        }

        return bst.getSuggestions(prefix.toUpperCase(), 4);
    }

    public List<String> suggestApi(String prefix) {
        Bst bst = new Bst();
        List<JsonNode> products = AutoCompleteService.fetchProducts();
    
        for (JsonNode product : products) {
            JsonNode productData = product.path("data");
            String productName = productData.path("nomeProduto").asText();
            String[] words = productName.toUpperCase().split("\\s+");
    
            for (String word : words) {
                bst.insert(word);
            }
        }
    
        return bst.getSuggestions(prefix.toUpperCase(), 4);
    }

}
