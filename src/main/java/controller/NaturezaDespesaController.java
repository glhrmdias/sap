package controller;

import DAO.BancoDados;
import DAO.NaturezaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.GrupoFinanceiro;
import model.NaturezaDespesa;
import org.controlsfx.control.Notifications;
import util.ComboBoxKeyCompleter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class NaturezaDespesaController {

    @FXML public TextField codigoDespesaTextField;
    @FXML public TextArea descricaoTextArea;
    @FXML public ComboBox<GrupoFinanceiro> grupoFinanceiroComboBox;
    @FXML public Button fecharButton, cadastrarButton;


    private NaturezaDespesa naturezaDespesa;
    public ListNaturezaController listNaturezaControl;
    public PrincipalController principalController;

    public void setNatureza (NaturezaDespesa natureza) {
        this.naturezaDespesa = natureza;
        preCadastro();
    }

    public void setListNaturezaController(ListNaturezaController listNaturezaController) {
        listNaturezaControl = listNaturezaController;
    }

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    BancoDados bancoDados = new BancoDados();
    NaturezaDAO naturezaDAO = new NaturezaDAO();
    ObservableList<GrupoFinanceiro> grupoFinanceiros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        grupoFinanceiroComboBox.setItems(grupoFinanceiros);
        grupoFinanceiros.addAll(bancoDados.getGrupoFinanceiro());

        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        cadastrarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));

        ComboBoxKeyCompleter completer = new ComboBoxKeyCompleter();
        completer.install(grupoFinanceiroComboBox);
    }

    public void preCadastro() {
        codigoDespesaTextField.setText(naturezaDespesa.getCodDespesa());
        grupoFinanceiroComboBox.setValue(naturezaDespesa.getGrupoFinanceiro());
        descricaoTextArea.setText(naturezaDespesa.getDescricao());
    }

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void cadastrarNaturezaDespesa() {

        if (!validarDadosReq()) {
            exibirMensagemErro("Por favor, preencha todos os dados!");
            return;
        }

        boolean editando = true;

        if (naturezaDespesa == null) {
            naturezaDespesa = new NaturezaDespesa();
            editando = false;
        }

        naturezaDespesa.setCodDespesa(codigoDespesaTextField.getText());
        naturezaDespesa.setDescricao(descricaoTextArea.getText());
        naturezaDespesa.setGrupoFinanceiro(grupoFinanceiroComboBox.getValue());

        if (editando == false) {
            principalController.adicionarNatureza(naturezaDespesa);
            naturezaDespesa = null;
            cadastrarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else if (editando == true) {
            naturezaDAO.atualizandoNatureza(naturezaDespesa);
            cadastrarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }
    }

    public void notificandoCadastro() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Natureza da Despesa Cadastrada")
                .text("Natureza da Despesa cadastrada com sucesso")
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

    public void notificandoAtualizacao() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Natureza da Despesa Atualizada")
                .text("Natureza da Despesa atualizada com sucesso")
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

    private void exibirMensagemErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public boolean validarDadosReq() {

        if (codigoDespesaTextField.getText() == null || codigoDespesaTextField.getText().isEmpty()) {
            return false;
        }

        if (descricaoTextArea.getText() == null || descricaoTextArea.getText().isEmpty()) {
            return false;
        }

        if (grupoFinanceiroComboBox.getValue() == null) {
            return false;
        }
        return true;
    }


}
