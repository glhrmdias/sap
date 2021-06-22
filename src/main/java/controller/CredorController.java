package controller;

import DAO.BancoDados;
import DAO.CredorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Credor;
import model.Sap;
import model.TipoCredor;
import org.controlsfx.control.Notifications;
import util.ComboBoxKeyCompleter;
import util.TextFieldFormatter;


import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 *
 */
public class CredorController {

    @FXML public TextField nomeCredorTextField, inscricaoEstadoTextField, inscricaoMunicipioTextField,
            cepTextField, enderecoTextField, bairroTextField, municipioTextField, ufTextField, pontoReferenciaTextField,
            telefoneTextField, telefoneCelularTextField, bancoTextField, agenciaTextField,
            contaTextField, nomeContaTextField, emailTextField, identificacaoTextField;

    @FXML public ComboBox<TipoCredor> tipoCredorComboBox;

    @FXML public Button fecharButton, cadastrarButton;

    public PrincipalController principalController;
    public SapController sapController;
    public RequisicaoController requisicaoController;
    public ListCredorController listCredorController;

    CredorDAO credorDAO = new CredorDAO();

    private Sap sap;
    private Credor cred;
    private Credor credor;

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    public void setCredorController(ListCredorController credorController) {
        this.listCredorController = credorController;
    }

    public void setCredor(Credor cred) {
        this.cred = cred;
        preCadastro();
    }

    public void setSap(Credor sap) {
        this.credor = sap;
        preCadastro();
    }

    public void setSapController(SapController sapControl) {
        sapController = sapControl;
    }

    private ObservableList<TipoCredor> tipoCredores = FXCollections.observableArrayList();
    BancoDados bancoDados = new BancoDados();

    List<Credor> listCredor = bancoDados.getCredor();

