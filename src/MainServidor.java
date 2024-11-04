import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("RMI registry already running.");
        }

        try {
            System.setProperty("java.rmi.server.hostname", "201.86.14.4"); 
            Naming.rebind("Banco", new Servidor());
            System.out.println("MainServidor is ready.");
        } catch (Exception e) {
            System.out.println("MainServidor failed:");
            e.printStackTrace();
        }
    }
}
