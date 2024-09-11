package voting;

import java.math.BigInteger;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

public class Web3jExample {

    // Conexão com o Web3j
    private static Web3j web3j;

    // Variáveis de configuração
    private static String privateKey;
    private static String contractAddress;
    
    // Credenciais e Provedor de Gas
    private static Credentials credentials;
    private static DefaultGasProvider gasProvider;

    // Instância do contrato inteligente de votação
    private static Voting voting;

    public static void main(String[] args) {
        try {
            // Inicializar a conexão com o nó BSC
            String bscNodeUrl = "https://bsc-dataseed.binance.org/";
            web3j = Web3j.build(new HttpService(bscNodeUrl));
            System.out.println("Conectado ao nó BSC com sucesso.");

            // Configurar a chave privada e o endereço do contrato
            privateKey = "0x0f71753eb21c6d31010c9f968f3819ed68eada232226edef8ee6cabca9526cd9";
            contractAddress = "0x570Ea8851bD6cB67549357c8e591d12F69074Acd";

            // Criar as credenciais com a chave privada
            credentials = Credentials.create(privateKey);

            // Usar o provedor de gas padrão para estimar os custos de transação
            gasProvider = new DefaultGasProvider();

            // Carregar o contrato inteligente de votação
            voting = Voting.load(contractAddress, web3j, credentials, gasProvider);

            // Executar operações de exemplo
            adicionarCandidato("Candidato 1");
            registrarVoto(BigInteger.valueOf(1));
            obterTotalDeVotos(BigInteger.valueOf(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um novo candidato ao contrato de votação.
     * @param nomeCandidato Nome do candidato a ser adicionado.
     */
    private static void adicionarCandidato(String nomeCandidato) {
        try {
            System.out.println("Adicionando candidato...");
            org.web3j.protocol.core.methods.response.TransactionReceipt receipt = voting.addCandidate(nomeCandidato).send();
            System.out.println("Candidato adicionado. Hash da transação: " + receipt.getTransactionHash());
        } catch (Exception e) {
            System.err.println("Erro ao adicionar candidato: " + e.getMessage());
        }
    }

    /**
     * Registra um voto para o candidato especificado.
     * @param candidatoId ID do candidato para o qual o voto será registrado.
     */
    private static void registrarVoto(BigInteger candidatoId) {
        try {
            System.out.println("Registrando voto...");
            org.web3j.protocol.core.methods.response.TransactionReceipt receipt = voting.vote(candidatoId).send();
            System.out.println("Voto registrado. Hash da transação: " + receipt.getTransactionHash());
        } catch (Exception e) {
            System.err.println("Erro ao registrar voto: " + e.getMessage());
        }
    }

    /**
     * Obtém o total de votos para um candidato específico.
     * @param candidatoId ID do candidato para o qual o total de votos será obtido.
     */
    private static void obterTotalDeVotos(BigInteger candidatoId) {
        try {
            BigInteger totalVotos = voting.getTotalVotes(candidatoId).send();
            System.out.println("Total de votos para o candidato " + candidatoId + ": " + totalVotos);
        } catch (Exception e) {
            System.err.println("Erro ao obter total de votos: " + e.getMessage());
        }
    }
}
