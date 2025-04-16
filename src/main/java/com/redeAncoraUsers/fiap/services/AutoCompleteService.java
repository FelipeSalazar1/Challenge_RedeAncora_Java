package com.redeAncoraUsers.fiap.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class AutoCompleteService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://api-stg-catalogo.redeancora.com.br/superbusca/api/integracao/catalogo/v2/produtos-filhos/query";
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3NDQ4MzYwNTksImV4cCI6MTc0NDkyMjQ1OSwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.i5sc0ZZEGuJkb7iegBdbbL38h5SW1FqWUCW1BZxoMEQ-ItXfM1TT-jlvH3NjW8mronFADHNmVXVM9GYFntFnv9xaur3XkaHsL6gK7SvURfoT34ez177qb9IbpiS6lqQIqgzt8BRyVzCx9HsrRYwCw8q_h_6IjA7hnpuMQeAxJa_7N-zdLHPeYoSXwsLMU73HzL2N-_NC3edXyY926fNrUW4RF4GDYF8S-YNRDSrdN_PNM70I2Z8uMFOj8jtz6PW0AUQOLdbgYMFfevLxiwjV5msZoY3ZVGyCfw29qIKc6uCCPacp0Z9pEZE_c4PaBDvH6yytpcpRiByxL6VwTx-B69eprHNtnrlGYs4-XGnIurj7_mzNy1dMOsaauzktGhbnF40PNVhP-IGbH-YMADe2SZiDd-MO1wE5n3ClUdQqNHNzclFIzeRclH9FB-_r_Ne8PaaYE1pwBTdhje55l9tYsv1MWZNzB1bqzdjygdzbPwanTakRXoJ3we_KBvUqLVWvBgtCgdZpgck2PSox0Uru9vNNV-5Rh0f9b-0KpDzYPdcL1BG0adWwT9ifTPRK-Y4nyZSKumgrgaS1NR8bUshScG7FknC8LkCLQHXMHwpcvetJ3bsu1s7-pWiAF3fUTgoCR2PAdR6Q2giFwLB5UUkNpWdPFyo-CMXCz8OJsP4rijc";

    public static List<JsonNode> fetchProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);

        String body = """
                    {
                        "produtoFiltro": {
                            "nomeFabricante": "BOSCH"
                        },
                        "veiculoFiltro": {
                            "veiculoPlaca": "DEM8i14"
                        },
                        "pagina": 0,
                        "itensPorPagina": 100
                    }
                """;

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(URL, HttpMethod.POST, request, JsonNode.class);
            JsonNode dataArray = Optional.ofNullable(response.getBody())
                    .map(bodyJson -> bodyJson.path("pageResult").path("data"))
                    .orElse(null);

            if (dataArray != null && dataArray.isArray()) {
                List<JsonNode> products = new ArrayList<>();
                for (JsonNode node : dataArray) {
                    products.add(node);
                }
                return products;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
