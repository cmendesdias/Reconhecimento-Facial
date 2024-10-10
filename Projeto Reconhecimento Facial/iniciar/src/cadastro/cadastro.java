package cadastro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane; // Importar StackPane
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import main.cadastroConexao;
import main.iniciar; // Importando a classe iniciar

public class cadastro extends Application {
    String emailString, nomeString, nivelAcessoString;

    private File arquivoSelecionado;

    @Override
    public void start(Stage primaryStage) {

        // Criando os objetos
        Label labelEmail = new Label("E-mail:");
        TextField campoEmail = new TextField();
        campoEmail.setPrefWidth(200); 
        campoEmail.setPromptText("Digite o email");


        Label labelNome = new Label("Nome:"); 
        TextField campoNome = new TextField(); 
        campoNome.setPrefWidth(200); 
        campoNome.setPromptText("Digite seu nome");


        Label labelNivelAcesso = new Label("Nível de Acesso:"); 
        TextField campoNivelAcesso = new TextField(); 
        campoNivelAcesso.setPrefWidth(200); 
        campoNivelAcesso.setPromptText("Digite o nível de acesso");


        
        // Label para exibir o nome do arquivo selecionado
        Label labelArquivoSelecionado = new Label("Nenhum arquivo selecionado.");

        // Botão "Enviar Foto" - inicialmente invisível - só aparece depois que foi selecionado alguma foto - olhar linha 85
        Button botaoEnviarFoto = new Button("Enviar Foto");
        botaoEnviarFoto.setVisible(false); // Define como invisível inicialmente
        
        // criando um ImageView para mostrar a imagem selecionada
        ImageView visualizarImagem = new ImageView();
        visualizarImagem.setFitWidth(250); // Largura do ImageView
        visualizarImagem.setFitHeight(250); // Altura do ImageView
        visualizarImagem.setPreserveRatio(true); // Mantem a proporção da imagem
        
        // criando botão botaoProcurarFoto
        Button botaoProcurarFoto = new Button("Procurar Foto");
        botaoProcurarFoto.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Foto");
            // Adicionando filtros de arquivos
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
            );
        
