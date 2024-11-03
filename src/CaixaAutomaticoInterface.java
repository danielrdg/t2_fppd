import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CaixaAutomaticoInterface extends Remote {
    void depositar(int contaId, double valor) throws RemoteException;
    void sacar(int contaId, double valor) throws RemoteException;
    double consultarSaldo(int contaId) throws RemoteException;
}
