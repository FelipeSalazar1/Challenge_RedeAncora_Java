package com.redeAncoraUsers.fiap.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        var produtos = buscarProdutos();
        var heap = new MaxHeap(produtos.size());

        produtos.forEach(p -> heap.insert(getId(p), p));

        System.out.println("Produtos com maior ID até o Menor:");
        for (int i = 0; i < 10 && !heap.isEmpty(); i++)
            System.out.println(heap.extractMax().path("id").asLong());
    }

    public void getKProdutosComMenorId() {
        var produtos = buscarProdutos();
        var heap = new MinHeap(produtos.size());

        produtos.forEach(p -> heap.insert(getId(p), p));

        System.out.println("Produtos com menor ID até o Maior:");
        for (int i = 0; i < 10 && !heap.isEmpty(); i++)
            System.out.println(heap.extractMin().path("id").asLong());
    }

    private long getId(JsonNode produto) {
        var similares = produto.path("similares");
        if (similares.isArray() && similares.size() > 0)
            return similares.get(0).path("id").asLong();
        return 0;
    }

    private List<JsonNode> buscarProdutos() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);

        var body = """
            {
                "veiculoFiltro": {
                    "veiculoPlaca": "JGO2484"
                },
                "superbusca": "AMORTECEDOR",
                "pagina": 0,
                "itensPorPagina": 100
            }
            """;

        var request = new HttpEntity<>(body, headers);
        try {
            var response = restTemplate.exchange(URL, HttpMethod.POST, request, JsonNode.class);
            var dataArray = Optional.ofNullable(response.getBody())
                .map(bodyJson -> bodyJson.path("pageResult").path("data"))
                .orElse(null);

            if (dataArray != null && dataArray.isArray()) {
                var produtos = new ArrayList<JsonNode>();
                dataArray.forEach(produtos::add);
                return produtos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static class MinHeap {
        private final long[] keys;
        private final JsonNode[] values;
        private int size;

        public MinHeap(int capacidade) {
            keys = new long[capacidade];
            values = new JsonNode[capacidade];
        }

        public void insert(long key, JsonNode value) {
            keys[size] = key;
            values[size] = value;
            var i = size++;
            while (i > 0 && keys[i] < keys[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        public JsonNode extractMin() {
            var min = values[0];
            keys[0] = keys[--size];
            values[0] = values[size];
            heapify(0);
            return min;
        }

        private void heapify(int i) {
            var smallest = i;
            var left = 2 * i + 1;
            var right = 2 * i + 2;
            if (left < size && keys[left] < keys[smallest]) smallest = left;
            if (right < size && keys[right] < keys[smallest]) smallest = right;
            if (smallest != i) {
                swap(i, smallest);
                heapify(smallest);
            }
        }

        private void swap(int i, int j) {
            var tempKey = keys[i];
            var tempValue = values[i];
            keys[i] = keys[j];
            values[i] = values[j];
            keys[j] = tempKey;
            values[j] = tempValue;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class MaxHeap {
        private final long[] keys;
        private final JsonNode[] values;
        private int size;

        public MaxHeap(int capacidade) {
            keys = new long[capacidade];
            values = new JsonNode[capacidade];
        }

        public void insert(long key, JsonNode value) {
            keys[size] = key;
            values[size] = value;
            var i = size++;
            while (i > 0 && keys[i] > keys[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        public JsonNode extractMax() {
            var max = values[0];
            keys[0] = keys[--size];
            values[0] = values[size];
            heapify(0);
            return max;
        }

        private void heapify(int i) {
            var largest = i;
            var left = 2 * i + 1;
            var right = 2 * i + 2;
            if (left < size && keys[left] > keys[largest]) largest = left;
            if (right < size && keys[right] > keys[largest]) largest = right;
            if (largest != i) {
                swap(i, largest);
                heapify(largest);
            }
        }

        private void swap(int i, int j) {
            var tempKey = keys[i];
            var tempValue = values[i];
            keys[i] = keys[j];
            values[i] = values[j];
            keys[j] = tempKey;
            values[j] = tempValue;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
