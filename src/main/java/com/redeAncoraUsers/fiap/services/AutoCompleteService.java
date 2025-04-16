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
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3NDQ3NjUxMzUsImV4cCI6MTc0NDg1MTUzNSwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.kblEXCv16Le6X5uozTHnzp9Cgc_6ok3iCSsDo37DI4c3aYPB8b_Lz2Y8hmaK50_3MxQrUre1fdnQM1MLDfAePzVPjqMP6RsSXghhW9mjyKo8lC5aeZyzokLYzLYQ2XQ6y3D3Qygv809Hs9JP9xSVrt2fCm-CbaHak6lmTnLAcIaM30ESL6Hfba8xP0sFisiVQRGJ6JPZE-R0Qrj-5DL9rM2TLvnEMiPFo5TWn3C93QjBGI_hwFuu5Fs9GmJRin2D2NYGXyv_P0C8Bv5jDwVSV1v2zCxaT4VzCcqBD-I1ojrrkQsUX8pYwRcrStN3mSeLZQNdCOWsrB7Aa6ITvBu-Q-1J9N5ldi1CAkEAXBdilRk676um3obYzaP4hoYjiuTxjMd_GKPoRMMtZUqSccUoAohUIpBjGsFRtNhAZZ91EwRQ6QwHcc_10fTGZIajLKooFqqs7hQZA1pSQSbgVbdUw7Aokow5olmBQRRgLAcZT_mlUVkxs0hK-u4gHjUdtBhYAcbXLI46KbX5vG3nhVHXMiJVgl81ZGOS4PWjBua5yLMiocs7sGZN2Ah1v5G2cd31abjeLOedivi6KW4vV_qeJ1pCAviu_lR_tL8i9U-ismqiVXrsai0-iFfI8KmUhC3eMqHrvWmS2BCW2zPJbdqhjDgM7VZB_U7L07bD74WM47s";

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
