package voting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigInteger;

public class VotingApp extends Application {
    private Scene scene;
    private VotingService votingService;

    public VotingApp(VotingService votingService2) {
        // Construtor vazio, removendo a tentativa de inicialização aqui
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Aplicativo de Votação");

            // Inicializar o serviço de votação com parâmetros reais
            votingService = new VotingService("https://bsc-dataseed.binance.org/", "SUA_CHAVE_PRIVADA");

            // Configuração da interface de votação
            VBox layout = new VBox(10);

            Label candidateIdLabel = new Label("ID do Candidato:");
            TextField candidateIdField = new TextField();
            Button voteButton = new Button("Votar");
            Label statusLabel = new Label();

            // Configuração do botão de votar
            voteButton.setOnAction(e -> {
                try {
                    String input = candidateIdField.getText().trim();
                    if (input.isEmpty()) {
                        statusLabel.setText("ID do candidato não pode estar vazio.");
                        return;
                    }

                    BigInteger candidateId = new BigInteger(input);
                    votingService.vote(candidateId);
                    statusLabel.setText("Voto registrado com sucesso para o candidato " + candidateId + ".");
                    candidateIdField.clear(); // Limpar o campo de texto após o sucesso
                } catch (NumberFormatException ex) {
                    statusLabel.setText("ID do candidato inválido. Por favor, insira um número.");
                } catch (Exception ex) {
                    statusLabel.setText("Erro ao registrar voto: " + ex.getMessage());
                }
            });

            layout.getChildren().addAll(candidateIdLabel, candidateIdField, voteButton, statusLabel);
            scene = new Scene(layout, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Definindo o comportamento ao fechar a janela
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Janela fechada.");
                // Aqui você pode liberar recursos se necessário
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao iniciar a aplicação: " + e.getMessage());
        }
    }

    public Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }
}
