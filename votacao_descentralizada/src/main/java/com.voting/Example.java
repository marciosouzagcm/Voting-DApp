package voting;

import org.web3j.crypto.Credentials;

public class Example {
    public static void main(String[] args) {
        // Substitua pela sua chave privada
        String privateKey = "0x0f71753eb21c6d31010c9f968f3819ed68eada232226edef8ee6cabca9526cd9";
        Credentials credentials = Credentials.create(privateKey);
        System.out.println("Endereço público: " + credentials.getAddress());
    }
}
