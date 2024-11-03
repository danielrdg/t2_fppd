import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgenciaInterface extends Remote {
    void abrirConta(String titular) throws RemoteException;
    void fecharConta(int contaId) throws RemoteException;
    void depositar(int contaId, double valor) throws RemoteException;
    void sacar(int contaId, double valor) throws RemoteException;
    double consultarSaldo(int contaId) throws RemoteException;
}
