package controller;

import DAO.BancoDados;
import DAO.NaturezaDAO;
import javafx.beans.property.SimpleObjectProperty;
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
import filter.FiltroNATUREZA;
import model.NaturezaDespesa;

import java.io.IOException;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ListNaturezaController {

    BancoDados bancoDados = new BancoDados();

    @FXML public Button cadastrarButton, editarButton, excluirButton, filtroButton;
    @FXML public TableView<NaturezaDespesa> naturezaTableView;
    @FXML public TableColumn<NaturezaDespesa, String> codigoTableColumn, descricaoTableColumn, grupoTableColumn;
    @FXML public ComboBox<FiltroNATUREZA> filtroComboBox;
    @FXML public TextField filtroTextField;

    public PrincipalController principalController;
    public NaturezaDespesaController naturezaDespesaController;
    NaturezaDAO naturezaDAO = new NaturezaDAO();


    public void setListCredorController(PrincipalController listCredorController) {
        this.principalController = listCredorController;
    }

    @FXML public void initialize() {

        codigoTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getCodDespesa());
        });

        descricaoTableColumn.setCellValueFactory(param -> {
            return new SimpleStringProperty(param.getValue().getDescricao());
        });

        grupoTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getGrupoFinanceiro().getDescricao());
        });

        Image create = new Image(getClass().getResourceAsStream("create.png"));
        Image delete = new Image(getClass().getResourceAsStream("delete.png"));

        cadastrarButton.setGraphic(new ImageView(create));
        editarButton.setGraphic(new ImageView(create));
        excluirButton.setGraphic(new ImageView(delete));

        doubleClick();

        filtroComboBox.getItems().addAll(FiltroNATUREZA.values());
        filtroComboBox.getSelectionModel().select(FiltroNATUREZA.CODIGO);
        atualizarTabela();

    }

    @FXML public void cadastrarNatureza() throws IOException {
        principalController.CadastrarNaturezaDespesa();
    }

    @FXML public void editarNatureza() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarNaturezaDespesaJanela.fxml"));
        Parent root = loader.load();

        NaturezaDespesaController controller = loader.getController();
        controller.setListNaturezaController(this);
        controller.setNatureza(
                naturezaTableView.getSelectionModel().getSelectedItem());
        secondStage.setTitle("Editar Natureza da Despesa");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML public void excluirNatureza() {
        ObservableList<NaturezaDespesa> naturezaSelected, todasNaturezas;
        todasNaturezas = naturezaTableView.getItems();
        naturezaSelected = naturezaTableView.getSelectionModel().getSelectedItems();

        naturezaSelected.forEach(bancoDados::excluirNatureza);
        todasNaturezas.removeAll(naturezaSelected);
    }

    public void atualizarTabela() {
        List<NaturezaDespesa> natureza = bancoDados.getNaturezaDespesa();
        naturezaTableView.getItems().clear();
        naturezaTableView.getItems().addAll(natureza);
    }

    @FXML public void filtrarNatureza() {

        String filto = filtroTextField.getText();

        FiltroNATUREZA filtroNATUREZA = filtroComboBox.getValue();

        List<NaturezaDespesa> listaFilto = naturezaDAO.filtrarNatureza(filtroNATUREZA, filto);

        naturezaTableView.getItems().clear();
        naturezaTableView.getItems().addAll(listaFilto);

        filtroTextField.clear();

    }

    public void doubleClick() {
        naturezaTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarNatureza();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
    }


}
