package controller;

import DAO.BancoDados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class LoguinController {

    @FXML public TextField loguinTextField;
    @FXML public PasswordField senhaTextField;
    @FXML public Button fecharButton, entrarButton;
    @FXML public Label loguinLabel;

    Usuario usuario = new Usuario();
    BancoDados bd = new BancoDados();

    String versao = ".0.0.7";

    @FXML
    public void initialize() {}

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void entrarSistema() {

        usuario = bd.getUsuarioMatricula(loguinTextField.getText());


        if (loguinTextField.getText() == null && senhaTextField.getText() == null) {
            /*loguinLabel.setText("Erro ao loggar!");*/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Usuário");
            alert.setHeaderText("Login e Senha em branco...");
            alert.setContentText("Entre em contato com a GETIG para dúvidas");
            alert.showAndWait();
            return;
        } else if (usuario != null) {
            if (!BCrypt.checkpw(senhaTextField.getText(), usuario.getSenha())) {
                /*loguinLabel.setText("Erro ao loggar!");*/
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Login ou Senha não conferem, tente novamente\n");
                alert.setContentText("Entre em contato com a GETIG para dúvidas");
                alert.setTitle("Dados de Login não conferem");
                alert.showAndWait();
                System.out.println("Erro");

            } else {
                /*loguinLabel.setText("Acesso confirmado!");*/
                System.out.println("Usário logado com sucesso!");
                try {
                    abrirPincipal();
                    loguinTextField.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            /*loguinLabel.setText("Erro ao loggar!");*/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Usuário");
            alert.setHeaderText("Login não existe...");
            alert.setContentText("Entre em contato com a GETIG para dúvidas");
            alert.showAndWait();
        }

    }

    public void abrirPincipal() throws IOException {
        Stage principalStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/janelaPrincipal.fxml"));
        principalStage.setTitle("SISTEMA DE PAGAMENTO (SAP) v" + versao);
        principalStage.setScene(new Scene(root));
        principalStage.setMaximized(true);
        principalStage.show();
    }


    @FXML
    private void handleKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && event.getSource() == senhaTextField) {
            entrarSistema();
            event.consume();
        } else if (event.getCode() == KeyCode.ENTER && event.getSource() == loguinTextField){
            entrarSistema();
            event.consume();
        }
    }

}
