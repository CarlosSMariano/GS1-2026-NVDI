package br.com.nvdi.gs.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaDTO {

    @JsonProperty("current")
    private DadosAtuais current;

    public DadosAtuais getCurrent(){
        return current;
    }
}
