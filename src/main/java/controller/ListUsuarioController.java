package controller;

import DAO.BancoDados;
import DAO.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import filter.FiltroUSUARIO;
import model.Usuario;

import java.io.IOException;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ListUsuarioController {

    @FXML public TableView<Usuario> usuariosTableView;
    @FXML public TableColumn<Usuario, String> nomeTableColumn, matriculaTableColumn, setorTableColumn;
    @FXML public Button cadastrarButton, editarButton, excluirButton, filtroButton;
    @FXML public TextField filtroTextField;
    @FXML public ComboBox<FiltroUSUARIO> filtroComboBox;

    private PrincipalController principalController;
    public UsuarioController usuarioController;

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    BancoDados bancoDados = new BancoDados();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {

        nomeTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getNome());
        });

        matriculaTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getMatricula());
        });

        setorTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getSetor().getNome());
        });

        Image create = new Image(getClass().getResourceAsStream("create.png"));
        Image delete = new Image(getClass().getResourceAsStream("delete.png"));

        cadastrarButton.setGraphic(new ImageView(create));
        editarButton.setGraphic(new ImageView(create));
        excluirButton.setGraphic(new ImageView(delete));

        atualizarTabela();
        doubleClick();

        filtroComboBox.getItems().addAll(FiltroUSUARIO.values());
        filtroComboBox.getSelectionModel().select(FiltroUSUARIO.MATRICULA);
        usuariosTableView.refresh();

    }

    public void atualizarTabela() {
        List<Usuario> usuarios = bancoDados.getUsuario();
        usuariosTableView.getItems().clear();
        usuariosTableView.getItems().addAll(usuarios);
    }

    @FXML
    public void cadastrarUsuario() throws IOException {
        principalController.CadastrarUsuario();
    }

    @FXML
    public void editarUsuario() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarUsuarioJanela.fxml"));
        Parent root = loader.load();

        UsuarioController controller = loader.getController();
        controller.setUsuarioController(this);
        controller.setUsuario(
                         usuariosTableView
                        .getSelectionModel()
                        .getSelectedItem());
        secondStage.setTitle("EDITAR USUÁRIO");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML
    public void excluirUsuario() {
        ObservableList<Usuario> usuarioSelected, todosUsuarios;
        todosUsuarios = usuariosTableView.getItems();
        usuarioSelected = usuariosTableView.getSelectionModel().getSelectedItems();

        usuarioSelected.forEach(bancoDados::excluirUsuario);
        todosUsuarios.removeAll(usuarioSelected);
    }

    @FXML
    public void filtrarUsuario() {

        String filtro = filtroTextField.getText();

        FiltroUSUARIO filtroUSUARIO = filtroComboBox.getValue();

        List<Usuario> listaUsuarios = usuarioDAO.filtrarUsuarios(filtroUSUARIO, filtro);

        usuariosTableView.getItems().clear();
        usuariosTableView.getItems().addAll(listaUsuarios);

        filtroTextField.clear();

    }

    public void doubleClick() {
        usuariosTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarUsuario();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
    }

}
