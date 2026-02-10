import java.util.*;
import java.io.*;


public class Hotel {

    static Quarto[] quartos = new Quarto[200];
    static Hospede[] hospedes = new Hospede[1000];
    static Reserva[] reservas = new Reserva[1000];

    static int totalQuartos = 0;
    static int totalHospedes = 0;
    static int totalReservas = 0;


    // INICIO DO MAIN - MENU PRINCIPAL
    public static void main(String[] args) {

        carregarQuartosCSV(); // CARREGAR QUARTOS.CSV
        carregarHospedesCSV(); // CARREGAR HOSPEDES.CSV
        carregarReservasCSV(); // CARREGAR RESERVAS.CSV
        atualizarOcupacaoQuartos(); // ATUALIZAR OCUPAÇAO DOS QUARTOS

        Scanner input = new Scanner(System.in);
        int opcao;

        do { // Menu principal
            System.out.println();
            System.out.println("===== HOTEL RATOS DO ESGOTO =====");
            System.out.println("Bem-vindo ao Hotel Ratos do Esgoto, por favor escolha uma das seguintes opções para avançar:");
            System.out.println("1. Gestão de Quartos >>>");
            System.out.println("2. Gestão de Hóspedes >>>");
            System.out.println("3. Gestão de Reservas >>>");
            System.out.println("4. Sair >>>");
            System.out.print("Opção: ");

            opcao = input.nextInt();


            switch (opcao) {
                case 1:
                    menuQuartos(input);
                    break;
                case 2:
                    menuHospedes(input);
                    break;
                case 3:
                    menuReservas(input);
                    break;
                case 4:
                    guardarHospedesCSV(); // chamar o metodo para guardar alterações feitas aos hóspedes
                    guardarReservasCSV(); // chamar o metodo para guardar alterações feitas às reservas
                    System.out.println("Dados guardados!");
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    System.out.println();
            }

        } while (opcao != 4);

        input.close();
    } // FIM DO MAIN

