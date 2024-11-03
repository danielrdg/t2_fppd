public class Conta {
    private int id;
    private String titular;
    private double saldo;

    public Conta(int id, String titular, double saldoInicial) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public String getTitular() {
        return titular;
    }
}
