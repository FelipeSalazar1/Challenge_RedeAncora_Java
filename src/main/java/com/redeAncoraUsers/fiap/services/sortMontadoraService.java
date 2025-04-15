package com.redeAncoraUsers.fiap.services;

import com.redeAncoraUsers.fiap.validators.MontadoraGetData;
import com.redeAncoraUsers.fiap.validators.MontadoraResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class sortMontadoraService {
    private final RestTemplate restTemplate;

    public sortMontadoraService(){
        this.restTemplate = new RestTemplate();
    }

    public String getData(){
        String url = "https://api-stg-catalogo.redeancora.com.br/superbusca/api/integracao/veiculo/montadoras/query";

        MontadoraGetData body = new MontadoraGetData(0, 100);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3NDQ2Mzg3ODEsImV4cCI6MTc0NDcyNTE4MSwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.haDU8Lh3oK2UCKKYpoqeH1nQaS3PiKXt3VwIV6EKNWbk__Vr5DYianFitHIqYLOvoNKmcUWWBwD7mVmJ2PMgWCwaAtIHlmseXHh_ElfQd9TE7gctH9lY_L16mv9FUhb-JWCtn5eaP0jCD-cIBF4mX-hGnnFdzPXdRYcPbQI959mnBrQcTjc-mogXsxifm60QqGRudJLOcSo6O58gHbFk44hwXKdC3jwPV3ueyNQiNjEx4bLpuMfxYZMGrNl4h-KO4iCi1_8ppoCN8AOFpOrZDO1CsK-c6HlSmm_JIBmeoTgAGY5z1Wiin2nWZhxvJgFAUcV-6ZjPTISCL2H-GKhyAjY3hTzyK-NM8vOrs7Xg5_xV_Ev2gzBvDPxHMNAz5oD7OSX-RpF40EE0GvdwbFny748dkC7-RjSKNKcMJjPdwjtWvPT-amLhAQB4xZCS2YGy1EwN5_w3SWmD_qvGj-4WufoXcVzTeWbszT_pvMqEIY4LYJN6FD0tyh54gtU1y1I1FdSPfkbbdY1yVI0DVwTrm7KEqjCFo9pDeP-546k82-wBE_wfb0oQ0Fixw6THhOxh3_HkqlHsadIJS05uVPsi6eW5qS8C56KZ6yh7UALVvPv5o8R5vcGDLgIhS2ESsaTJ4fHeGvarsoT9eJSp7zDE4P91djJ4VBjGiGUrGlwAMls";
        headers.setBearerAuth(token);

        HttpEntity<MontadoraGetData> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
        );
        return response.getBody();
    }

    public MontadoraResponse converterJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, MontadoraResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
