package br.com.nvdi.gs.domain;

public class FazendaFrutas extends Fazenda {


    FazendaFrutas() {}
    FazendaFrutas (String nome, String proprietario, double latitude, double longitude) {
        super(nome, proprietario, latitude, longitude);
    }

    public String calcularRisco(double temp, double chuva, int umidade) {
        if (temp > 30 && umidade < 40) {
            return "ALERTA: Risco de escaldadura e desidratação das frutas.";
        }

        if (umidade > 85 && chuva < 10) {
            return "ALERTA: Risco de proliferação de fungos devido à alta umidade.";
        }

        if (chuva > 80) {
            return "ALERTA: Risco de queda prematura dos frutos e alagamento.";
        }

        return "Condições estáveis para o pomar.";
    }

    public String calcularRisco(double chuva) {
        return (chuva > 50) ? "Risco para a colheita." : "Condições ideais.";
    }
}
