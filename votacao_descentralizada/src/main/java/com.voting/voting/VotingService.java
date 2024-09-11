package voting;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.concurrent.CompletableFuture;
import org.web3j.crypto.Credentials;

public class VotingService {

    // Instância do contrato inteligente de votação
    private Voting contract;

    /**
     * Construtor do VotingService que inicializa a conexão com o contrato inteligente.
     *
     * @param rpcUrl URL do nó RPC (endpoint) da BSC.
     * @param privateKey Chave privada usada para assinar transações.
     * @throws Exception Se o contrato não puder ser carregado corretamente.
     */
    public VotingService(String rpcUrl, String privateKey) throws Exception {
        // Conectar ao nó da Binance Smart Chain
        Web3j web3 = Web3j.build(new HttpService(rpcUrl));

        // Criar as credenciais a partir da chave privada
        Credentials credentials = Credentials.create(privateKey);

        // Endereço do contrato de votação na blockchain
        String contractAddress = "0x570Ea8851bD6cB67549357c8e591d12F69074Acd"; // Deve ser substituído pelo endereço do contrato em uso

        // Carregar o contrato usando as credenciais e o provedor de gas padrão
        contract = Voting.load(contractAddress, web3, credentials, new DefaultGasProvider());

        // Verificar se o contrato foi carregado corretamente
        if (contract.isValid()) {
            System.out.println("Contrato carregado com sucesso.");
        } else {
            throw new Exception("Falha ao carregar o contrato.");
        }
    }

    /**
     * Registra um voto para o candidato especificado.
     *
     * @param candidateId ID do candidato que receberá o voto.
     * @throws Exception Se a transação falhar.
     */
    public void vote(java.math.BigInteger candidateId) throws Exception {
        contract.vote(candidateId).send(); // Executa a transação de votação
    }

    /**
     * Obtém o total de votos para um candidato específico de forma assíncrona.
     *
     * @param candidateId ID do candidato para o qual o total de votos será obtido.
     * @return CompletableFuture que resolve o total de votos.
     */
    public CompletableFuture<java.math.BigInteger> getTotalVotes(java.math.BigInteger candidateId) {
        return contract.getTotalVotes(candidateId).sendAsync(); // Chamada assíncrona para obter o total de votos
    }

    /**
     * Adiciona um novo candidato ao contrato de votação.
     *
     * @param candidateName Nome do candidato a ser adicionado.
     * @throws Exception Se a transação falhar.
     */
    public void addCandidate(String candidateName) throws Exception {
        contract.addCandidate(candidateName).send(); // Executa a transação para adicionar um candidato
    }
