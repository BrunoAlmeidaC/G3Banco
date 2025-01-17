package br.com.cdb.bancodigital.view;

import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.dao.ContaDAO;
import br.com.cdb.bancodigital.entity.*;
import br.com.cdb.bancodigital.entity.Cliente.TipoCliente;
import br.com.cdb.bancodigital.service.ClienteService;
import br.com.cdb.bancodigital.service.ContaService;

import java.util.Scanner;

public class Main {

    private static ClienteService clienteService;
    private static ContaService contaService;
    private static Scanner scanner;

    public static void main(String[] args) {

        clienteService = new ClienteService(new ClienteDAO());
        contaService = new ContaService(new ContaDAO());


        scanner = new Scanner(System.in);

        exibirMenu();
    }

    private static void exibirMenu() {

        int opcao;
        int opcao2;

        do {
            System.out.println();
            System.out.println("===== BEM-VINDO AO CDBANK =====");
            System.out.println();
            System.out.println("DIGITE A OPÇÃO DESEJADA:");
            System.out.println();
            System.out.println("1 - Clientes");
            System.out.println("2 - Operações");
            System.out.println("3 - Exibir");
            System.out.println("4 - Cartões");
            System.out.println("5 - Seguros");
            System.out.println("6 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("1 - Cadastro de clientes");
                    System.out.println("2 - Lista de clientes");
                    System.out.println("3 - Cadastro de conta corrente");
                    System.out.println("4 - Cadastro de conta poupança");
                    System.out.println("5 - Voltar");
                    opcao2 = scanner.nextInt();
                    switch (opcao2) {
                        case 1:
                            cadastrarCliente();
                            break;
                        case 2:
                            listarClientes();
                            break;
                        case 3:
                            criarContaCorrente();
                            break;
                        case 4:
                            criarContaPoupanca();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");

                            break;
                    }
                    break;
                case 2:
                    System.out.println("1 - Depósito");
                    System.out.println("2 - Transferencia");
                    System.out.println("3 - Saque");
                    System.out.println("4 - Voltar");
                    opcao2 = scanner.nextInt();
                    switch (opcao2) {
                        case 1:
                            depositar();
                            break;
                        case 2:
                            transferir();
                            break;
                        case 3:
                            sacar();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1 - Saldo da conta");
                    System.out.println("2 - Extrato da conta");
                    System.out.println("3 - Dados da conta");
                    System.out.println("4 - Voltar");
                    opcao2 = scanner.nextInt();
                    switch (opcao2) {
                        case 1:
                            exibirSaldo();
                            break;
                        case 2:
                            exibirExtrato();
                            break;
                        case 3:
                            exibirDadosConta();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1 - Emitir cartão de crédito");
                    System.out.println("2 - Emitir cartão de débito");
                    System.out.println("3 - Voltar");
                    opcao2 = scanner.nextInt();
                    switch (opcao2) {
                        case 1:
                            emitirCartaoCredito();
                            break;
                        case 2:
                            emitirCartaoDebito();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1 - Contratar Seguro Viagem");
                    System.out.println("2 - Emissão de Apólice de Seguro Viagem");
                    System.out.println("3 - Emissão de Apólice de Seguro Fraude");
                    System.out.println("4 - Voltar");
                    opcao2 = scanner.nextInt();
                    switch (opcao2) {
                        case 1:
                            contratarSeguroViagem();
                            break;
                        case 2:
                            exibirApoliceViagem();
                            break;
                        case 3:
                            exibirApoliceFraude();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Sessão encerrada. O CDBANK agradece sua visita.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente a opção desejada:");
            }
        } while (opcao != 6);
    }

    private static void cadastrarCliente() {
        scanner.nextLine();
        System.out.println("Você selecionou cadastro de cliente.");
        System.out.println("Por favor, digite o CPF (apenas os números):");
        String cpf = scanner.nextLine();
        System.out.println("Por favor, digite o nome completo:");
        String nome = scanner.nextLine();
        System.out.println("Por favor, digite a data de nascimento (dd/mm/aaaa):");
        String dataNascimento = scanner.nextLine();
        System.out.println("Por favor, digite o endereco completo (rua, número, complemento, cidade, estado, cep:");
        String endereco = scanner.nextLine();
        System.out.println("Por favor, informe a categoria do cliente: COMUM, SUPER, PREMIUM:");
        String tipoString = scanner.nextLine();
        TipoCliente tipo = TipoCliente.valueOf(tipoString.toUpperCase());


        clienteService.cadastrarCliente(cpf, nome, dataNascimento, endereco, tipo);
        System.out.println();
    }

    private static void listarClientes() {
        System.out.println("Relação de clientes CDBANK:");
        for (Cliente cliente : clienteService.getClientes()) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Categoria: " + cliente.getTipo());
        }
        System.out.println();
    }

    private static void criarContaCorrente() {
        scanner.nextLine();
        System.out.println("Você selecionou cadastro de conta corrente.");
        System.out.println("Por favor, digite o CPF do titular da conta (apenas números).");
        String cpf = scanner.nextLine();
        Cliente titular = clienteService.encontrarClientePorCPF(cpf);
        if (titular == null) {
            System.out.println("Cliente não está cadastrado.");
            return;
        }
        System.out.println("Por favor, informe o saldo inicial da conta.");
        double saldoInicial = scanner.nextDouble();
        ContaCorrente contaCorrente = contaService.criarContaCorrente(cpf, titular, saldoInicial);
        System.out.println("Conta corrente criada com sucesso!");
        System.out.println("Número da conta: " + contaCorrente.getNumeroConta());
    }

    private static void criarContaPoupanca() {
        scanner.nextLine();
        System.out.println("Você selecionou cadastro de conta poupança.");
        System.out.println("Por favor, digite o CPF do titular da conta (apenas números).");
        String cpf = scanner.nextLine();
        Cliente titular = clienteService.encontrarClientePorCPF(cpf);
        if (titular == null) {
            System.out.println("Cliente não está cadastrado.");
            return;
        }
        System.out.println("Por favor, informe o saldo inicial da conta.");
        double saldoInicial = scanner.nextDouble();
        ContaPoupanca contaPoupanca = contaService.criarContaPoupanca(cpf, titular, saldoInicial);
        System.out.println("Conta corrente criada com sucesso!");
        System.out.println("Número da conta: " + contaPoupanca.getNumeroConta());
    }

    private static void depositar() {
        scanner.nextLine();
        System.out.println("Você selecionou depositar.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();
        System.out.println("Por favor, digite o valor a ser depositado:");
        double valor = scanner.nextDouble();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta != null) {
            contaService.depositar(conta, valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void transferir() {
        scanner.nextLine();
        System.out.println("Você selecionou transferir.");
        System.out.println("Por favor, digite o número da conta de origem:");
        String origemNumeroConta = scanner.nextLine();
        System.out.println("Por favor, digite o número da conta de destino:");
        String destinoNumeroConta = scanner.nextLine();
        System.out.println("Por favor, digite o valor a ser transferido:");
        double valor = scanner.nextDouble();

        Conta origem = contaService.buscarContaPorNumero(origemNumeroConta);
        Conta destino = contaService.buscarContaPorNumero(destinoNumeroConta);

        if (origem == null || destino == null) {
            System.out.println("Uma das contas informadas não foi encontrada.");
            return;
        }
        contaService.transferir(origem, destino, valor);
    }

    private static void sacar() {
        scanner.nextLine();
        System.out.println("Você selecionou sacar.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();
        System.out.println("Por favor, digite o valor a ser sacado:");
        double valor = scanner.nextDouble();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }
        contaService.sacar(conta, valor);
    }

    private static void exibirSaldo() {
        scanner.nextLine();
        System.out.println("Você selecionou exibir saldo.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }
        contaService.exibirSaldo(conta);
    }

    private static void exibirExtrato() {
        scanner.nextLine();
        System.out.println("Você selecionou exibir extrato.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }
        contaService.exibirExtrato(conta);
    }

    private static void exibirDadosConta() {
        scanner.nextLine();
        System.out.println("Você selecionou exibir dados da conta.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }
        contaService.exibiDadosConta(conta);
    }

    private static void emitirCartaoCredito() {
        scanner.nextLine();
        System.out.println("Você selecionou emissão de cartão de crédito.");
        System.out.println("Por favor, digite o número da conta");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (conta.getCartaoCredito() != null) {
            System.out.println("Essa conta já possui um cartão de crédito vinculado.");
            return;
        }

        System.out.println("Por favor, digite a senha do cartão com 6 números.");
        String senhaCredito = scanner.nextLine();

        CartaoCredito cartaoCredito = contaService.emitirCartaoCredito(conta);
        if (cartaoCredito != null) {
            cartaoCredito.setSenha(senhaCredito);
            System.out.println("Cartão de crédito emitido com sucesso.");
            System.out.println("Número do cartão: " + cartaoCredito.getNumero());
            System.out.println("A sua senha é: " + cartaoCredito.getSenha());
        }
    }

    private static void emitirCartaoDebito() {
        scanner.nextLine();
        System.out.println("Você selecionou emissão de cartão de débito.");
        System.out.println("Por favor, digite o número da conta");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (conta.getCartaoDebito() != null) {
            System.out.println("Essa conta já possui um cartão de débito vinculado.");
            return;
        }

        System.out.println("Por favor, digite a senha do cartão com 6 números.");
        String senhaDebito = scanner.nextLine();

        CartaoDebito cartaoDebito = contaService.emitirCartaoDebito(conta, 0);
        if (cartaoDebito != null) {
            cartaoDebito.setSenha(senhaDebito);
            System.out.println("Cartão de débito emitido com sucesso.");
            System.out.println("Número do cartão: " + cartaoDebito.getNumero());
            System.out.println("A sua senha é: " + cartaoDebito.getSenha());
        }
    }

    private static void exibirApoliceViagem() {
        scanner.nextLine();
        System.out.println("Você selecionou exibir detalhes da apólice do seguro de viagem.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }

        Cliente titular = conta.getTitular();
        CartaoCredito cartaoCredito = conta.getCartaoCredito();

        if (cartaoCredito == null) {
            System.out.println("Essa conta não possui um cartão de crédito vinculado.");
            return;
        }

        SeguroViagem seguroViagem = new SeguroViagem();
        String detalhesApoliceViagem = seguroViagem.gerarApolice(titular, cartaoCredito);
        System.out.println(detalhesApoliceViagem);

        System.out.println("\nPressione Enter para retornar ao Menu Principal.");
        scanner.nextLine();
    }

    private static void exibirApoliceFraude() {
        scanner.nextLine();
        System.out.println("Você selecionou exibir detalhes da apólice do seguro contra fraude.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }

        Cliente titular = conta.getTitular();
        CartaoCredito cartaoCredito = conta.getCartaoCredito();

        if (cartaoCredito == null) {
            System.out.println("Essa conta não possui um cartão de crédito vinculado.");
            return;
        }

        SeguroFraude seguroFraude = new SeguroFraude();
        String detalhesApoliceFraude = seguroFraude.gerarApolice(titular, cartaoCredito);
        System.out.println(detalhesApoliceFraude);

        System.out.println("\nPressione Enter para retornar ao Menu Principal.");
        scanner.nextLine();
    }

    private static void contratarSeguroViagem() {
        scanner.nextLine();
        System.out.println("Você selecionou contratar seguro viagem.");
        System.out.println("Por favor, digite o número da conta:");
        String numeroConta = scanner.nextLine();

        Conta conta = contaService.buscarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("A conta informada não foi encontrada.");
            return;
        }

        Cliente cliente = conta.getTitular();

        if (cliente.getTipo() == TipoCliente.PREMIUM) {
            System.out.println("Seguro viagem já incluso para clientes Premium.");
            return;
        }

        if (cliente.isSeguroViagemContratado()) {
            System.out.println("O seguro já foi contratado para esse cliente.");
            return;
        }

        double custoSeguro = 50.0;

        if (!cobrarCustoSeguro(cliente, custoSeguro)) {
            System.out.println("Saldo insuficiente para a contratação desse seguro");
            return;
        }

        cliente.setSeguroViagemContratado(true);

        System.out.println("Seguro viagem contratado com sucesso.");
    }

    private static boolean cobrarCustoSeguro(Cliente cliente, double custoSeguro) {

        Conta conta = cliente.getConta();

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return false;
        }

        double saldo = conta.getSaldo();

        if (saldo >= custoSeguro) {
            conta.debitar(custoSeguro);
            return true;
        } else {
            System.out.println("Saldo insuficiente para contratação do seguro");
            return false;
        }
    }

}

