package programas;

import utilitarios.util;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
//        Cliente polyana = new Cliente();
//        polyana.setNome("Polyana");
//        polyana.setCPF("456.456.789-56");
//        polyana.setEmail("nome@email.com");
//
//        Conta cc = new ContaCorrente(polyana);
//        Conta pp = new ContaPoupanca(polyana);
//
//        cc.depositar(100);
//        cc.transferir(100,pp);
//
//        cc.imprimirExtrato();
//       pp.imprimirExtrato();
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("------------------Bem vindos a nossa agencia------------------");
        System.out.println("--------------------------------------------------------------");
        System.out.println("----------Selecione uma operação que deseja realizar----------");
        System.out.println("--------------------------------------------------------------");
        System.out.println("|   Opção 1 - Criar conta    |");
        System.out.println("|   Opção 2 - Depositar      |");
        System.out.println("|   Opção 3 - Sacar          |");
        System.out.println("|   Opção 4 - Transferir     |");
        System.out.println("|   Opção 5 - Listar         |");
        System.out.println("|   Opção 6 - Sair           |");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                System.out.println("Obrigado por usar a nossa agencia.");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static String escolherTipoConta() {
        System.out.println("--------------------------------------------------");
        System.out.println("---------Qual tipo de conta deseja criar?---------");
        System.out.println("--------------------------------------------------");
        System.out.println("|   Opção 1 - Conta Corrente   |");
        System.out.println("|   Opção 2 - Poupança.    |");

        int tipo = input.nextInt();

        while (tipo != 1 && tipo != 2) {
            System.out.println("Opção inválida!");
            escolherTipoConta();
        }
        String tConta = "conta";
        switch (tipo) {
            case 1:
                tConta = "cc";
                break;
            case 2:
                tConta = "pp";
                break;
            default:
                System.out.println("Opção inválida!");
                escolherTipoConta();
                break;
        }
        return tConta;
    }

    public static void criarConta() {
        System.out.println("Nome: ");
        String nome = input.next();

        System.out.println("CPF: ");
        String cpf = input.next();

        System.out.println("Email: ");
        String email = input.next();

        Cliente cliente = new Cliente(nome, cpf, email);

        String tipoConta = escolherTipoConta();

        if (tipoConta == "cc") {
            Conta cc = new ContaCorrente(cliente);
            contasBancarias.add(cc);
            System.out.println("Sua conta foi criada com sucesso!");
            cc.imprimirExtrato();
        } else {
            Conta pp = new ContaPoupanca(cliente);
            contasBancarias.add(pp);
            System.out.println("Sua conta foi criada com sucesso!");
            pp.imprimirExtrato();
        }
        operacoes();

    }

    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta c : contasBancarias) {
                if (c.getNumero() == numeroConta) {
                    conta = c;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
            System.out.println("Valor depositado com sucesso!");

            conta.imprimirExtrato();
        } else {
            System.out.println("Conta não encontrada!");
        }
        operacoes();
    }

    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja sacar? ");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
            conta.imprimirIfosComuns();
        } else {
            System.out.println("Conta não encontrada!");
        }
        operacoes();
    }

    public static void transferir() {
        System.out.println("Número da conta do remetente: ");
        int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            System.out.println("Número da conta de destino: ");
            int numeroContaDestinatario = input.nextInt();

            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);
            if (contaDestinatario != null) {
                System.out.println("Valor da transfência: ");
                Double valor = input.nextDouble();
                contaRemetente.transferir(contaDestinatario, valor);
                contaRemetente.imprimirExtrato();
                contaDestinatario.imprimirExtrato();
            } else {
                System.out.println("Conta do remetente não encontrada!");
            }
        } else {
            System.out.println("Conta do destinatário não encontrada!");
        }
        operacoes();
    }

    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                conta.imprimirExtrato();
            }
        } else {
            System.out.println("Não há contas cadastradas!");
        }
        operacoes();
    }
}


   /* public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas)
    this.contas = contas;
    }}*/
