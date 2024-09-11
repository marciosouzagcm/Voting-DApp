package voting;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.application.Platform;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class ElectionResultsApp {
    private Scene scene;
    private VotingService votingService;

    public ElectionResultsApp(VotingService votingService) {
        this.votingService = votingService;

        // Configuração da interface de resultados da eleição
        VBox layout = new VBox(10);

        Label candidateIdLabel = new Label("ID do Candidato:");
        TextField candidateIdField = new TextField();
        Button resultsButton = new Button("Obter Resultados");
        Label resultsLabel = new Label();

        // Configuração do botão de obter resultados
        resultsButton.setOnAction(e -> {
            try {
                BigInteger candidateId = new BigInteger(candidateIdField.getText());

                // Obtenção dos votos de forma assíncrona
                CompletableFuture<BigInteger> totalVotesFuture = votingService.getTotalVotes(candidateId);
                totalVotesFuture.thenAccept(totalVotes -> {
                    // Atualizando a interface gráfica na UI Thread
                    Platform.runLater(() -> resultsLabel.setText("Total de votos para o candidato " + candidateId + ": " + totalVotes));
                }).exceptionally(ex -> {
                    // Tratamento de exceções e atualização da interface gráfica na UI Thread
                    Platform.runLater(() -> resultsLabel.setText("Erro ao obter resultados: " + ex.getMessage()));
                    return null;
                });

            } catch (NumberFormatException ex) {
                resultsLabel.setText("ID do candidato inválido.");
            } catch (Exception ex) {
                resultsLabel.setText("Erro ao obter resultados: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(candidateIdLabel, candidateIdField, resultsButton, resultsLabel);
        this.scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setOnCloseRequest(EventHandler<WindowEvent> onClose) {
        this.scene.getWindow().setOnCloseRequest(onClose);
    }
}
