import java.rmi.RemoteException;
import java.util.UUID;

public class CaixaAutomatico implements CaixaAutomaticoInterface {
    private ServidorInterface servidor;

    public CaixaAutomatico(ServidorInterface servidor) {
        this.servidor = servidor;
    }

    public void depositar(int contaId, double valor) throws RemoteException {
        String transactionId = UUID.randomUUID().toString(); 
        servidor.depositar(contaId, valor, transactionId);
        System.out.println("Depósito de " + valor + " na conta " + contaId + " com transactionId: " + transactionId);
    }

    public void sacar(int contaId, double valor) throws RemoteException {
        String transactionId = UUID.randomUUID().toString(); 
        boolean sucesso = servidor.sacar(contaId, valor, transactionId);
        
        if (sucesso) {
            System.out.println("Saque de " + valor + " da conta " + contaId + " com transactionId: " + transactionId);
        } else {
            System.out.println("Falha ao realizar saque de " + valor + " da conta " + contaId + ". Verifique saldo e tente novamente.");
        }
    }

    public double consultarSaldo(int contaId) throws RemoteException {
        double saldo = servidor.consultarSaldo(contaId);
        System.out.println("Saldo da conta " + contaId + ": " + saldo);
        return saldo;
    }
}
