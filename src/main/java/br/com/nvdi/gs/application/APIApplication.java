package br.com.nvdi.gs.application;


import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIApplication {

    private static final String URL_HEAD = "https://api.open-meteo.com/v1/forecast?";
    private static final String URL_TAIL = "&current=precipitation,temperature_2m,relative_humidity_2m,wind_speed_10m,rain,surface_pressure";
    private ObjectMapper objectMapper = new ObjectMapper();

    public DadosAtuais getData(double latitude, double longitude){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_HEAD+"latitude="+latitude+"&longitude="+longitude+URL_TAIL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

           ClimaDTO dto = objectMapper.readValue(response.body(), ClimaDTO.class);
           return dto.getCurrent();

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}