    @FXML public void initialize() {
        tipoCredorComboBox.setItems(tipoCredores);
        tipoCredores.addAll(bancoDados.getTipoCredor());

        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        cadastrarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));

        ComboBoxKeyCompleter completer = new ComboBoxKeyCompleter();
        completer.install(tipoCredorComboBox);
    }

    public void preCadastro() {
        tipoCredorComboBox.setValue(cred.getTipoCredor());
        identificacaoTextField.setText(cred.getIdentificacao());
        nomeCredorTextField.setText(cred.getNomeCredor());
        inscricaoEstadoTextField.setText(cred.getInscEstado());
        inscricaoMunicipioTextField.setText(cred.getInscMunicipio());
        cepTextField.setText(cred.getCep());
        enderecoTextField.setText(cred.getEndComercio());
        bairroTextField.setText(cred.getBairro());
        municipioTextField.setText(cred.getMunicipio());
        ufTextField.setText(cred.getUf());
        pontoReferenciaTextField.setText(cred.getPontoReferencia());
        telefoneTextField.setText(cred.getTelefone());
        telefoneCelularTextField.setText(cred.getTelefoneCelular());
        bancoTextField.setText(cred.getBanco());
        agenciaTextField.setText(cred.getAgencia());
        contaTextField.setText(cred.getConta());
        nomeContaTextField.setText(cred.getNomeConta());
        emailTextField.setText(cred.getEmail());
    }

    @FXML
    public void cadastrarCredor() {

        if (!validandoCampos()) {
            exibirMensagemErro("Os dados em vermelhos são obrigatórios, por favor, preencha todos!");
            return;
        }

        boolean editando = true;

        if (cred == null) {
            cred = new Credor();
            editando = false;
        }

        cred.setIdentificacao(identificacaoTextField.getText());
        cred.setTipoCredor(tipoCredorComboBox.getValue());
        cred.setNomeCredor(nomeCredorTextField.getText());
        cred.setInscEstado(inscricaoEstadoTextField.getText());
        cred.setInscMunicipio(inscricaoMunicipioTextField.getText());
        cred.setCep(cepTextField.getText());
        cred.setEndComercio(enderecoTextField.getText());
        cred.setBairro(bairroTextField.getText());
        cred.setMunicipio(municipioTextField.getText());
        cred.setUf(ufTextField.getText());
        cred.setPontoReferencia(pontoReferenciaTextField.getText());
        cred.setTelefone(telefoneTextField.getText());
        cred.setTelefoneCelular(telefoneCelularTextField.getText());
        cred.setBanco(bancoTextField.getText());
        cred.setAgencia(agenciaTextField.getText());
        cred.setConta(contaTextField.getText());
        cred.setNomeConta(nomeContaTextField.getText());
        cred.setEmail(emailTextField.getText());


        if (editando == false){
            principalController.adicionarCredor(cred);
            cred = null;
            cadastrarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else if (editando == true){
            credorDAO.atualizandoCredores(cred);
            credors.addAll(bancoDados.getCredor());
            cadastrarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        } else {
            credorDAO.atualizandoCredores(cred);
            credors.addAll(bancoDados.getCredor());
            cadastrarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }

    }

    ObservableList<Credor> credors = FXCollections.observableArrayList();

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    public boolean validarDados() {

        if (identificacaoTextField.getText() == null || inscricaoEstadoTextField.getText().isEmpty()){
            return false;
        }

        if (tipoCredorComboBox.getValue() == null) {
            return false;
        }

        if (nomeCredorTextField.getText() == null || nomeCredorTextField.getText().isEmpty()) {
            return false;
        }

        if (cepTextField.getText() == null || cepTextField.getText().isEmpty()) {
            return true;
        }

        if (enderecoTextField.getText() == null || enderecoTextField.getText().isEmpty()) {
            return true;
        }

        if (bairroTextField.getText() == null || bairroTextField.getText().isEmpty()) {
            return true;
        }

        if (municipioTextField.getText() == null || municipioTextField.getText().isEmpty()) {
            return true;
        }

        if (ufTextField.getText() == null || ufTextField.getText().isEmpty()) {
            return true;
        }

        if (pontoReferenciaTextField.getText() == null || pontoReferenciaTextField.getText().isEmpty()) {
            return true;
        }

        if (telefoneTextField.getText() == null || telefoneTextField.getText().isEmpty()) {
            return true;
        }

        if (telefoneCelularTextField.getText() == null  || telefoneCelularTextField.getText().isEmpty()){
            return true;
        }

        if (bancoTextField.getText() == null || bancoTextField.getText().isEmpty()) {
            return true;
        }

        if (agenciaTextField.getText() == null || agenciaTextField.getText().isEmpty()) {
            return true;
        }

        if (contaTextField.getText() == null || contaTextField.getText().isEmpty()) {
            return true;
        }

        if (nomeContaTextField.getText() == null || nomeContaTextField.getText().isEmpty()) {
            return true;
        }

        if (emailTextField.getText() == null || emailTextField.getText().isEmpty()) {
            return true;
        }

        if (inscricaoEstadoTextField.getText() == null ) {
            return true;
        }

        if (inscricaoMunicipioTextField.getText() == null ) {
            return true;
        }

        return true;
    }

    public boolean validandoCampos() {

        if (identificacaoTextField.getText() == null || identificacaoTextField.getText().isEmpty()){
            return false;
        }

        if (tipoCredorComboBox.getValue() == null) {
            return false;
        }

        if (nomeCredorTextField.getText() == null || nomeCredorTextField.getText().isEmpty()) {
            return false;
        }

        if (inscricaoMunicipioTextField.getText() == null) {
            return true;
        }

        if (inscricaoEstadoTextField.getText() == null) {
            return true;
        }

        if (cepTextField.getText() == null || cepTextField.getText().isEmpty()) {
            return true;
        }

        if (enderecoTextField.getText() == null || enderecoTextField.getText().isEmpty()) {
            return true;
        }

        if (bairroTextField.getText() == null || bairroTextField.getText().isEmpty()) {
            return true;
        }

        if (municipioTextField.getText() == null || municipioTextField.getText().isEmpty()) {
            return true;
        }

        if (ufTextField.getText() == null || ufTextField.getText().isEmpty()) {
            return true;
        }

        if (pontoReferenciaTextField.getText() == null || pontoReferenciaTextField.getText().isEmpty()) {
            return true;
        }

        if (telefoneTextField.getText() == null || telefoneTextField.getText().isEmpty()) {
            return true;
        }

        if (telefoneCelularTextField.getText() == null  || telefoneCelularTextField.getText().isEmpty()){
            return true;
        }

        if (bancoTextField.getText() == null || bancoTextField.getText().isEmpty()) {
            return true;
        }

        if (agenciaTextField.getText() == null || agenciaTextField.getText().isEmpty()) {
            return true;
        }

        if (contaTextField.getText() == null || contaTextField.getText().isEmpty()) {
            return true;
        }

        if (nomeContaTextField.getText() == null || nomeContaTextField.getText().isEmpty()) {
            return true;
        }

        if (emailTextField.getText() == null || emailTextField.getText().isEmpty()) {
            return true;
        }

        return true;
    }

    private void exibirMensagemErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void setRequisicaoController(RequisicaoController requisicaoControl) {
        requisicaoController = requisicaoControl;
    }

    public void notificandoCadastro() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Credor Cadastrado")
                .text("Credor cadastrado com sucesso")
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

    public void notificandoAtualizacao() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Credor Atualizado")
                .text("Credoratualizado com sucesso")
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

    public boolean botaoSalvar(boolean botao) {

        cadastrarButton.setDisable(true);
        return true;
    }

    @FXML
    public void mascara() {
        /*TextFieldFormatter cpfFormat = new TextFieldFormatter();
        cpfFormat.setMask("##########################");
        cpfFormat.setCaracteresValidos("0123456789");
        cpfFormat.setTf(identificacaoTextField);
        cpfFormat.formatter();*/
    }

}
