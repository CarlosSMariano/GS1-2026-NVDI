package br.com.nvdi.gs.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAtuais {

    private String time;
    @JsonProperty("temperature_2m")
    private double temperatura;
    @JsonProperty("relative_humidity_2m")
    private int umidade;
    @JsonProperty("wind_speed_10m")
    private double vento;
    @JsonProperty("rain")
    private double chuva;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public double getVento() {
        return vento;
    }

    public void setVento(double vento) {
        this.vento = vento;
    }

    public double getChuva() {
        return chuva;
    }

    public void setChuva(double chuva) {
        this.chuva = chuva;
    }

    @Override
    public String toString() {
        return "Dados Atuais { " +
                "\nHora: " + time + "\nTemperatura: " + this.temperatura +
                "\nUmidade: " + this.umidade + "\nVento: " + this.vento +
                "\nChuva: " + this.chuva + "\n}";
    }
}
