

import java.math.BigInteger;

public class Candidate {
    private BigInteger id;  // Identificador do candidato
    private String name;    // Nome do candidato
    private BigInteger voteCount; // Contagem de votos

    // Construtor para inicializar um candidato
    public Candidate(BigInteger id, String name, BigInteger voteCount) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
    }

    // Getter para ID
    public BigInteger getId() {
        return id;
    }

    // Setter para ID
    public void setId(BigInteger id) {
        this.id = id;
    }

    // Getter para nome
    public String getName() {
        return name;
    }

    // Setter para nome
    public void setName(String name) {
        this.name = name;
    }

    // Getter para contagem de votos
    public BigInteger getVoteCount() {
        return voteCount;
    }

    // Setter para contagem de votos
    public void setVoteCount(BigInteger voteCount) {
        this.voteCount = voteCount;
    }

    // Método para incrementar a contagem de votos
    public void incrementVoteCount() {
        this.voteCount = this.voteCount.add(BigInteger.ONE);
    }

    @Override
    public String toString() {
        return "Candidate{id=" + id + ", name='" + name + "', voteCount=" + voteCount + "}";
    }
}
