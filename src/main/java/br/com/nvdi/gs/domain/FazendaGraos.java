package br.com.nvdi.gs.domain;

public class FazendaGraos extends Fazenda {

    public FazendaGraos() {}
    FazendaGraos (String nome, String proprietario, double latitude, double longitude) {
        super(nome, proprietario, latitude, longitude);
    }

    public String calcularRisco(double temp, double chuva, int umidade) {
        if (temp > 33 && chuva < 5 && umidade < 30) {
            return "ALERTA: Risco alto de estresse hídrico para Grãos.";
        }
        if (chuva > 80) {
            return "ALERTA: Risco de alagamento para a plantação de Grãos.";
        }
        return "Condições estáveis para Grãos.";
    }

}
