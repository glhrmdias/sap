package controller;

import DAO.BancoDados;
import DAO.UsuarioDAO;
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
import model.Unidade;
import model.Usuario;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;
import util.ComboBoxKeyCompleter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class UsuarioController {

    @FXML public ComboBox<Unidade> setorComboBox;
    @FXML public TextField nomeTextField, matriculaTextField;
    @FXML public PasswordField senhaTextField;
    @FXML public Button fecharButton, cadastrarButton;

    public PrincipalController principalController;
    public ListUsuarioController usuController;
    public ListUsuarioController listUsuarioController;
    private Usuario usuario;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void setListUsuarioController (ListUsuarioController listusucontroller) {
        listUsuarioController = listusucontroller;
    }

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }
    public void setUsuarioController (ListUsuarioController usuarioController) {
        usuController = usuarioController;
    }

    BancoDados bancoDados = new BancoDados();

    private ObservableList<Unidade> unidades = FXCollections.observableArrayList();

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
        preCadastro();
    }

    public void preCadastro() {
        nomeTextField.setText(usuario.getNome());
        matriculaTextField.setText(usuario.getMatricula());
        setorComboBox.setValue(usuario.getSetor());
        senhaTextField.setText(usuario.getSenha());
    }

    @FXML
    public void initialize() {
        setorComboBox.setItems(unidades);
        unidades.addAll(bancoDados.getUnidade());

        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        cadastrarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));

        ComboBoxKeyCompleter completer = new ComboBoxKeyCompleter();
        completer.install(setorComboBox);
    }

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void cadastrarUsuario() {

        if (!validarDados()) {
            exibirMensagem("Existem campos em branco, por favor, preencher todos os campos!");
            return;
        }

        boolean editando = true;

        if (usuario == null) {
            usuario = new Usuario();
            editando = false;
        }

        usuario.setNome(nomeTextField.getText());
        usuario.setSenha(createEncryptedPassword(senhaTextField.getText()));
        usuario.setMatricula(matriculaTextField.getText());
        usuario.setSetor(setorComboBox.getValue());

        if (editando == false){
            principalController.adicionarUsuario(usuario);
            usuario = null;
            cadastrarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else if (editando == true) {
            //usuController.atualizarTabela();
            usuarioDAO.atualizandoUsuario(usuario);
            cadastrarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }

        System.out.println(usuario);

    }

    private static String createEncryptedPassword(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    public void notificandoCadastro() {
        Image img = new Image(getClass().getResourceAsStream("check.png"));
        Notifications notifications = Notifications.create()
                .title("Usuário Cadastrado")
                .text("Usuário cadastrado com sucesso")
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
                .title("Usuário Atualizado")
                .text("Usuário atualizado com sucesso")
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

    public boolean validarDados() {

        if (nomeTextField.getText() == null || nomeTextField.getText().isEmpty()) {
            return false;
        }

        if (matriculaTextField.getText() == null || matriculaTextField.getText().isEmpty()) {
            return false;
        }

        if (senhaTextField.getText() == null || senhaTextField.getText().isEmpty()) {
            return false;
        }

        if (setorComboBox.getValue() == null) {
            return false;
        }

        return true;
    }

    public void exibirMensagem(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario.getMatricula() != null) {
            usuarioDAO.cadastrandoUsuario(usuario);
        } else {
            usuarioDAO.cadastrandoUsuario(usuario);
        }
    }


}
