package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import login.login;
import cadastro.cadastro;

public class iniciar extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Instanciando a classe button para criar um botão para login
        Button botaoLogin = new Button("Login");
        botaoLogin.setLayoutX(100);  // posição x
        botaoLogin.setLayoutY(50);   // Posição y
        botaoLogin.setPrefWidth(100);  // largura
        botaoLogin.setPrefHeight(20); // tamanho
        
        // Definindo ação para o botão de login
        botaoLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login loginJanela = new login();
                try {
                    loginJanela.start(new Stage()); // Abre a nova janela
                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        });

        // Instanciando a classe button para criar um botão para cadastro
        Button botaoCadastro = new Button("Cadastro");
        botaoCadastro.setLayoutX(100); // posição x
        botaoCadastro.setLayoutY(150); // Posição y
        botaoCadastro.setPrefWidth(100);  // largura
        botaoCadastro.setPrefHeight(20); // tamanho

        // Definindo ação para o botão de cadastro
        botaoCadastro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadastro cadastroJanela = new cadastro();
                try {
                    cadastroJanela.start(new Stage()); // Abre a nova janela
                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        });

        // Criando um Label acima do botão de cadastro
        Label textoCadastro = new Label("Não possui cadastro? Clique aqui para se cadastrar");
        textoCadastro.setLayoutX(15);  // Posição x para alinhar com o botão
        textoCadastro.setLayoutY(125);  // Posição y um pouco acima do botão de cadastro

        // Instanciando a classe button para criar um botão para sair
        Button botaoSair = new Button("Sair");
        botaoSair.setLayoutX(100);  // posição x
        botaoSair.setLayoutY(200);  // posição y abaixo do botão de cadastro
        botaoSair.setPrefWidth(100);  // largura
        botaoSair.setPrefHeight(20); // tamanho

        // Definindo ação para o botão de sair
        botaoSair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close(); // Fecha a aplicação
            }
        });

        // Criando o Pane para posicionamento absoluto dos botões e do texto
        Pane root = new Pane();
        root.getChildren().addAll(botaoLogin, botaoCadastro, textoCadastro, botaoSair);

        // Criando a cena
        Scene Janela = new Scene(root, 300, 250);

        // Configurando o palco (janela)
        primaryStage.setTitle("Programa");
        primaryStage.setScene(Janela);
        primaryStage.setResizable(false);  // impede o redimensionamento da janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
