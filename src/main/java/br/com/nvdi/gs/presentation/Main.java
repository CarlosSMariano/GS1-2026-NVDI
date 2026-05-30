package br.com.nvdi.gs.presentation;

import br.com.nvdi.gs.domain.*;
import br.com.nvdi.gs.infrastructure.Repository;
import java.util.Scanner;

public class Main {
    private static final Repository fazendaFrutasRepo = new Repository();
    private static final Repository fazendaGraosRepo = new Repository();
    private static final Scanner scanner = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String[] CORES = {
            "\u001B[31m", "\u001B[33m", "\u001B[32m",
            "\u001B[36m", "\u001B[34m", "\u001B[35m"
    };

    public static void main(String[] args) {
        boolean rodando = true;
        while (rodando) {
            mostrarMenu();

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> cadastrarFazenda("GRAOS");
                case "2" -> cadastrarFazenda("FRUTAS");
                case "3" -> listarFazendas();
                case "0" -> rodando = false;
                default -> System.out.println("Opção inválida!");
            }
            if (rodando) pausar();
        }
    }

    private static void mostrarMenu() {
        String menu = """
            ===========================
            ~ SISTEMA DE MONITORAMENTO ~
            ===========================
                1 - Cadastrar fazendas
                2 - Listar fazendas
                3 - Editar fazendas
                4 - Excluir fazendas
                0 - sair
            ==========================
            --> Escolha uma opção: """;
        imprimirMenu(menu, 10);
    }

    public static void imprimirMenu(String texto, int velocidade) {
        for (int i = 0; i < texto.length(); i++) {
            String cor = CORES[i % CORES.length];
            System.out.print(cor + texto.charAt(i));
            try {
                Thread.sleep(velocidade);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print(RESET);
    }

    private static void cadastrarFazenda(String tipo) {
        System.out.print("\n--> Nome da Fazenda: ");
        String nome = scanner.nextLine();
        System.out.print("--> Latitude: ");
        double lat = Double.parseDouble(scanner.nextLine());
        System.out.print("--> Longitude: ");
        double lon = Double.parseDouble(scanner.nextLine());

        Fazenda f = tipo.equals("GRAOS") ? new FazendaGraos() : new FazendaFrutas();
        f.setNome(nome);
        f.setLatitude(lat);
        f.setLongitude(lon);

        if (tipo.equals("GRAOS")) {
            fazendaGraosRepo.salvar(f);
        } else {
            fazendaFrutasRepo.salvar(f);
        }

        System.out.println("--> Cadastrado com sucesso!");
    }

    private static void listarFazendas() {
        if (fazendaGraosRepo.getFazendas().isEmpty() && fazendaFrutasRepo.getFazendas().isEmpty()) {
            System.out.println("--> Nenhuma fazenda cadastrada.");
            return;
        }

        if(!fazendaGraosRepo.getFazendas().isEmpty()) {

            fazendaGraosRepo.getFazendas().forEach(System.out::println);
        }

        if (!fazendaFrutasRepo.getFazendas().isEmpty()) {
            fazendaFrutasRepo.getFazendas().forEach(System.out::println);
        }
    }


    private static void pausar() {
        System.out.println("\nPressione < ENTER > para continuar...");
        scanner.nextLine();
    }
}