package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import main.iniciar;

public class login extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Criando a label para o usuário
        Label labelUsuario = new Label("Email do Usuário:");

        // Campo de texto para o usuário
        TextField campoEmail = new TextField();
        campoEmail.setPromptText("Digite o email");

        // Criando a label onde será exibida a câmera
        Label cameraLabel = new Label("Aqui será exibida a câmera.");
        cameraLabel.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-min-width: 200px; -fx-min-height: 150px;"); // Estilo para simular uma área de vídeo com tamanho mínimo

        // Ajustando a posição da label da câmera
        StackPane cameraPane = new StackPane(cameraLabel);
        cameraPane.setPadding(new Insets(20)); // Adiciona espaçamento interno ao redor do label
        cameraPane.setAlignment(Pos.TOP_CENTER); // Posiciona o label mais ao centro do StackPane

        // Criando o botão "Tirar Foto"
        Button botaoTirarFoto = new Button("Tirar Foto");
        botaoTirarFoto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para tirar foto
                System.out.println("Foto tirada para o usuário: " + campoEmail.getText()); // Placeholder para lógica
            }
        });

        // Criando o botão "Voltar"
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iniciar iniciarJanela = new iniciar(); // Cria uma nova instância da classe iniciar
                try {
                    iniciarJanela.start(new Stage()); // Abre a nova janela de login
                    primaryStage.close(); // Fecha a janela da câmera
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Criando um layout de VBox para os botões e campos (à esquerda)
        VBox vboxEsquerda = new VBox(10); // 10 pixels de espaçamento entre os elementos
        vboxEsquerda.setPadding(new Insets(10)); // Adiciona espaço em torno do VBox
        vboxEsquerda.getChildren().addAll(labelUsuario, campoEmail, botaoTirarFoto, botaoVoltar); // Adiciona os elementos ao VBox

        // Criando um layout de BorderPane
        BorderPane layout = new BorderPane();
        layout.setLeft(vboxEsquerda); // Colocando o VBox com os campos à esquerda
        layout.setRight(cameraPane); // Colocando o StackPane com a câmera à direita

        // Criando a cena
        Scene scene = new Scene(layout, 500, 300); // Aumentando o tamanho da cena

        // Configurando o palco (janela)
        primaryStage.setTitle("Login com Câmera");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // impede o redimensionamento da janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
