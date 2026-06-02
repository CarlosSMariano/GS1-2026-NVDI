package br.com.nvdi.gs.domain;

import br.com.nvdi.gs.application.DadosAtuais;

public class FazendaGraos extends Fazenda {

    public FazendaGraos() {}
    public FazendaGraos (String nome, String proprietario, double latitude, double longitude) {
        super(nome, proprietario, latitude, longitude);
    }

    public String calcularRisco(DadosAtuais d) {
        if (d.getTemperatura() > 33 && d.getChuva() < 5 && d.getUmidade() < 30) {
            return "ALERTA: Risco alto de estresse hídrico para Grãos.";
        }
        if (d.getChuva() > 80) {
            return "ALERTA: Risco de alagamento para a plantação de Grãos.";
        }
        return "Condições estáveis para Grãos.";
    }

}
