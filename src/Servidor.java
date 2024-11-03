import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Servidor extends UnicastRemoteObject implements ServidorInterface {
    private Map<Integer, Conta> contas = new HashMap<>();
    private int contadorContas = 0;

    public Servidor() throws RemoteException {
        super();
    }

    public synchronized int criarConta(String titular) {
        int contaId = ++contadorContas;
        contas.put(contaId, new Conta(titular, 0.0));
        return contaId;
    }

    public synchronized void fecharConta(int contaId) throws RemoteException {
        contas.remove(contaId);
    }

    public synchronized double consultarSaldo(int contaId) throws RemoteException {
        return contas.getOrDefault(contaId, new Conta("", 0.0)).getSaldo();
    }

    public synchronized void depositar(int contaId, double valor) throws RemoteException {
        Conta conta = contas.get(contaId);

        if (conta == null) {
            System.out.println("Conta " + contaId + " não encontrada.");
            return;
        }

        conta.depositar(valor);
    }

    public synchronized boolean sacar(int contaId, double valor) throws RemoteException {
        Conta conta = contas.get(contaId);

        if (conta == null) {
            System.out.println("Conta " + contaId + " não encontrada.");
            return false;
        }

        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente para realizar o saque da conta " + contaId);
            return false;
        }

        conta.sacar(valor);
        System.out.println("Saque de " + valor + " realizado da conta " + contaId);
        return true;
    }

}