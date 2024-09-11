package voting;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.TypeReference;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe para interagir com o contrato inteligente de votação.
 */
public class Voting extends Contract {

    public static final String FUNC_ADDCANDIDATE = "addCandidate";
    public static final String FUNC_VOTE = "vote";
    public static final String FUNC_GETTOTALVOTES = "getTotalVotes";

    // Construtor protegido, apenas a própria classe e subclasses podem instanciar diretamente.
    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super("", contractAddress, web3j, credentials, gasProvider);
    }

    /**
     * Método para carregar o contrato de votação.
     * @param contractAddress Endereço do contrato.
     * @param web3j Instância do Web3j.
     * @param credentials Credenciais do usuário.
     * @param gasProvider Provedor de gas para a transação.
     * @return Uma instância de Voting.
     */
    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new Voting(contractAddress, web3j, credentials, gasProvider);
    }

    /**
     * Adiciona um novo candidato ao contrato de votação.
     * @param name Nome do candidato.
     * @return Um RemoteCall para TransactionReceipt.
     */
    public RemoteCall<TransactionReceipt> addCandidate(String name) {
        final Function function = new Function(
                FUNC_ADDCANDIDATE,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.emptyList()
        );
        return executeRemoteCallTransaction(function);
    }

    /**
     * Registra um voto para o candidato especificado.
     * @param candidateId ID do candidato.
     * @return Um RemoteCall para TransactionReceipt.
     */
    public RemoteCall<TransactionReceipt> vote(BigInteger candidateId) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new Uint256(candidateId)),
                Collections.emptyList()
        );
        return executeRemoteCallTransaction(function);
    }

    /**
     * Obtém o total de votos para o candidato especificado.
     * @param candidateId ID do candidato.
     * @return Um RemoteCall para o total de votos como BigInteger.
     */
    public RemoteCall<BigInteger> getTotalVotes(BigInteger candidateId) {
        final Function function = new Function(
                FUNC_GETTOTALVOTES,
                Arrays.<Type>asList(new Uint256(candidateId)),
                Collections.singletonList(new TypeReference<Uint256>() {})
        );
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }
}