            // Mostrando o explorador de arquivos
            arquivoSelecionado = fileChooser.showOpenDialog(primaryStage);
            if (arquivoSelecionado != null) {
                // Carregar a imagem no ImageView
                Image foto = new Image(arquivoSelecionado.toURI().toString());
                visualizarImagem.setImage(foto); // Definindo a imagem para o ImageView
        
                // Atualizando o Label com o nome do arquivo selecionado
                labelArquivoSelecionado.setText("Arquivo: " + arquivoSelecionado.getName());
        
                // Tornando o botão "Enviar Foto" visível
                botaoEnviarFoto.setVisible(true); // Olhar linha 127
            } else {
                // Resetando o estado se o usuário cancelar a seleção
                visualizarImagem.setImage(null); // Remove a imagem do ImageView
                labelArquivoSelecionado.setText("Nenhum arquivo selecionado."); // Resetando o texto do label
                botaoEnviarFoto.setVisible(false); // botaoEnviarFoto invisível se nenhum arquivo for selecionado
            }
        });
        


        // Botão Cadastrar - passar parametros -> email - nome - nivel de acesso - foto
        // Criar if pra validação
    Button botaoCadastro = new Button("Cadastrar");
    botaoCadastro.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            emailString = campoEmail.getText();
            nomeString = campoNome.getText();
            nivelAcessoString = campoNivelAcesso.getText();
            
        // Verificando se todas as variáveis são não nulas e não vazias
        if (emailString != null && !emailString.isEmpty() &&
            nomeString != null && !nomeString.isEmpty() &&
            nivelAcessoString != null &&  !nivelAcessoString.isEmpty() &&
            arquivoSelecionado != null) {
          
                // Chamando o controlador com os dados
                new cadastroConexao(emailString, nomeString, nivelAcessoString, arquivoSelecionado);          

                Alert AlertaOK = new Alert(AlertType.INFORMATION);
                AlertaOK.setTitle("Cadastro");
                AlertaOK.setHeaderText("Sucesso");
                AlertaOK.setContentText("Cadastro realizado com sucesso!");
                AlertaOK.showAndWait();
                

                // Esvazia os textfields
                campoEmail.setText("");
                campoNome.setText("");
                campoNivelAcesso.setText("");

                // Limpa a imagem do ImageView
                visualizarImagem.setImage(null); // Remove a imagem do ImageView
                arquivoSelecionado = null; // Reseta a variável de arquivo selecionado
                labelArquivoSelecionado.setText("Nenhum arquivo selecionado.");

        } else {
            Alert AlertaErro = new Alert(AlertType.ERROR);
            AlertaErro.setTitle("Erro");
            AlertaErro.setHeaderText("Falha no Cadastro");
            AlertaErro.setContentText("Por favor, preencha todos os campos obrigatórios.");
            AlertaErro.showAndWait();
            }
        }    
    });

        
        // Botão Voltar
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iniciar iniciarJanela = new iniciar(); // Cria uma nova instância da classe iniciar
                try {
                    iniciarJanela.start(new Stage()); // Abre a nova janela
                    primaryStage.close(); // Fecha a janela de cadastro
                } catch (Exception e) {
                    e.printStackTrace(); // Imprime o rastreamento da pilha
                }
            }
        });

        // Botões adicionais
        Button botaoTirarFoto = new Button("Tirar Foto");
        

        botaoEnviarFoto.setOnAction(event -> {
            if (arquivoSelecionado != null) {
                try {
                    // Defina o caminho de destino
                    File destino = new File("C:\\Users\\caiom\\OneDrive\\Imagens\\Reconhecimento Facial Fotos\\" + arquivoSelecionado.getName());
            
                    // Copiando o arquivo selecionado para o diretório de destino
                    Files.copy(arquivoSelecionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    // Mostrando alerta de sucesso
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Enviar Foto");
                    alert.setHeaderText("Sucesso");
                    alert.setContentText("Foto enviada para: " + destino.getAbsolutePath());
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Falha ao Enviar Foto");
                alert.setContentText("Erro ao copiar a foto: " + e.getMessage());
                alert.showAndWait();
                }
            }
        });


        Button botaoSair = new Button("Sair");
        botaoSair.setOnAction(event -> primaryStage.close()); // Ação para o botão Sair

        // Criando um VBox para os campos e botões
        VBox vboxCampos = new VBox(10); // Espaçamento de 10 pixels entre os campos
        vboxCampos.setAlignment(Pos.CENTER_LEFT); // Alinhando os campos à esquerda
        vboxCampos.setPadding(new Insets(10)); // Espaçamento interno

        // Adicionando os campos ao VBox
        vboxCampos.getChildren().addAll(
            labelEmail, campoEmail,
            labelNome, campoNome,
            labelNivelAcesso, campoNivelAcesso,
            labelArquivoSelecionado,
            botaoTirarFoto,
            botaoProcurarFoto,
            botaoEnviarFoto,
            botaoCadastro,
            botaoVoltar,
            botaoSair
        );

        // Criando um StackPane para o ImageView
        StackPane imageContainer = new StackPane(visualizarImagem);
        imageContainer.setPadding(new Insets(20)); // Espaçamento em torno do ImageView

        // Criando um BorderPane para o layout
        BorderPane layout = new BorderPane();

        // Adicionando o VBox de campos à esquerda do BorderPane
        layout.setLeft(vboxCampos);
        
        // Adicionando o StackPane com o ImageView à direita
        layout.setRight(imageContainer); // Coluna 1, Linha 4

        // Criando a cena com tamanho maior
        Scene Janela = new Scene(layout, 600, 500); // Aumentando a largura e a altura

        // Configurando o palco (janela)
        primaryStage.setTitle("Cadastro");
        primaryStage.setScene(Janela);
        primaryStage.setResizable(false);  // impede o redimensionamento da janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
