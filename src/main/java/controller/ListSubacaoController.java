package controller;

import DAO.BancoDados;
import DAO.SubacaoDAO;
import javafx.beans.property.SimpleObjectProperty;
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
import filter.FiltroSUBACAO;
import model.Subacao;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ListSubacaoController {

    @FXML public TableView<Subacao> subacoesTableView;
    @FXML public TableColumn<Subacao, String> subacaoTableColumn, descricaoTableColumn;
    @FXML public Button cadastrarButton, editarButton, excluirButton, filtroButton;
    @FXML public TextField filtroTextField;
    @FXML public ComboBox<FiltroSUBACAO> filtroComboBox;

    public PrincipalController principalController;
    BancoDados bancoDados = new BancoDados();
    SubacaoDAO subacaoDAO = new SubacaoDAO();

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    @FXML
    public void initialize() {

        subacaoTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getSubacao());
        });

        descricaoTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getDescricao());
        });

        Image create = new Image(getClass().getResourceAsStream("create.png"));
        Image delete = new Image(getClass().getResourceAsStream("delete.png"));

        cadastrarButton.setGraphic(new ImageView(create));
        editarButton.setGraphic(new ImageView(create));
        excluirButton.setGraphic(new ImageView(delete));

        doubleClick();

        filtroComboBox.getItems().addAll(FiltroSUBACAO.values());
        filtroComboBox.getSelectionModel().select(FiltroSUBACAO.SUBACAO);
        atualizarTabela();
        atualizarDados();
    }

    public void atualizarTabela() {
        List<Subacao> subac = bancoDados.getSubacao();
        subacoesTableView.getItems().clear();
        subacoesTableView.getItems().addAll(subac);
    }

    @FXML public void cadastrarSubacao() throws IOException {
        principalController.CadastrarSubacao();
    }

    public void atualizarDados() {
        subacoesTableView.refresh();
    }

    @FXML public void editarSubacao() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarSubacaoJanela.fxml"));
        Parent root = loader.load();

        SubacaoController controller = loader.getController();
        controller.setListSubacaoController(this);
        controller.setSubacao(
                         subacoesTableView
                        .getSelectionModel()
                        .getSelectedItem());
        secondStage.setTitle("EDITAR USUÁRIO");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML public void excluirSubacao() {
            ObservableList<Subacao> subacaoSelected, todasSubacoes;
            todasSubacoes = subacoesTableView.getItems();
            subacaoSelected = subacoesTableView.getSelectionModel().getSelectedItems();

            subacaoSelected.forEach(bancoDados::excluirSubacao);
            todasSubacoes.removeAll(subacaoSelected);
            notificandoExcluir();
    }

    public void notificandoExcluir() {
        Image img = new Image(getClass().getResourceAsStream("cross.png"));
        Notifications notifications = Notifications.create()
                .title("Subação Excluida")
                .text("Subação excluida com sucesso")
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

    @FXML public void filtrarSubacao() {

        String filtro = filtroTextField.getText();

        FiltroSUBACAO  filtroSUBACAO = filtroComboBox.getValue();

        List<Subacao> listaFiltro = subacaoDAO.filtrandoSubacao(filtroSUBACAO, filtro);

        subacoesTableView.getItems().clear();
        subacoesTableView.getItems().addAll(listaFiltro);

        filtroTextField.clear();
    }

    public void doubleClick() {
        subacoesTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarSubacao();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
    }

}
