import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterface extends Remote {
    int criarConta(String titular) throws RemoteException;
    void fecharConta(int contaId) throws RemoteException;
    double consultarSaldo(int contaId) throws RemoteException;
    void depositar(int contaId, double valor, String transactionId) throws RemoteException;
    boolean sacar(int contaId, double valor, String transactionId) throws RemoteException;
}
