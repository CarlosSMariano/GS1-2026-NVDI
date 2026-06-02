package br.com.nvdi.gs.domain;

import br.com.nvdi.gs.application.DadosAtuais;

public class Fazenda {
    private int id;
    private String nome;
    private String proprietario;
    private double latitude;
    private double longitude;

    Fazenda() {
    }

    Fazenda(String nome, String proprietario, double latitude, double longitude) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String calcularRisco(DadosAtuais d) {
        return (d.getChuva() > 80) ? "Risco de Alagamento" : "Condições Normais";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " | Nome: "  + this.nome + " | Proprietario: " + this.proprietario;
    }
}