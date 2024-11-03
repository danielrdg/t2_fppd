import java.rmi.RemoteException;

public class Agencia implements AgenciaInterface {
    private ServidorInterface servidor;

    public Agencia(ServidorInterface servidor) {
        this.servidor = servidor;
    }

    public void abrirConta(String titular) throws RemoteException {
        int contaId = servidor.criarConta(titular);
        System.out.println("Conta aberta com ID: " + contaId);
    }

    public void fecharConta(int contaId) throws RemoteException {
        servidor.fecharConta(contaId);
        System.out.println("Conta fechada com ID: " + contaId);
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