    // 1 - MENU GESTÃO DE QUARTOS
    public static void menuQuartos(Scanner input) {

        int opcao;

        do {
            System.out.println();
            System.out.println("===== GESTÃO DE QUARTOS =====");
            System.out.println("1. Listar todos os quartos >>>");
            System.out.println("2. Listar quartos livres >>>");
            System.out.println("3. Listar quartos ocupados >>>");
            System.out.println("4. Ver detalhes de um quarto >>>");
            System.out.println("5. Voltar >>>");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    listarTodosQuartos();
                    break;
                case 2:
                    listarQuartosLivres();
                    break;
                case 3:
                    listarQuartosOcupados();
                    break;
                case 4:
                    detalhesQuarto(input);
                    break;
                case 5:
                    System.out.println("A voltar...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);
    } // FIM MENU QUARTOS

    // 2 - MENU GESTÃO DE HOSPEDES
    public static void menuHospedes(Scanner input) {

        int opcao;

        do {
            System.out.println();
            System.out.println("===== GESTÃO DE HÓSPEDES =====");
            System.out.println("1. Listar hóspedes >>>");
            System.out.println("2. Adicionar hóspede >>>");
            System.out.println("3. Procurar hóspede por documento >>>");
            System.out.println("4. Editar hóspede >>>");
            System.out.println("5. Voltar >>>");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    listarHospedes();
                    break;
                case 2:
                    adicionarHospede(input);
                    break;
                case 3:
                    procurarHospede(input);
                    break;
                case 4:
                    editarHospede(input);
                    break;
                case 5:
                    System.out.println("A voltar ao menu principal...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    System.out.println();

            }

        } while (opcao != 5);
    } // FIM MENU HOSPEDES

    // 3 - MENU GESTÃO DAS RESERVAS
    public static void menuReservas(Scanner input) {

        int opcao;

        do {
            System.out.println();
            System.out.println("===== GESTÃO DE RESERVAS =====");
            System.out.println("1. Criar reserva >>>");
            System.out.println("2. Listar reservas >>>");
            System.out.println("3. Listar reservas por quarto >>>");
            System.out.println("4. Listar reservas por hóspede >>>");
            System.out.println("5. Editar reservas >>>");
            System.out.println("6. Cancelar reserva >>>");
            System.out.println("7. Voltar >>>");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    criarReserva(input);
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    listarReservasPorQuarto(input);
                    break;
                case 4:
                    listarReservasPorHospede(input);
                    break;
                case 5:
                    editarReserva(input);
                    break;
                case 6:
                    cancelarReserva(input);
                    break;
                case 7:
                    System.out.println("A voltar...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    System.out.println();
            }

        } while (opcao != 7);
    } // FIM MENU RESERVAS








    // LISTAR OS QUARTOS TODOS
    public static void listarTodosQuartos() {

        if (totalQuartos == 0) { // verificaçao da existencia de quartos criados
            System.out.println("Não existem quartos.");
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("=== TODOS OS QUARTOS ===");

        for (int i = 0; i < totalQuartos; i++) {
            Quarto q = quartos[i];
            System.out.println(
                    "ID: " + q.id +
                            " | Número: " + q.numero +
                            " | Capacidade: " + q.capacidade +
                            " | Preço por noite: " + q.precoNoite + " €" +
                            " | Ocupado: " + (q.estaOcupado ? "Sim" : "Não")
            );
        }
    } // FIM LISTAR OS QUARTOS

    // VER QUARTOS LIVRES
    public static void listarQuartosLivres() {

        boolean encontrou = false;

        System.out.println();
        System.out.println("=== QUARTOS LIVRES ===");

        for (int i = 0; i < totalQuartos; i++) {
            if (!quartos[i].estaOcupado) {
                Quarto q = quartos[i];
                System.out.println(
                        "Número: " + q.numero +
                                " | Capacidade: " + q.capacidade
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não existem quartos livres."); // verificaçao se nao encontrou quartos
            System.out.println();
        }
    } // FIM QUARTOS LIVRES

    // VER QUARTOS OCUPADOS
    public static void listarQuartosOcupados() {

        boolean encontrou = false;

        System.out.println();
        System.out.println("=== QUARTOS OCUPADOS ===");

        for (int i = 0; i < totalQuartos; i++) {
            if (quartos[i].estaOcupado) {
                Quarto q = quartos[i];
                System.out.println(
                        "Número: " + q.numero +
                                " | Capacidade: " + q.capacidade
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não existem quartos ocupados.");
            System.out.println();
        }
    } // FIM QUARTOS OCUPADOS

    // VER DETALHES QUARTO
    public static void detalhesQuarto(Scanner input) {

        System.out.print("Número do quarto: ");
        int numero = input.nextInt();

        for (int i = 0; i < totalQuartos; i++) {
            Quarto q = quartos[i];
            if (q.numero == numero) {
                System.out.println();
                System.out.println("=== DETALHES DO QUARTO ===");
                System.out.println("ID: " + q.id);
                System.out.println("Número: " + q.numero);
                System.out.println("Capacidade: " + q.capacidade);
                System.out.println("Preço por noite: " + q.precoNoite + " €");
                System.out.println("Ocupado: " + (q.estaOcupado ? "Sim" : "Não"));
                return;
            }
        }

        System.out.println("Quarto não encontrado.");
        System.out.println();
    } // FIM DETALHES QUARTO



    // LISTAR HOSPEDES
    public static void listarHospedes() {

        if (totalHospedes == 0) {
            System.out.println("Não existem hóspedes registados.");
            return;
        }
        System.out.println();
        System.out.println("=== LISTA DE HÓSPEDES ===");

        for (int i = 0; i < totalHospedes; i++) {
            Hospede h = hospedes[i];
            System.out.println(
                    "ID: " + h.id +
                            " | Nome: " + h.nome +
                            " | Documento: " + h.documento
            );
        }
    } // FIM LISTAR HOSPEDES

    // ADD HOSPEDES
    public static void adicionarHospede(Scanner input) {

        if (totalHospedes >= hospedes.length) {
            System.out.println("Limite de hóspedes atingido.");
            return;
        }

        System.out.print("Nome do hóspede: ");
        String nome = input.nextLine();

        if (nome.isEmpty()) {
            System.out.println("Nome inválido.");
            return;
        }

        System.out.print("Documento: ");
        String documento = input.nextLine();

        if (documento.isEmpty()) {
            System.out.println("Documento inválido.");
            System.out.println();
            return;
        }

        // verificar duplicação de documento
        for (int i = 0; i < totalHospedes; i++) {
            if (hospedes[i].documento.equalsIgnoreCase(documento)) {
                System.out.println("Já existe um hóspede com esse documento.");
                System.out.println();
                return;
            }
        }

        Hospede h = new Hospede();
        h.id = totalHospedes + 1; // ID autoincremental
        h.nome = nome;
        h.documento = documento;

        hospedes[totalHospedes] = h;
        totalHospedes++;

        System.out.println("Hóspede adicionado com sucesso.");
        System.out.println();
    }  // FIM ADD HOSPEDES

    // PROCURAR HOSPEDE
    public static void procurarHospede(Scanner input) {

        System.out.print("Documento a procurar: ");
        String doc = input.nextLine();

        for (int i = 0; i < totalHospedes; i++) {
            Hospede h = hospedes[i];
            if (h.documento.equalsIgnoreCase(doc)) {
                System.out.println("Hóspede encontrado:");
                System.out.println("ID: " + h.id);
                System.out.println("Nome: " + h.nome);
                System.out.println("Documento: " + h.documento);
                return;
            }
        }

        System.out.println("Nenhum hóspede encontrado com esse documento.");
        System.out.println();
    } // FIM PROCURAR HOSPEDE

    // EDITAR HOSPEDES
    public static void editarHospede(Scanner input) {

        System.out.print("Documento do hóspede a editar: ");
        String doc = input.nextLine();

        for (int i = 0; i < totalHospedes; i++) {
            Hospede h = hospedes[i];

            if (h.documento.equalsIgnoreCase(doc)) {

                System.out.print("Novo nome: ");
                String novoNome = input.nextLine();

                if (!novoNome.isEmpty()) {
                    h.nome = novoNome;
                }

                System.out.print("Novo documento: ");
                String novoDoc = input.nextLine();

                if (!novoDoc.isEmpty()) {
                    // verificar duplicação
                    for (int j = 0; j < totalHospedes; j++) {
                        if (j != i && hospedes[j].documento.equalsIgnoreCase(novoDoc)) {
                            System.out.println("Esse documento já está a ser utilizado por outro hóspede.");
                            System.out.println();
                            return;
                        }
                    }
                    h.documento = novoDoc;
                }

                System.out.println("Hóspede atualizado com sucesso.");
                System.out.println();
                return;
            }
        }

        System.out.println("Hóspede não encontrado.");
        System.out.println();
    } // FIM EDITAR HOSPEDES









    // CRIAR RESERVAS
    public static void criarReserva(Scanner input) {

        if (totalHospedes == 0) { // verificar a existencia de hospedes
            System.out.println("Não existem hóspedes registados.");
            System.out.println();
            return;
        }

        if (totalReservas >= reservas.length) { // testar o limite do vetor reservas (1000)
            System.out.println("Limite de reservas atingido.");
            System.out.println();
            return;
        }

        System.out.print("ID do hóspede: "); // pedir o ID do hospede
        int idHospede = input.nextInt();
        input.nextLine();

        Hospede hospede = null;
        for (int i = 0; i < totalHospedes; i++) { // procurar o hospede pelo ID
            if (hospedes[i].id == idHospede) {
                hospede = hospedes[i];
                break;
            }
        }

        if (hospede == null) {
            System.out.println("Hóspede não encontrado.");
            System.out.println();
            return;
        }

        System.out.print("Número de hóspedes: ");  // pedir o numero de hospedes para a reserva
        int numHospedes = input.nextInt();
        input.nextLine();

        if (numHospedes < 1) { // se é introduzido pelo menos 1 hóspede
            System.out.println("Número de hóspedes inválido.");
            System.out.println();
            return;
        }

        // ===== DATAS =====
        System.out.print("Data início (YYYY-MM-DD): "); // pedir data de inicio
        String dataInicio = input.nextLine();

        System.out.print("Data fim (YYYY-MM-DD): "); // pedir data de fim
        String dataFim = input.nextLine();

        if (!dataFormatoValido(dataInicio) || !dataFormatoValido(dataFim)) { // retorna mensagem de erro caso formato das datas esteja invalido
            System.out.println("Formato de data inválido.");
            System.out.println();
            return;
        }

        if (!datasOrdemValida(dataInicio, dataFim)) { // retorna mensagem caso formato das datas nao estiver na ordem correta
            System.out.println("Data início deve ser anterior ou igual à data fim.");
            System.out.println();
            return;
        }

        // ===== ESCOLHER QUARTO SEM CONFLITOS =====
        Quarto quartoEscolhido = null;

        for (int i = 0; i < totalQuartos; i++) {
            Quarto q = quartos[i];

            if (q.capacidade >= numHospedes) { // testar se o quarto tem capacidade sufiente para o nº de hospedes escolhido

                boolean conflito = false;

                for (int j = 0; j < totalReservas; j++) { // percorrer as reservas existentes
                    Reserva r = reservas[j];

                    if (r.ativa && r.idQuarto == q.id) { // verificar conflito  das datas
                        if (datasConflitam(dataInicio, dataFim, r.dataInicio, r.dataFim)) {
                            conflito = true;
                            break;
                        }
                    }
                }

                if (!conflito) { // atribuir o quarto caso nao haja conflito
                    quartoEscolhido = q;
                    break;
                }
            }
        }

        // testar se existem quartos disponiveis
        if (quartoEscolhido == null) {

            System.out.println("Não existem quartos disponíveis para essas datas.");
            System.out.println();
            return;
        }

        // ===== CÁLCULO DE NOITES E CUSTO =====
        int noites = calcularNoites(dataInicio, dataFim);

        if (noites <= 0) { // pelo menos 1 noite para calcular
            System.out.println("Datas inválidas para calcular as noites.");
            return;
        }

        double total = noites * quartoEscolhido.precoNoite; // devolve valor total da estadia

        System.out.println("Total de noites: " + noites);
        System.out.println("Preço por noite: " + quartoEscolhido.precoNoite + " €");
        System.out.println("Total da estadia: " + total + " €");

        // ===== CRIAR RESERVA =====
        Reserva r = new Reserva();
        r.id = totalReservas + 1;
        r.idHospede = hospede.id;
        r.idQuarto = quartoEscolhido.id;
        r.numeroHospedes = numHospedes;
        r.dataInicio = dataInicio;
        r.dataFim = dataFim;
        r.custoTotal = total;
        r.ativa = true;

        reservas[totalReservas] = r;
        totalReservas++;

        atualizarOcupacaoQuartos(); // atualizar ocupaçao dos quartos

        System.out.println("Reserva criada com sucesso!");
        System.out.println("Quarto atribuído: " + quartoEscolhido.numero);
        System.out.println();
    } // FIM CRIAR RESERVAS

    // LISTAR AS RESERVAS
    public static void listarReservas() {

        if (totalReservas == 0) { // testar se existem reservas
            System.out.println("Não existem reservas.");
            System.out.println();
            return;
        }
        System.out.println();
        System.out.println("=== LISTA DE RESERVAS ===");

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];
            if (r.ativa) {
                System.out.println(
                        "Reserva ID: " + r.id +
                                " | Quarto ID: " + r.idQuarto +
                                " | Hóspede ID: " + r.idHospede +
                                " | Hóspedes: " + r.numeroHospedes +
                                " | " + r.dataInicio + " até " + r.dataFim +
                                " | Total de noites: " + calcularNoites(r.dataInicio, r.dataFim) +
                                " | Custo total: " + r.custoTotal + " €"

                );
            }
        }
    } // FIM LISTA DE RESERVAS

    // LISTAR RESERVAS/QUARTO
    public static void listarReservasPorQuarto(Scanner input) {

        System.out.print("Número do quarto: "); // pedir numero do quarto a procurar
        int numeroQuarto = input.nextInt();
        input.nextLine();

        Quarto quarto = null;

        // procurar o quarto
        for (int i = 0; i < totalQuartos; i++) {
            if (quartos[i].numero == numeroQuarto) {
                quarto = quartos[i];
                break;
            }
        }

        if (quarto == null) {
            System.out.println("Quarto não encontrado.");
            System.out.println();
            return;
        }
        boolean encontrou = false;

        System.out.println();
        System.out.println("=== RESERVAS DO QUARTO " + quarto.numero + " ===");

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];

            if (r.ativa && r.idQuarto == quarto.id) {
                System.out.println(
                        "Reserva ID: " + r.id +
                                " | Hóspede ID: " + r.idHospede +
                                " | Hóspedes: " + r.numeroHospedes +
                                " | " + r.dataInicio + " até " + r.dataFim +
                                " | Custo: " + r.custoTotal + " €"
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não existem reservas para este quarto.");
        }

        System.out.println();
    } // FIM RESERVAS/QUARTO

    // LISTAR RESERVAS/HOSPEDE
    public static void listarReservasPorHospede(Scanner input) {

        System.out.print("ID do hóspede: "); // pedir ID do hospede a procurar
        int idHospede = input.nextInt();
        input.nextLine();

        Hospede hospede = null;

        // procurar hóspede
        for (int i = 0; i < totalHospedes; i++) {
            if (hospedes[i].id == idHospede) {
                hospede = hospedes[i];
                break;
            }
        }

        if (hospede == null) {
            System.out.println("Hóspede não encontrado.");
            System.out.println();
            return;
        }

        boolean encontrou = false;

        System.out.println();
        System.out.println("=== RESERVAS DO HÓSPEDE: " + hospede.nome + " ===");

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];

            if (r.ativa && r.idHospede == hospede.id) {
                System.out.println(
                        "Reserva ID: " + r.id +
                                " | Quarto ID: " + r.idQuarto +
                                " | Hóspedes: " + r.numeroHospedes +
                                " | " + r.dataInicio + " até " + r.dataFim +
                                " | Custo: " + r.custoTotal + " €"
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Este hóspede não tem reservas ativas.");
        }

        System.out.println();
    } // FIM RESERVAS/HOSPEDE

    // EDITAR RESERVAS
    public static void editarReserva(Scanner input) {

        System.out.print("ID da reserva a editar: "); // pedir ID da reserva a procurar
        int idReserva = input.nextInt();
        input.nextLine();

        Reserva reserva = null;

        // procurar reserva
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].id == idReserva && reservas[i].ativa) {
                reserva = reservas[i];
                break;
            }
        }

        if (reserva == null) {
            System.out.println("Reserva não encontrada ou não ativa.");
            System.out.println();
            return;
        }

        // procurar quarto atual da reserva
        Quarto quartoAtual = null;
        for (int i = 0; i < totalQuartos; i++) {
            if (quartos[i].id == reserva.idQuarto) {
                quartoAtual = quartos[i];
                break;
            }
        }

        if (quartoAtual == null) {
            System.out.println("Erro: quarto associado não encontrado.");
            return;
        }

        // mostrar dados atuais da reserva
        System.out.println();
        System.out.println("=== DADOS ATUAIS DA RESERVA ===");
        System.out.println("Quarto: " + quartoAtual.numero);
        System.out.println("Hóspedes: " + reserva.numeroHospedes);
        System.out.println("Datas: " + reserva.dataInicio + " até " + reserva.dataFim);
        System.out.println();

        // ===== NOVOS DADOS ===== (a pedir ao utilizador)
        System.out.print("Novo número de hóspedes (0 para manter): ");
        int novoNumHospedes = input.nextInt();
        input.nextLine();

        System.out.print("Nova data início (ENTER para manter): ");
        String novaDataInicio = input.nextLine();

        System.out.print("Nova data fim (ENTER para manter): ");
        String novaDataFim = input.nextLine();

        // manter valores antigos
        if (novoNumHospedes == 0) {
            novoNumHospedes = reserva.numeroHospedes;
        }

        if (novaDataInicio.isEmpty()) {
            novaDataInicio = reserva.dataInicio;
        }

        if (novaDataFim.isEmpty()) {
            novaDataFim = reserva.dataFim;
        }

        // ===== VALIDAÇÕES =====

        if (novoNumHospedes < 1) {
            System.out.println("Número de hóspedes inválido.");
            return;
        }

        if (!dataFormatoValido(novaDataInicio) || !dataFormatoValido(novaDataFim)) {
            System.out.println("Formato de data inválido.");
            return;
        }

        if (!datasOrdemValida(novaDataInicio, novaDataFim)) {
            System.out.println("Data início deve ser anterior ou igual à data fim.");
            return;
        }

        // ===== VERIFICAR SE O QUARTO ATUAL AINDA SERVE =====
        Quarto quartoFinal = quartoAtual;

        if (novoNumHospedes > quartoAtual.capacidade) {

            // procurar novo quarto adequado
            Quarto novoQuarto = null;

            for (int i = 0; i < totalQuartos; i++) {
                Quarto q = quartos[i];

                if (q.capacidade >= novoNumHospedes) { // testar se a novo numero de hospedes excede a capacidade do quarto

                    boolean conflito = false;

                    for (int j = 0; j < totalReservas; j++) {
                        Reserva outra = reservas[j];

                        // verificar conflito de datas com outras reservas ativas do quarto
                        if (outra.ativa && outra.idQuarto == q.id && outra.id != reserva.id
                                && datasConflitam(novaDataInicio, novaDataFim, outra.dataInicio, outra.dataFim)) {
                            conflito = true;
                            break;
                        }
                    }

                    if (!conflito) {
                        novoQuarto = q;
                        break;
                    }
                }
            }

            if (novoQuarto == null) {
                System.out.println("Não existe quarto disponível para esse número de hóspedes.");
                return;
            }

            quartoFinal = novoQuarto;
            reserva.idQuarto = novoQuarto.id;
        }

        // ===== VERIFICAR CONFLITOS NO NOVO QUARTO ATRIBUIDO =====
        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];

            if (r.ativa &&
                    r.idQuarto == reserva.idQuarto &&
                    r.id != reserva.id) {

                if (datasConflitam(novaDataInicio, novaDataFim,
                        r.dataInicio, r.dataFim)) {
                    System.out.println("Erro: conflito de datas com outra reserva.");
                    return;
                }
            }
        }

        // ===== ATUALIZAR RESERVA =====
        reserva.numeroHospedes = novoNumHospedes;
        reserva.dataInicio = novaDataInicio;
        reserva.dataFim = novaDataFim;

        int noites = calcularNoites(novaDataInicio, novaDataFim);
        reserva.custoTotal = noites * quartoFinal.precoNoite;

        atualizarOcupacaoQuartos();

        System.out.println("Reserva atualizada com sucesso!");
        System.out.println("=== NOVOS DADOS DA RESERVA ===");
        System.out.println("Reserva ID: " + reserva.id);
        System.out.println("Quarto: " + quartoFinal.numero);
        System.out.println("Número de hóspedes: " + reserva.numeroHospedes);
        System.out.println("Datas: " + reserva.dataInicio + " até " + reserva.dataFim);
        System.out.println("Total de noites: " + noites);
        System.out.println("Custo total: " + reserva.custoTotal + " €");
        System.out.println();
    } // FIM EDITAR RESERVAS

