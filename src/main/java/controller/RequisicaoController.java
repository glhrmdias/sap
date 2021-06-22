package controller;

import DAO.BancoDados;
import DAO.RequisicaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import model.Requisicao;
import model.Sap;
import org.controlsfx.control.Notifications;
import util.ComboBoxKeyCompleter;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class RequisicaoController {

    @FXML public TextField numerorequisicaoTextField, valorrequisicaoTextField;
    @FXML public ComboBox<Unidade> setorComboBox;
    @FXML public DatePicker datasolicitacaoDatePicker;
    @FXML public ComboBox<Credor> credorComboBox;
    @FXML public ComboBox<Subacao> subacaoComboBox;
    @FXML public ComboBox<FonteRecurso> fonterecursoComboBox;
    @FXML public ComboBox<NaturezaDespesa> naturezaComboBox;
    @FXML public TextArea descricaoTextArea, justificativaTextArea;
    @FXML public Button fecharButton, salvarButton, editarCredorButton;

    public PrincipalController principalController;

    BancoDados bancoDados = new BancoDados();
    Sap sap = new Sap();
    private Requisicao requisicao;
    private Credor credor;
    RequisicaoDAO requisicaoDAO = new RequisicaoDAO();

    public void setSap(Sap sap) {
        this.sap = sap;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
        preRequisicao();
    }

    public void preRequisicao() {
        numerorequisicaoTextField.setText(String.valueOf(requisicao.getNumeroRequisicao()));
        setorComboBox.setValue(requisicao.getSetorSolicitante());
        datasolicitacaoDatePicker.setValue(requisicao.getDataRequisicao());
        valorrequisicaoTextField.setText(String.valueOf(requisicao.getValorRequisicao()));
        credorComboBox.setValue(requisicao.getCredor());
        subacaoComboBox.setValue(requisicao.getSubacao());
        fonterecursoComboBox.setValue(requisicao.getFonteRecurso());
        naturezaComboBox.setValue(requisicao.getNaturezaDespesa());
        descricaoTextArea.setText(requisicao.getDescricao());
        justificativaTextArea.setText(requisicao.getJustificativa());
    }

    private ObservableList<Subacao> subacaos = FXCollections.observableArrayList();
    private ObservableList<Unidade> unidades = FXCollections.observableArrayList();
    private ObservableList<FonteRecurso> fontesRecurso = FXCollections.observableArrayList();
    private ObservableList<NaturezaDespesa> naturezasDespesa = FXCollections.observableArrayList();
    private ObservableList<Credor> credores = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        subacaoComboBox.setItems(subacaos);
        subacaos.addAll(bancoDados.getSubacao());
        setorComboBox.setItems(unidades);
        unidades.addAll(bancoDados.getUnidade());
        fonterecursoComboBox.setItems(fontesRecurso);
        fontesRecurso.addAll(bancoDados.getFonteRecurso());
        naturezaComboBox.setItems(naturezasDespesa);
        naturezasDespesa.addAll(bancoDados.getNaturezaDespesa());
        credorComboBox.setItems(credores);
        credores.addAll(bancoDados.getCredor());

        datasolicitacaoDatePicker.setValue(LocalDate.now());

        ComboBoxKeyCompleter completer = new ComboBoxKeyCompleter();
        completer.install(setorComboBox);
        completer.install(credorComboBox);
        completer.install(subacaoComboBox);
        completer.install(fonterecursoComboBox);
        completer.install(naturezaComboBox);

        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        salvarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));

    }

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    @FXML
    public void fecharRequisicao() {
        fecharButton.getScene().getWindow().hide();
    }

    NumberFormat nf = NumberFormat.getCurrencyInstance();

    @FXML
    public void salvarRequisicao() {

        if (!validarDadosReq()) {
            exibirMensagemErro("Por favor, preencha todos os dados!");
            return;
        }

        boolean editando = true;

        if (requisicao == null) {
            requisicao = new Requisicao();
            editando = false;
        }

        requisicao.setSubacao(subacaoComboBox.getValue());
        requisicao.setSetorSolicitante(setorComboBox.getValue());
        requisicao.setFonteRecurso(fonterecursoComboBox.getValue());
        requisicao.setNaturezaDespesa(naturezaComboBox.getValue());
        requisicao.setCredor(credorComboBox.getValue());
        requisicao.setValorRequisicao(Float.parseFloat(valorrequisicaoTextField.getText()));
        requisicao.setDataRequisicao(datasolicitacaoDatePicker.getValue());
        requisicao.setDescricao(descricaoTextArea.getText());
        requisicao.setJustificativa(justificativaTextArea.getText());

        if (editando == false) {
            principalController.adicionarRequisicao(requisicao);
            requisicao = null;
            salvarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else {
            principalController.atualizarItensRequisicao();
            requisicaoDAO.atualizarRequisicao(requisicao);
            salvarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }
    }

    public boolean validarDadosReq() {

        if (credorComboBox.getValue() == null) {
            return false;
        }

        if (datasolicitacaoDatePicker.getValue() == null) {
            return false;
        }

        if (valorrequisicaoTextField.getText() == null || valorrequisicaoTextField.getText().isEmpty()) {
            return false;
        }

        if (credorComboBox.getValue() == null) {
            return false;
        }

        if (subacaoComboBox.getValue() == null) {
            return false;
        }

        if (fonterecursoComboBox.getValue() == null) {
            return false;
        }

        if (naturezaComboBox.getValue() == null) {
            return false;
        }

        if (descricaoTextArea.getText() == null || descricaoTextArea.getText().isEmpty()) {
            return false;
        }

        if (justificativaTextArea.getText() == null || justificativaTextArea.getText().isEmpty()) {
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

    @FXML
    public void editandoCredor() throws IOException {
        /*System.out.println("teste");*/
        Stage credorStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarCredorJanela.fxml"));
        Parent root = loader.load();

        CredorController credorControl = loader.getController();
        credorControl.setRequisicaoController(this);

        if (credorComboBox.getValue() == null) {
            credorSelected();
        } else if (credorComboBox.getValue() != null){
            credorControl.setCredor(credorComboBox.getValue());
            credorControl.botaoSalvar(true);
            credorStage.setTitle("EDITAR CREDOR");
            credorStage.setScene(new Scene(root));
            credorStage.setResizable(false);
            credorStage.show();
        }


    }

    public void notificandoCadastro() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Requisição Cadastrada")
                .text("Requisição cadastrada com sucesso")
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
                .title("Requisição Atualizada")
                .text("Requisição atualizada com sucesso")
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

    public void credorSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro de Sistema");
        alert.setHeaderText("Nenhum credor foi selecionado.");
        alert.setContentText("É necessário selecionar um credor para abrir a janela de informações.");
        alert.showAndWait();
    }
}