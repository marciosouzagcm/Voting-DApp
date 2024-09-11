package voting;

/**
 * Representa um recibo de transação na blockchain.
 */
public class TransactionReceipt {

    private String transactionHash;

    // Construtor para inicializar o recibo de transação com o hash.
    public TransactionReceipt(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    /**
     * Obtém o hash da transação.
     * @return O hash da transação.
     */
    public String getTransactionHash() {
        return transactionHash;
    }

    // Você pode adicionar outros métodos e campos conforme necessário
}
