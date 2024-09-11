package voting;

import org.web3j.tx.gas.ContractGasProvider;
import java.math.BigInteger;

public class DefaultGasProvider implements ContractGasProvider {

    @Override
    public BigInteger getGasPrice(String contractFunc) {
        // Define o preço do gás (gas price) padrão, por exemplo, 20 Gwei
        return BigInteger.valueOf(20_000_000_000L); // 20 Gwei em Wei
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        // Define o limite de gás (gas limit) padrão, por exemplo, 4.500.000
        return BigInteger.valueOf(4_500_000);
    }

    @Override
    public BigInteger getGasPrice() {
        // Retorna o preço do gás padrão
        return getGasPrice(null);
    }

    @Override
    public BigInteger getGasLimit() {
        // Retorna o limite de gás padrão
        return getGasLimit(null);
    }
}
