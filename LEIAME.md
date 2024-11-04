# Instruções para Executar o Programa Cliente-Servidor com RMI

Este tutorial explica como compilar e rodar o programa de modelo cliente-servidor utilizando Java RMI no Visual Studio Code. Siga os passos abaixo para configurar e executar corretamente.

## Estrutura das Pastas

- `src`: pasta contendo os arquivos fonte `.java` do programa.
- `bin`: pasta onde os arquivos `.class` compilados serão armazenados.

## Passo a Passo para Execução

### 1. Configuração Inicial

1. Abra o Visual Studio Code e certifique-se de que o JDK está instalado e configurado no ambiente.
2. Navegue até o diretório `src`, onde estão localizados os arquivos `.java`.

### 2. Compilar os Arquivos Fonte

1. Abra o terminal no Visual Studio Code ou um terminal de sistema externo.
2. Navegue até a pasta `src` do projeto:
   cd src
   
3. Compile todos os arquivos Java dentro do diretório `src` com o seguinte comando:
   javac *.java

### 3. Configuração do IP do Servidor

Para o funcionamento do programa, é necessário configurar o IP do servidor. No arquivo `MainServidor.java`, localize a linha onde o IP é configurado:

System.setProperty("java.rmi.server.hostname", "192.168.15.4");

**Substitua `"localhost"` pelo IP da máquina que irá rodar o servidor**. Esse IP pode ser encontrado executando `ipconfig` (no Windows) ou `ifconfig` (no Linux/Mac) no terminal. Este IP será utilizado pelos clientes para se conectarem ao servidor.

### 4. Executar o Servidor

1. Abra um novo terminal e continue na pasta `src`.
2. Inicie o servidor executando o comando abaixo:
   ```bash
   java MainServidor
   ```
3. O servidor exibirá mensagens no terminal, indicando que o registry RMI foi iniciado e que o servidor está pronto para receber conexões.

### 5. Executar o Cliente

Para testar o programa, você pode abrir múltiplos terminais e executar o cliente várias vezes, simulando diferentes operações.

1. Em um novo terminal, continue na pasta `src`.
2. Execute o cliente com o comando apropriado para cada operação, substituindo `<servidor_ip>` pelo IP configurado no servidor:

   - **Abrir Conta**:
     ```bash
     java MainCliente <servidor_ip> abrirConta "Nome da Conta"
     ```
   - **Consultar Saldo**:
     ```bash
     java MainCliente <servidor_ip> consultarSaldo <contaId>
     ```
   - **Depósito**:
     ```bash
     java MainCliente <servidor_ip> depositar <contaId> <valor>
     ```
   - **Saque**:
     ```bash
     java MainCliente <servidor_ip> sacar <contaId> <valor>
     ```

3. Cada operação enviará uma requisição ao servidor. Por exemplo, para realizar um depósito de 500 na conta com ID 1, use:
```bash
   java MainCliente <seu_ip> depositar 1 500
   ```

4. O servidor processará a operação e retornará uma resposta no terminal do cliente, exibindo o sucesso ou erro da operação.

### 6. Considerações sobre Múltiplas Operações

- **Concorrência**: É possível abrir múltiplos terminais e executar operações simultâneas, como depósitos e saques, para testar o controle de concorrência no servidor.
- **Idempotência**: Transações repetidas com o mesmo ID de transação serão ignoradas pelo servidor, assegurando que cada operação seja processada apenas uma vez.

### Exemplo Completo de Execução

1. **No terminal do servidor**:
   java MainServidor
   

2. **No terminal do cliente** (para uma operação de depósito):
```bash
   java MainCliente <seu_ip> depositar 1 100
    ```

