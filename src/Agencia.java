import java.rmi.RemoteException;
import java.util.UUID;

public class Agencia implements AgenciaInterface {
    private ServidorInterface servidor;

    public Agencia(ServidorInterface servidor) {
        this.servidor = servidor;
    }

    public void abrirConta(String titular) throws RemoteException {
        int contaId = servidor.criarConta(titular);
        System.out.println("Conta aberta com ID: " + contaId + " para o usuário " + titular);
    }

    public void fecharConta(int contaId) throws RemoteException {
        servidor.fecharConta(contaId);
        System.out.println("Conta fechada com ID: " + contaId);
    }

    public void depositar(int contaId, double valor) throws RemoteException {
        String transactionId = UUID.randomUUID().toString(); 
        servidor.depositar(contaId, valor, transactionId);
        System.out.println("Depósito de " + valor + " na conta " + contaId + " com transactionId: " + transactionId);
    }

    public void sacar(int contaId, double valor) throws RemoteException {
        String transactionId = UUID.randomUUID().toString();

        if (servidor.sacar(contaId, valor, transactionId)) {
            System.out.println("Saque de " + valor + " da conta " + contaId + " com transactionId: " + transactionId);
        } else {
            System.out.println("Erro: Saque de " + valor + " não foi realizado na conta " + contaId);
        }
    }

    public double consultarSaldo(int contaId) throws RemoteException {
        double saldo = servidor.consultarSaldo(contaId);
        System.out.println("Saldo da conta " + contaId + ": " + saldo);
        return saldo;
    }
}
