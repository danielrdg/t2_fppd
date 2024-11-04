import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServidor {
    public static void main(String[] args) {
        try {
            // Cria o registry na porta 1099 (ou usa o existente)
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Inicializa o objeto remoto
            ServidorInterface servidor = new Servidor();
            
            // Registra o objeto remoto no RMI Registry
            registry.rebind("Banco", servidor);
            
            System.out.println("Servidor iniciado e registrado no RMI Registry...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
