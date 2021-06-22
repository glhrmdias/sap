package controller;

import DAO.SubacaoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Subacao;
import org.controlsfx.control.Notifications;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class SubacaoController {

    @FXML public TextField subacaoTextField;
    @FXML public TextArea descricaoTextArea;
    @FXML public Button fecharButton, cadastrarButton;

    public PrincipalController principalController;
    public ListSubacaoController listSubacaoController;

    public void setListSubacaoController (ListSubacaoController controller) {
        listSubacaoController = controller;
    }

    private Subacao subacao;

    public void setSubacao(Subacao subacao) {
        this.subacao = subacao;
        preCadastro();
    }

    public void preCadastro() {
        subacaoTextField.setText(String.valueOf(subacao.getSubacao()));
        descricaoTextArea.setText(subacao.getDescricao());
    }

    SubacaoDAO subacaoDAO = new SubacaoDAO();

    @FXML public void initialize() {
        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        cadastrarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));
    }

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void cadastrarSubacao() {

        if (!validarDados()) {
            exibirMensagemErro("Por favor, preencha todos os dados!");
            return;
        }

        boolean editando = true;

        if (subacao == null) {
            subacao = new Subacao();
            editando = false;
        }

        subacao.setSubacao(subacaoTextField.getText());
        subacao.setDescricao(descricaoTextArea.getText());

        if (editando == false) {
            principalController.adicionarSubacao(subacao);
            subacao = null;
            cadastrarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else if (editando){
            subacaoDAO.atualizandoSubacao(subacao);
            cadastrarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }

    }

    public boolean validarDados() {

        if (subacaoTextField.getText() == null || subacaoTextField.getText().isEmpty()) {
            return false;
        }

        if (descricaoTextArea.getText() == null || descricaoTextArea.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void exibirMensagemErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void notificandoAtualizacao() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Subação Atualizada")
                .text("Subação atualizada com sucesso")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Notificação cadastro");
                    }
                });
        notifications.show();
    }

    public void notificandoCadastro() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Subação Cadastrada")
                .text("Subação cadastrado com sucesso")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(2.5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Notificação cadastro");
                    }
                });
        notifications.show();
    }


}
