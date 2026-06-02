package br.com.nvdi.gs.presentation;

import br.com.nvdi.gs.application.APIApplication;
import br.com.nvdi.gs.application.DadosAtuais;
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
                case "1" -> cadastrarFazenda();
                case "2" -> listarFazendas();
                case "3" -> excluirFazendas();
                case "4" -> consultarPrevisao();
                case "0" -> rodando = false;
                default -> System.out.println("Opcao invalida!");
            }
            if (rodando) pausar();
        }

        System.out.println("\nObrigado por usar o Sistema de Monitoramento!");
    }

    private static void mostrarMenu() {
        String menu = """
            \n
            ╔══════════════════════════════════════╗
            ║        SISTEMA DE MONITORAMENTO      ║
            ╠══════════════════════════════════════╣
            ║                                      ║
            ║      1 - Cadastrar fazendas          ║
            ║      2 - Listar fazendas             ║
            ║      3 - Excluir fazendas            ║
            ║      4 - Consultar Previsao          ║
            ║      0 - Sair                        ║
            ║                                      ║
            ╚══════════════════════════════════════╝
            => Escolha uma opcao: """;
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

    private static void cadastrarFazenda() {
        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│     NOVO CADASTRO DE FAZENDA    │");
        System.out.println("└─────────────────────────────────┘");

        System.out.print("Nome da Fazenda: ");
        String nome = scanner.nextLine();
        System.out.print("Nome do Proprietario: ");
        String nomeProprietario = scanner.nextLine();
        System.out.print("Latitude (ex: 23.550520): ");
        double lat = Double.parseDouble(scanner.nextLine());
        System.out.print("Longitude (ex: 46.633308): ");
        double lon = Double.parseDouble(scanner.nextLine());

        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│          TIPO DE PLANTIO        │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│    1 - Graos                    │");
        System.out.println("│    2 - Frutas                   │");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("=> Escolha uma opcao: ");
        String tipo = scanner.nextLine();

        Fazenda f = tipo.equals("1") ? new FazendaGraos() : new FazendaFrutas();
        f.setNome(nome);
        f.setProprietario(nomeProprietario);
        f.setLatitude(lat);
        f.setLongitude(lon);

        if (tipo.equals("1")) {
            fazendaGraosRepo.salvar(f);
            System.out.println("\nFazenda de GRAOS cadastrada com sucesso!");
        } else {
            fazendaFrutasRepo.salvar(f);
            System.out.println("\nFazenda de FRUTAS cadastrada com sucesso!");
        }

        System.out.println("   ID da fazenda: " + f.getId());
    }

    private static void listarFazendas() {
        if (fazendaGraosRepo.exibirFazendas().isEmpty() && fazendaFrutasRepo.exibirFazendas().isEmpty()) {
            System.out.println("\nNenhuma fazenda cadastrada no sistema.");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│                 LISTA DE FAZENDAS               │");
        System.out.println("└─────────────────────────────────────────────────┘");

        listarFazendasGraos();
        listarFazendasFrutas();
    }

    private static void listarFazendasFrutas() {
        if (!fazendaFrutasRepo.exibirFazendas().isEmpty()) {
            System.out.println("\n═══════════════════════════════════════════════");
            System.out.println("            FAZENDAS DE FRUTAS");
            System.out.println("═══════════════════════════════════════════════");
            System.out.println(String.format("%-5s %-25s %-20s %-15s %-15s",
                    "ID", "NOME", "PROPRIETARIO", "LATITUDE", "LONGITUDE"));
            System.out.println("─────────────────────────────────────────────────────────");

            fazendaFrutasRepo.exibirFazendas().forEach(fazenda -> {
                System.out.println(String.format("%-5d %-25s %-20s %-15.6f %-15.6f",
                        fazenda.getId(),
                        fazenda.getNome(),
                        fazenda.getProprietario(),
                        fazenda.getLatitude(),
                        fazenda.getLongitude()));
            });
            System.out.println("═══════════════════════════════════════════════");
        } else {
            System.out.println("\nNenhuma fazenda de FRUTAS cadastrada.");
        }
    }

    private static void listarFazendasGraos() {
        if (!fazendaGraosRepo.exibirFazendas().isEmpty()) {
            System.out.println("\n═══════════════════════════════════════════════");
            System.out.println("            FAZENDAS DE GRAOS");
            System.out.println("═══════════════════════════════════════════════");
            System.out.println(String.format("%-5s %-25s %-20s %-15s %-15s",
                    "ID", "NOME", "PROPRIETARIO", "LATITUDE", "LONGITUDE"));
            System.out.println("─────────────────────────────────────────────────────────");

            fazendaGraosRepo.exibirFazendas().forEach(fazenda -> {
                System.out.println(String.format("%-5d %-25s %-20s %-15.6f %-15.6f",
                        fazenda.getId(),
                        fazenda.getNome(),
                        fazenda.getProprietario(),
                        fazenda.getLatitude(),
                        fazenda.getLongitude()));
            });
            System.out.println("═══════════════════════════════════════════════");
        } else {
            System.out.println("\nNenhuma fazenda de GRAOS cadastrada.");
        }
    }

    private static void excluirFazendas() {
        Repository repo = null;
        String tipoFazenda = "";

        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│           EXCLUIR FAZENDA        │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│    1 - Graos                      │");
        System.out.println("│    2 - Frutas                     │");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("=> Escolha uma opcao: ");
        String tipo = scanner.nextLine();

        if (tipo.equals("1")) {
            listarFazendasGraos();
            repo = fazendaGraosRepo;
            tipoFazenda = "GRAOS";
        } else if (tipo.equals("2")) {
            listarFazendasFrutas();
            repo = fazendaFrutasRepo;
            tipoFazenda = "FRUTAS";
        } else {
            System.out.println("Opcao invalida!");
            return;
        }

        if (repo.exibirFazendas().isEmpty()) {
            System.out.println("\nNao ha fazendas de " + tipoFazenda + " para excluir.");
            return;
        }

        System.out.print("\nDigite o ID da fazenda que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Fazenda fazenda = repo.encontrarFazenda(id);
        if (fazenda != null) {
            System.out.print("\nTem certeza que deseja excluir a fazenda '" +
                    fazenda.getNome() + "'? (s/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                repo.remover(id);
                System.out.println("Fazenda excluida com sucesso!");
            } else {
                System.out.println("Exclusao cancelada.");
            }
        } else {
            System.out.println("Fazenda com ID " + id + " nao encontrada!");
        }
    }

    private static void consultarPrevisao() {
        Repository repo = null;
        String tipoFazenda = "";

        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│        CONSULTAR PREVISAO       │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│    1 - Graos                    │");
        System.out.println("│    2 - Frutas                   │");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("=> Escolha uma opcao: ");
        String tipo = scanner.nextLine();

        if (tipo.equals("1")) {
            listarFazendasGraos();
            repo = fazendaGraosRepo;
            tipoFazenda = "GRAOS";
        } else if (tipo.equals("2")) {
            listarFazendasFrutas();
            repo = fazendaFrutasRepo;
            tipoFazenda = "FRUTAS";
        } else {
            System.out.println("Opcao invalida!");
            return;
        }

        if (repo.exibirFazendas().isEmpty()) {
            System.out.println("\nNao ha fazendas de " + tipoFazenda + " cadastradas.");
            return;
        }

        System.out.print("\nDigite o ID da fazenda para consultar a previsao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Fazenda fazenda = repo.encontrarFazenda(id);
        if (fazenda == null) {
            System.out.println("Fazenda com ID " + id + " nao encontrada!");
            return;
        }

        System.out.println("\nBuscando dados climaticos...");

        APIApplication app = new APIApplication();
        DadosAtuais dadosClimaticos = app.getData(fazenda.getLatitude(), fazenda.getLongitude());

        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│              PREVISAO PARA A FAZENDA            │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  Nome: " + fazenda.getNome());
        System.out.println("│  Proprietario: " + fazenda.getProprietario());
        System.out.println("│  Localizacao: " + String.format("%.6f", fazenda.getLatitude()) +
                ", " + String.format("%.6f", fazenda.getLongitude()));
        System.out.println("└─────────────────────────────────────────────────┘");

        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│                CONDICOES CLIMATICAS             │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  Temperatura: " + dadosClimaticos.getTemperatura() + "C");
        System.out.println("│  Umidade: " + dadosClimaticos.getUmidade() + "%");
        System.out.println("│  Chuva: " + dadosClimaticos.getChuva() + " mm");
        System.out.println("│  Vento: " + dadosClimaticos.getVento() + " km/h");
        System.out.println("└─────────────────────────────────────────────────┘");

        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│                 ANALISE DE RISCO                │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println(" "+fazenda.calcularRisco(dadosClimaticos));
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    private static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}