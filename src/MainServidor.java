import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServidor {
    public static void main(String[] args) {
        try {
            ServidorInterface servidor = new Servidor();
            Registry registry = LocateRegistry.getRegistry(1099); // Conecta ao registry já em execução
            registry.rebind("Banco", servidor); // Registra o objeto remoto
            System.out.println("Servidor iniciado e registrado no RMI Registry...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
