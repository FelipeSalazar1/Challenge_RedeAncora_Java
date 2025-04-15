package com.redeAncoraUsers.fiap.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.redeAncoraUsers.fiap.utils.MaxHeapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class AutoCompleteService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://api-stg-catalogo.redeancora.com.br/superbusca/api/integracao/catalogo/v2/produtos-filhos/query";
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3NDQ2NzkyMTAsImV4cCI6MTc0NDc2NTYxMCwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.HH17qojHTt6R8Tg_-6w8Lqjy8CUCyfpTERge9CnkkrsNs7nS4DLUPPnqbX01FtKDVKtmjZ3i2JCAPqw8BXgPs5kMs7DWVpaa2-OjQHLqYChQPwC0aJbjB9t36C6kp30m4HA4Sy9hBHcjgi21SV2wjbJxtGS59TKFN3Pm4H4OIhc2I_RNyACIaFfwvj87h766F5zBxcn0u8FWevtew8rVpesej_scfX9pighg6s9k89i55vk0qOQpwrtH2eB25vpkvjgMFdtMeMQybm5LF9W53atQUnKqxry6bJ-GWoRV0OdnOh36YWrTVAwdoyeebZOy8c2P_wJI0zcRQkdp1NlesH9SSHurtVecCfegwoVCSt4KK-A3ipuvzt1hq1gtlt81-TX2nQ5h5llRsXJ0sGExZ_aFYe8Us6aIl2WcWZ6lqwmdOAxHl5aUkzwjkET47P-TcJptgQJMD_ftupa2llFI1qAY0wHIvF8tPTM5cyWS2tHIjwDu4t7GwieGzJIGJTtszd9ujQNCvQVA9usp0OeNb9zZFcgHEJCE01ixh74t-a6XJ7nKwggOksYFF3nd2zPqCIehVrUpfUa6ooAGG2cbq82ccJPU1ncz8Csynm8yH3m6jw1RrJecrfBgx3HDJl0fcOqYDAhzREqIfKiwlWEOPDiS8JJolnV4-umNbt5b4Cs";

    public List<String> suggest(String prefix) {
        Set<String> allWords = new HashSet<>();
        List<JsonNode> products = fetchProducts();

        for (JsonNode product : products) {
            JsonNode productData = product.path("data");
            String productName = productData.path("nomeProduto").asText();
            String[] words = productName.split("\\s+");
            allWords.addAll(Arrays.asList(words));
        }

        return getSuggestions(prefix.toUpperCase(), new ArrayList<>(allWords));
    }

    private List<String> getSuggestions(String prefix, List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.toUpperCase().startsWith(prefix)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        MaxHeapUtils heap = new MaxHeapUtils(frequencyMap.size());

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            JsonNode node = new TextNode(entry.getKey());
            heap.insert(entry.getValue(), node);
        }

        List<String> suggestions = new ArrayList<>();
        int limit = 4;

        while (!heap.isEmpty() && suggestions.size() < limit) {
            suggestions.add(heap.extractMax().asText());
        }
        System.out.println("Suggestions: " + suggestions);
        return suggestions;
    }

    private List<JsonNode> fetchProducts() {
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