    // CANCELAR A RESERVA
    public static void cancelarReserva(Scanner input) {

        System.out.print("ID da reserva a cancelar: "); // procurar o ID da reserva
        int id = input.nextInt();

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];

            if (r.id == id && r.ativa) { // mudar o estado de ativa para nao ativa
                r.ativa = false;

                System.out.println("Reserva cancelada com sucesso.");
                System.out.println();
                return;
            }
        }
        System.out.println("Reserva não encontrada.");
        System.out.println();
        atualizarOcupacaoQuartos(); // atualizar ocupaçao dos quartos

    } // FIM CANCELAR RESERVA






    // ----------- VALIDAÇÃO DAS DATAS  -----------
    public static boolean dataFormatoValido(String data) {
        if (data.length() != 10) { // VERIFICAR TAMANHO DA STRING DATA
            return false;
        }

        if (data.charAt(4) != '-' || data.charAt(7) != '-') { // VERIFICAR OS HIFENS NA POSIÇAO CORRETA DO FORMATO YYYY-MM-DD
            return false;
        }

        for (int i = 0; i < data.length(); i++) {
            if (i == 4 || i == 7) continue;
            if (!Character.isDigit(data.charAt(i))) { // CONFIRMAR SE SAO DIGITOS E NÃO CARACTERES
                return false;
            }
        }

        return true;
    } // FIM VALIDAÇAO DA DATA

    // CONFIRMAR A DATA DE INICIO / DATA DE FIM DA RESERVA
    public static boolean datasOrdemValida(String inicio, String fim) {
        return inicio.compareTo(fim) <= 0;
    } // FIM CONF ORDEM DATA

    // CONFIRMAR CONFLITO DAS DATAS
    public static boolean datasConflitam(
            String inicio1, String fim1,
            String inicio2, String fim2) {

        return !(fim1.compareTo(inicio2) < 0 || inicio1.compareTo(fim2) > 0);
    } // FIM CONF CONFLITO DATAS

    // BUSCAR A DATA DE HOJE
    public static String dataAtual() {
        return java.time.LocalDate.now().toString(); // obter a data atual
    }

    // COMPARAÇAO DAS DATAS
    public static boolean dataDentroIntervalo(String data, String inicio, String fim) {
        return data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0; // verifica se uma data pertence ao período de uma reserva ativa,  para determinar se o quarto está ocupado.
    } // FIM COMPARAÇAO DATAS


    // ----------- CALCULAR AS NOITES COM BASE NAS DATAS ESCOLHIDAS PELO HOSPEDE  -----------
    public static int[] separarData(String data) {
        String[] partes = data.split("-"); // separar a data em 3 partes dividindo com - (ano-mes-dia)
        int ano = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new int[]{ano, mes, dia};
    }

    // validar a escolha dos meses
    public static int diasNoMes(int mes) { // devolve para cada mes os dias totais correspondentes segundo a data escolhida
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return 28;
            default:
                return 0;
        }
    }

    // converter a data para dias
    public static int dataParaDias(String data) {

        int[] d = separarData(data); // converter em dias para depois calcular o total de noites da reserva
        int ano = d[0];
        int mes = d[1];
        int dia = d[2];

        int total = ano * 365;

        for (int i = 1; i < mes; i++) {
            total += diasNoMes(i);
        }
        total += dia;
        return total;
    }

    // calculo das noites da reserva
    public static int calcularNoites(String dataInicio, String dataFim) {
        return dataParaDias(dataFim) - dataParaDias(dataInicio); // devolve o total de noites
    }










    // INICIO ATUALIZAR OCUPAÇAO QUARTOS
    public static void atualizarOcupacaoQuartos() {

        // iniciar os quartos como livres
        for (int i = 0; i < totalQuartos; i++) {
            quartos[i].estaOcupado = false;
        }

        String hoje = dataAtual(); // criar data atual

        // correr todas as reservas
        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];

            // verificar se o quarto esta ocupado HOJE
            if (r.ativa && dataDentroIntervalo(hoje, r.dataInicio, r.dataFim)) {

                // encontrar o quarto dessa reserva
                for (int j = 0; j < totalQuartos; j++) {
                    if (quartos[j].id == r.idQuarto) {
                        quartos[j].estaOcupado = true;
                        break;
                    }
                }
            }
        }
    } // FIM ATUALIZAR QUARTOS

    // ----------- FICHEIROS CSV -----------

    // QUARTOS CSV
    public static void carregarQuartosCSV() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("quartos.csv")); // ler o ficheiro quartos.csv
            String linha;

            br.readLine(); // ignora cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(","); // reconhecer a divisao das caracteristicas de Quarto

                Quarto q = new Quarto();
                q.id = Integer.parseInt(partes[0]);
                q.numero = Integer.parseInt(partes[1]);
                q.capacidade = Integer.parseInt(partes[2]);
                q.precoNoite = Double.parseDouble(partes[3]);
                q.estaOcupado = false;

                quartos[totalQuartos] = q; // meter notas
                totalQuartos++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro quartos.csv");
            System.out.println();
        }
    } // FIM QUARTOS CSV

    // HOSPEDES CSV
    public static void carregarHospedesCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("hospedes.csv"));
            String linha;

            br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(",");

                Hospede h = new Hospede();
                h.id = Integer.parseInt(partes[0]);
                h.nome = partes[1];
                h.documento = partes[2];

                hospedes[totalHospedes] = h;
                totalHospedes++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro hospedes.csv");
            System.out.println();
        }
    } // FIM HOSPEDES CSV

    // RESERVAS CSV
    public static void carregarReservasCSV() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("reservas.csv"));
            String linha;

            br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(",");

                Reserva r = new Reserva();
                r.id = Integer.parseInt(partes[0]);
                r.idQuarto = Integer.parseInt(partes[1]);
                r.idHospede = Integer.parseInt(partes[2]);
                r.numeroHospedes = Integer.parseInt(partes[3]);
                r.dataInicio = partes[4];
                r.dataFim = partes[5];
                r.custoTotal = Double.parseDouble(partes[6]);
                r.ativa = Boolean.parseBoolean(partes[7]);

                reservas[totalReservas] = r;
                totalReservas++;
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro reservas.csv");
            System.out.println();
        }
    } // FIM RESERVAS CSV

    // GUARDAR HOSPEDES CSV
    public static void guardarHospedesCSV() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("hospedes.csv"));

            // cabeçalho
            bw.write("id,nome,documento\n");

            // escrever hóspedes
            for (int i = 0; i < totalHospedes; i++) {
                Hospede h = hospedes[i];
                bw.write(
                        h.id + "," +
                            h.nome + "," +
                            h.documento + "\n"
                );
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Erro ao guardar o ficheiro hospedes.csv");
        }
    } // FIM GUARDAR HOSPEDES

    // GUARDAR RESERVAS CSV
    public static void guardarReservasCSV() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reservas.csv"));

            // cabeçalho
            bw.write("id,idQuarto,idHospede,numeroHospedes,dataInicio,dataFim,custoTotal,ativa\n");

            // escrever reservas
            for (int i = 0; i < totalReservas; i++) {
                Reserva r = reservas[i];
                bw.write(
                        r.id + "," +
                                r.idQuarto + "," +
                                r.idHospede + "," +
                                r.numeroHospedes + "," +
                                r.dataInicio + "," +
                                r.dataFim + "," +
                                r.custoTotal + "," +
                                r.ativa + "\n"
                );
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Erro ao guardar o ficheiro reservas.csv");
        }
    } // FIM GUARDAR RESERVAS

} // FIM PROGRAMA