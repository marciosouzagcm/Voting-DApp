package voting;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

public class CandidateRegistrationApp {
    private Scene scene;
    private VotingService votingService;
    private VBox layout;

    /**
     * @param votingService Serviço de votação para interagir com o contrato inteligente
     */
    public CandidateRegistrationApp(VotingService votingService) {
        this.votingService = votingService;
        this.layout = new VBox(10);

        // Configuração da interface de registro de candidatos
        Label nameLabel = new Label("Nome do Candidato:");
        TextField nameField = new TextField();
        Button addButton = new Button("Adicionar Candidato");
        Label statusLabel = new Label();

        // Configuração do botão de adicionar candidato
        addButton.setOnAction(e -> {
            String candidateName = nameField.getText();
            if (candidateName.isEmpty()) {
                statusLabel.setText("Nome do candidato não pode estar vazio.");
            } else {
                try {
                    votingService.addCandidate(candidateName);
                    statusLabel.setText("Candidato '" + candidateName + "' adicionado com sucesso.");
                    nameField.clear();
                } catch (Exception ex) {
                    statusLabel.setText("Erro ao adicionar candidato: " + ex.getMessage());
                }
            }
        });

        // Adiciona os componentes ao layout e cria a cena
        layout.getChildren().addAll(nameLabel, nameField, addButton, statusLabel);
        this.scene = new Scene(layout, 400, 300); // Define o tamanho da cena
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setOnCloseRequest(EventHandler<WindowEvent> onClose) {
        if (this.scene.getWindow() != null) {
            this.scene.getWindow().setOnCloseRequest(onClose);
        }
    }
}
