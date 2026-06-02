package br.com.nvdi.gs.domain;

import br.com.nvdi.gs.application.DadosAtuais;

public class FazendaFrutas extends Fazenda {


    public FazendaFrutas() {}
    public FazendaFrutas (String nome, String proprietario, double latitude, double longitude) {
        super(nome, proprietario, latitude, longitude);
    }

    public String calcularRisco(DadosAtuais d) {
        if (d.getTemperatura() > 30 && d.getUmidade() < 40) {
            return "ALERTA: Risco de escaldadura e desidratação das frutas.";
        }

        if ( d.getUmidade() > 85 && d.getChuva() < 10) {
            return "ALERTA: Risco de proliferação de fungos devido à alta umidade.";
        }

        if (d.getChuva()  > 80) {
            return "ALERTA: Risco de queda prematura dos frutos e alagamento.";
        }

        return "Condições estáveis para o pomar.";
    }

    public String calcularRisco(double chuva) {
        return (chuva > 50) ? "Risco para a colheita." : "Condições ideais.";
    }
}
