import java.rmi.RemoteException;

public class CaixaAutomatico implements CaixaAutomaticoInterface {
    private ServidorInterface servidor;

    public CaixaAutomatico(ServidorInterface servidor) {
        this.servidor = servidor;
    }

    public void depositar(int contaId, double valor) throws RemoteException {
        servidor.depositar(contaId, valor);
        System.out.println("Depósito de " + valor + " na conta " + contaId);
    }

    public void sacar(int contaId, double valor) throws RemoteException {
        servidor.sacar(contaId, valor);
        System.out.println("Saque de " + valor + " da conta " + contaId);
    }

    public double consultarSaldo(int contaId) throws RemoteException {
        return servidor.consultarSaldo(contaId);
    }
}
