package controller;

import DAO.BancoDados;
import DAO.CredorDAO;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Credor;
import filter.FiltroCREDOR;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ListCredorController {

    @FXML public TableView<Credor> credorTableView;
    @FXML public TableColumn<Credor, String> identificacaoTableColumn, nomeTableColumn;
    @FXML public Button cadastrarButton, editarButton, excluirButton, filtroButton;
    @FXML public TextField filtroTextField;
    @FXML public ComboBox<FiltroCREDOR> filtroComboBox;

    public PrincipalController principalController;
    public ListCredorController credorControl;
    private Credor credor;
    BancoDados bancoDados = new BancoDados();
    CredorDAO credorDAO = new CredorDAO();

    public void setListCredorController(PrincipalController listCredorController) {
        this.principalController = listCredorController;
    }

    @FXML
    public void initialize() {

        identificacaoTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getIdentificacao());
        });

        nomeTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getNomeCredor());
        });

        Image create = new Image(getClass().getResourceAsStream("create.png"));
        Image delete = new Image(getClass().getResourceAsStream("delete.png"));

        cadastrarButton.setGraphic(new ImageView(create));
        editarButton.setGraphic(new ImageView(create));
        excluirButton.setGraphic(new ImageView(delete));

        atualizarItens();
        doubleClick();

        filtroComboBox.getItems().addAll(FiltroCREDOR.values());
        filtroComboBox.getSelectionModel().select(FiltroCREDOR.CNPJ);
    }

    public void atualizarItens() {
        List<Credor> credor = bancoDados.getCredor();
        credorTableView.getItems().clear();
        credorTableView.getItems().addAll(credor);
    }

    @FXML
    public void cadastrarCredor() throws IOException {
        principalController.CadastrarCredor();
    }

    @FXML
    public void editarCredor() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarCredorJanela.fxml"));
        Parent root = loader.load();

        CredorController controller = loader.getController();
        controller.setCredorController(this);
        controller.setCredor(
                        credorTableView
                        .getSelectionModel()
                        .getSelectedItem());
        secondStage.setTitle("EDITAR CREDOR");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML
    public void excluirCredor() {
        ObservableList<Credor> credorSelecionado, todosCredores;
        todosCredores = credorTableView.getItems();
        credorSelecionado = credorTableView.getSelectionModel().getSelectedItems();

        credorSelecionado.forEach(bancoDados::excluirCredor);
        todosCredores.remove(credorSelecionado);
        notificandoExcluir();
    }

    public void setCredorController(ListCredorController credorController) {
        this.credorControl = credorController;
    }

    @FXML
    public void filtrarCredores() {
        String filtro = filtroTextField.getText();

        FiltroCREDOR filtroCREDOR = filtroComboBox.getValue();

        List<Credor> filtroCredores = credorDAO.filtrarCredor(filtroCREDOR, filtro);

        credorTableView.getItems().clear();
        credorTableView.getItems().addAll(filtroCredores);

        filtroTextField.clear();

    }

    public void doubleClick() {
        credorTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarCredor();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void notificandoExcluir() {
        Image img = new Image(getClass().getResourceAsStream("cross.png"));
        Notifications notifications = Notifications.create()
                .title("Credor Excluido")
                .text("Credor excluido com sucesso")
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