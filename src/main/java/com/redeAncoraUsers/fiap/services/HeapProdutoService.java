package com.redeAncoraUsers.fiap.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redeAncoraUsers.fiap.Utils.MaxHeapUtils;
import com.redeAncoraUsers.fiap.Utils.MinHeapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class HeapProdutoService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String URL = "https://api-stg-catalogo.redeancora.com.br/superbusca/api/integracao/catalogo/v2/produtos/query/sumario";
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3NDQ2NzkyMTAsImV4cCI6MTc0NDc2NTYxMCwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.HH17qojHTt6R8Tg_-6w8Lqjy8CUCyfpTERge9CnkkrsNs7nS4DLUPPnqbX01FtKDVKtmjZ3i2JCAPqw8BXgPs5kMs7DWVpaa2-OjQHLqYChQPwC0aJbjB9t36C6kp30m4HA4Sy9hBHcjgi21SV2wjbJxtGS59TKFN3Pm4H4OIhc2I_RNyACIaFfwvj87h766F5zBxcn0u8FWevtew8rVpesej_scfX9pighg6s9k89i55vk0qOQpwrtH2eB25vpkvjgMFdtMeMQybm5LF9W53atQUnKqxry6bJ-GWoRV0OdnOh36YWrTVAwdoyeebZOy8c2P_wJI0zcRQkdp1NlesH9SSHurtVecCfegwoVCSt4KK-A3ipuvzt1hq1gtlt81-TX2nQ5h5llRsXJ0sGExZ_aFYe8Us6aIl2WcWZ6lqwmdOAxHl5aUkzwjkET47P-TcJptgQJMD_ftupa2llFI1qAY0wHIvF8tPTM5cyWS2tHIjwDu4t7GwieGzJIGJTtszd9ujQNCvQVA9usp0OeNb9zZFcgHEJCE01ixh74t-a6XJ7nKwggOksYFF3nd2zPqCIehVrUpfUa6ooAGG2cbq82ccJPU1ncz8Csynm8yH3m6jw1RrJecrfBgx3HDJl0fcOqYDAhzREqIfKiwlWEOPDiS8JJolnV4-umNbt5b4Cs";

      public void getKProdutosComMaiorId() {
        List<JsonNode> produtos = buscarProdutos();
        MaxHeapUtils heap = new MaxHeapUtils(produtos.size());

        for (JsonNode p : produtos) {
            heap.insert(getId(p), p);
        }

        System.out.println("Produtos com maior ID até o Menor:");
        for (int i = 0; i < 10 && !heap.isEmpty(); i++) {
            System.out.println(heap.extractMax().path("id").asLong());
        }
    }

    public void getKProdutosComMenorId() {
        List<JsonNode> produtos = buscarProdutos();
        MinHeapUtils heap = new MinHeapUtils(produtos.size());

        for (JsonNode p : produtos) {
            heap.insert(getId(p), p);
        }

        System.out.println("Produtos com menor ID até o Maior:");
        for (int i = 0; i < 10 && !heap.isEmpty(); i++) {
            System.out.println(heap.extractMin().path("id").asLong());
        }
    }

    private long getId(JsonNode produto) {
        JsonNode similares = produto.path("similares");
        if (similares.isArray() && similares.size() > 0) {
            return similares.get(0).path("id").asLong();
        }
        return 0;
    }

    private List<JsonNode> buscarProdutos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);

        String body = """
            {
                "veiculoFiltro": {
                    "veiculoPlaca": "JGO2484"
                },
                "superbusca": "AMORTECEDOR",
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
                List<JsonNode> produtos = new ArrayList<>();
                for (JsonNode node : dataArray) {
                    produtos.add(node);
                }
                return produtos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
