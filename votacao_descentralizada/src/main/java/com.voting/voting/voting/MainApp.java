package voting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Aplicativo principal para gerenciar o sistema de votação.
 */
public class MainApp extends Application {

    private VotingService votingService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inicializa o serviço de votação com o endereço do contrato e a chave privada
            votingService = new VotingService("0x570Ea8851bD6cB67549357c8e591d12F69074Acd", "0x524a61eA2091B5b6122B9aE414d3ce399dD9070c");

            // Define o título da janela principal
            primaryStage.setTitle("Sistema de Votação");

            // Inicia o processo de cadastro de candidatos
            startCandidateRegistration(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
            // Considerar exibir uma mensagem de erro amigável ao usuário
        }
    }

    /**
     * Inicia a interface de cadastro de candidatos.
     * @param primaryStage A janela principal.
     */
    private void startCandidateRegistration(Stage primaryStage) {
        try {
            CandidateRegistrationApp registrationApp = new CandidateRegistrationApp(votingService);

            // Configura a cena inicial e exibe a janela
            primaryStage.setScene(registrationApp.getScene());
            primaryStage.show();

            // Avança para a votação após o cadastro
            registrationApp.setOnCloseRequest(event -> startVoting(primaryStage));
        } catch (Exception e) {
            e.printStackTrace();
            // Considerar exibir uma mensagem de erro amigável ao usuário
        }
    }

    /**
     * Inicia a interface de votação.
     * @param primaryStage A janela principal.
     */
    private void startVoting(Stage primaryStage) {
        try {
            VotingApp votingApp = new VotingApp(votingService);

            // Configura a cena de votação e exibe
            primaryStage.setScene(votingApp.getScene());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Considerar exibir uma mensagem de erro amigável ao usuário
        }
    }

    /**
     * Inicia a interface de resultados da eleição.
     * @param primaryStage A janela principal.
     */
    private void startElectionResults(Stage primaryStage) {
        try {
            ElectionResultsApp resultsApp = new ElectionResultsApp(votingService);

            // Configura a cena de resultados e exibe
            primaryStage.setScene(resultsApp.getScene());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Considerar exibir uma mensagem de erro amigável ao usuário
        }
    }
}