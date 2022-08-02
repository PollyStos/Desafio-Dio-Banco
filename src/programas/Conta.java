package programas;

import utilitarios.util;

public class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;


    protected int agencia;
    protected int numero;
    protected double saldo = 0.0;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar (double valor) {

        if(valor>0&&this.getSaldo()>=valor){
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        }else
            System.out.println("Não foi possível realizar o saque!");
    }

    @Override
    public void depositar(double valor) {
        if(valor>0){
            saldo += valor;
            System.out.println("Seu depósito foi realizado com sucesso!");
        }else
            System.out.println("Não foi possível realizar o depósito!");

    }

    @Override
    public void transferir(double valor, Conta contaDestino) {

    }

    @Override
    public void imprimirExtrato() {

    }

    @Override
    public void transferir(Conta contaDestino, double valor) {

        if(valor>0&&this.getSaldo()>=valor){
            saldo -= valor;
            contaDestino.saldo = contaDestino.getSaldo()+valor;
            System.out.println("Transferencia realizada com sucesso!");
        }else
            System.out.println("Não foi possível realizar a transferência!");
    }

    public int getAgencia() {

        return agencia;
    }

    public int getNumero() {

        return numero;
    }

    public double getSaldo() {

        return saldo;
    }

    public void imprimirIfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("CPF: %s", this.cliente.getCPF()));
        System.out.println(String.format("Email: %s", this.cliente.getEmail()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: "+ util.doubleToString(this.getSaldo())));

}
}
