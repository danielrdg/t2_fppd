import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainCliente {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java MainCliente <servidor_ip> <comando> [<parametros>]");
            System.out.println("Exemplos:");
            System.out.println("  java MainCliente 192.168.0.1 abrirConta \"Nome da Conta\"");
            System.out.println("  java MainCliente 192.168.0.1 consultarSaldo <contaId>");
            System.out.println("  java MainCliente 192.168.0.1 depositar <contaId> <valor>");
            System.out.println("  java MainCliente 192.168.0.1 sacar <contaId> <valor>");
            return;
        }

        try {
            String servidorIp = args[0];
            String comando = args[1];
            Registry registry = LocateRegistry.getRegistry(servidorIp, 1099);
            ServidorInterface servidor = (ServidorInterface) registry.lookup("Banco");

            switch (comando) {
                case "abrirConta":
                    if (args.length != 3) {
                        System.out.println("Uso: java MainCliente <servidor_ip> abrirConta \"Nome da Conta\"");
                        return;
                    }
                    String titular = args[2];
                    int contaId = servidor.criarConta(titular);
                    System.out.println("Conta criada com ID: " + contaId);
                    break;

                case "consultarSaldo":
                    if (args.length != 3) {
                        System.out.println("Uso: java MainCliente <servidor_ip> consultarSaldo <contaId>");
                        return;
                    }
                    int consultaId = Integer.parseInt(args[2]);
                    double saldo = servidor.consultarSaldo(consultaId);
                    System.out.println("Saldo da conta " + consultaId + ": " + saldo);
                    break;

                case "depositar":
                    if (args.length != 4) {
                        System.out.println("Uso: java MainCliente <servidor_ip> depositar <contaId> <valor>");
                        return;
                    }
                    int contaIdDeposito = Integer.parseInt(args[2]);
                    double valorDeposito = Double.parseDouble(args[3]);
                    servidor.depositar(contaIdDeposito, valorDeposito);
                    System.out.println("Depósito de " + valorDeposito + " realizado na conta " + contaIdDeposito);
                    break;

                case "sacar":
                    if (args.length != 4) {
                        System.out.println("Uso: java MainCliente <servidor_ip> sacar <contaId> <valor>");
                        return;
                    }
                    int contaIdSaque = Integer.parseInt(args[2]);
                    double valorSaque = Double.parseDouble(args[3]);
                    
                    if (servidor.sacar(contaIdSaque, valorSaque)) {
                        System.out.println("Saque de " + valorSaque + " realizado da conta " + contaIdSaque);
                    } else {
                        System.out.println("Erro: Saque de " + valorSaque + " nao foi realizado na conta " + contaIdSaque);
                    }
                    break;

                default:
                    System.out.println("Comando desconhecido: " + comando);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}