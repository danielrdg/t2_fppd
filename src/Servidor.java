import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Servidor extends UnicastRemoteObject implements ServidorInterface {
    private Map<Integer, Conta> contas = new HashMap<>();
    private Map<String, Boolean> transacoesAplicadas = new HashMap<>(); // Registra transações para idempotência
    private int contadorContas = 0;

    public Servidor() throws RemoteException {
        super();
    }

    public synchronized int criarConta(String titular) throws RemoteException {
        // Verifica se já existe uma conta com o mesmo titular para evitar duplicações
        for (Conta conta : contas.values()) {
            if (conta.getTitular().equals(titular)) {
                System.out.println("Conta já existe para " + titular);
                return conta.getId();
            }
        }
    
        int contaId = ++contadorContas;
        contas.put(contaId, new Conta(contaId, titular, 0.0)); // Passa o ID ao criar a conta
        System.out.println("Conta criada com ID: " + contaId);
        return contaId;
    }

    public synchronized void fecharConta(int contaId) throws RemoteException {
        contas.remove(contaId);
    }

    public synchronized double consultarSaldo(int contaId) throws RemoteException {
        Conta conta = contas.get(contaId);
        if (conta == null) {
            System.out.println("Conta " + contaId + " nao encontrada.");
            return -1.0;
        }
        return conta.getSaldo();
    }
    

    public synchronized void depositar(int contaId, double valor, String transactionId) throws RemoteException {
        // Verifica se a transação já foi aplicada para garantir idempotência
        if (transacoesAplicadas.containsKey(transactionId)) {
            System.out.println("Transacao de deposito " + transactionId + " ja aplicada. Ignorando.");
            return;
        }

        Conta conta = contas.get(contaId);
        if (conta == null) {
            System.out.println("Conta " + contaId + " nao encontrada.");
            return;
        }

        conta.depositar(valor);
        transacoesAplicadas.put(transactionId, true); // Marca a transação como aplicada
        System.out.println("Deposito de " + valor + " realizado na conta " + contaId);
    }

    public synchronized boolean sacar(int contaId, double valor, String transactionId) throws RemoteException {
        // Verifica se a transação já foi aplicada para garantir idempotência
        if (transacoesAplicadas.containsKey(transactionId)) {
            System.out.println("Transacao de saque " + transactionId + " ja aplicada. Ignorando.");
            return false;
        }

        Conta conta = contas.get(contaId);
        if (conta == null) {
            System.out.println("Conta " + contaId + " nao encontrada.");
            return false;
        }

        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente para realizar o saque da conta " + contaId);
            return false;
        }

        conta.sacar(valor);
        transacoesAplicadas.put(transactionId, true); // Marca a transação como aplicada
        System.out.println("Saque de " + valor + " realizado da conta " + contaId);
        return true;
    }
}
