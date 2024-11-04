import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;

public class MainServidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("Erro: O RMI registry já está em execução ou ocorreu um problema ao inicializar o registry.");
            e.printStackTrace();
        }

        try {
            System.setProperty("java.rmi.server.hostname", "localhost");
            Naming.rebind("Banco", new Servidor());
            System.out.println("MainServidor is ready.");
        } catch (RemoteException e) {
            System.out.println("Erro: Falha ao registrar o objeto remoto. Verifique se o objeto implementa a interface Remote corretamente.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Erro: A URL fornecida para o registro RMI está incorreta.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao iniciar o MainServidor:");
            e.printStackTrace();
        }
    }
}
