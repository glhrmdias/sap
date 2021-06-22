package controller;

import DAO.*;
import filter.FiltroSAP;
import javafx.beans.property.SimpleObjectProperty;
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
import model.*;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class PrincipalController {

    @FXML public Button emitirSapButton, emitirRequisicaoButton, imprimirButton, filtrarButton;
    @FXML public TableView<Sap> dadosTableView;
    @FXML public TableColumn<Sap, String> numeroTableColumn, dataTableColumn, credorTableColumn,
            naturezaTableColumn, valorTableColumn;
    @FXML public TableColumn<Sap, Unidade> setorTableColumn;
    @FXML public TableView<Requisicao> requisicaoTableView;
    @FXML public TableColumn<Requisicao, String> numeroReqTableColumn, setorReqTableColumn,
            dataReqTableColumn, naturezaReqTableColumn, valorReqTableColumn ;
    @FXML public TableColumn<Requisicao, Unidade> credorReqTableColumn;
    @FXML public TableColumn numeracaoTableColumn, numeracaoRequisicaoTableColumn;
    @FXML public MenuItem menuItemUsuario, menuItemCredor, excluirSapMenuItem, excluirRequisicaoMenuItem,
            menuItemSubacao, editarSapMenuItem, editarRequisicaoMenuItem, sobreMenuItem,
            usuariosMenuItem, credorMenuItem, naturezaMenuItem, cadastrarNaturezaMenuItem;

    @FXML public ComboBox filtroComboBox;

    @FXML public TextField filtroTextField;

    Sap sap = new Sap();
    public ListUsuarioController usuController;
    public ListCredorController credController;
    public ListSubacaoController subController;
    public ListNaturezaController natlistController;


    public NaturezaDespesaController natController;

    private CredorDAO credorDAO = new CredorDAO();

    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    NumberFormat nf = NumberFormat.getCurrencyInstance();
    BancoDados bancoDados = new BancoDados();
    SapDAO sapDAO = new SapDAO();
    NaturezaDAO naturezaDAO = new NaturezaDAO();
    RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
    SubacaoDAO subacaoDAO = new SubacaoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML public void initialize() {

   numeracaoTableColumn.setCellFactory(column -> new TableCell<Sap, String>() {
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                String index = empty ? null : getIndex() + 1 + "";
                getStyleClass().addAll("column-header");

                setGraphic(null);
                setText(index);
            }
        });

        numeracaoTableColumn.setMaxWidth(35);
        numeracaoTableColumn.setMinWidth(35);

        numeracaoRequisicaoTableColumn.setCellFactory(column -> new TableCell<Sap, String>() {
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                String index = empty ? null : getIndex() + 1 + "";
                getStyleClass().addAll("column-header");

                setGraphic(null);
                setText(index);
            }
        });

        numeracaoRequisicaoTableColumn.setMaxWidth(35);
        numeracaoRequisicaoTableColumn.setMinWidth(35);

        credorTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getCredor());
        });

        setorTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getSetorSolicitante());
        });

        dataTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(dateTimeFormatter.format(param.getValue().getDatasolicitacao()));
        });

        naturezaTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getNaturezaDespesa());
        });

        valorTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(nf.format(param.getValue().getValor()));
        });

        credorReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getCredor());
        });

        setorReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getSetorSolicitante());
        });

        dataReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(dateTimeFormatter.format(param.getValue().getDataRequisicao()));
        });

        naturezaReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getNaturezaDespesa());
        });

        valorReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(nf.format(param.getValue().getValorRequisicao()));
        });

        numeroTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getNumeroSap());
        });

        numeroReqTableColumn.setCellValueFactory(param -> {
            return new SimpleObjectProperty(param.getValue().getNumeroRequisicao());
        });

        Image print = new Image(getClass().getResourceAsStream("print.png"));
        Image create = new Image(getClass().getResourceAsStream("create.png"));
        Image delete = new Image(getClass().getResourceAsStream("delete.png"));

        emitirSapButton.setGraphic(new ImageView(create));
        emitirRequisicaoButton.setGraphic(new ImageView(create));
        imprimirButton.setGraphic(new ImageView(print));

        atualizarTabelaSap();
        atualizarTabelaRequisicao();

        dadosTableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        requisicaoTableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);

        doubleClickSap();
        doubleClickReq();
        filtroComboBox.getItems().addAll(FiltroSAP.values());
        filtroComboBox.getSelectionModel().select(FiltroSAP.SETOR);

    }

    @FXML public void emitirSap() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaSap.fxml"));
        Parent root = loader.load();

        SapController controller = loader.getController();
        controller.setPrincipalController(this);
        //Parent root = FXMLLoader.load(getClass().getResource("janelaSap.fxml"));
        secondStage.setTitle("SOLICITAÇÃO DE SAP");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML public void emitirRequisicao() throws IOException {
        Stage requisicaoStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaRequisicao.fxml"));
        Parent root = loader.load();

        RequisicaoController controller = loader.getController();
        controller.setPrincipalController(this);
        requisicaoStage.setTitle("SOLICITAÇÃO DE REQUISIÇÃO");
        requisicaoStage.setScene(new Scene(root));
        requisicaoStage.setResizable(false);
        requisicaoStage.show();
    }

    @FXML public void adicionarSap(Sap sap) {

        if (sap.getCodSap() == 0) {
            dadosTableView.getItems().add(sap);
            sapDAO.inserindoSap(sap);
            atualizarTabelaSap();
        } else {

        }

    }

    public void adicionarSubacao(Subacao subacao) {

        if (subacao.getCodSubacao() == 0) {
            subacaoDAO.cadastrandoSubacao(subacao);
            subController.atualizarTabela();
        } else {
            subacaoDAO.atualizandoSubacao(subacao);
        }

    }

    public void adicionarNatureza(NaturezaDespesa naturezaDespesa) {

        if (naturezaDespesa.getCodDespesa() != null) {
            naturezaDAO.cadastrandoNatureza(naturezaDespesa);
            natlistController.atualizarTabela();
        } else {
            naturezaDAO.atualizandoNatureza(naturezaDespesa);
        }
    }

    public void atualizarTabelaSap() {
        List<Sap> saps = bancoDados.listarSaps();
        dadosTableView.getItems().clear();
        dadosTableView.getItems().addAll(saps);
    }

    public void atualizarItensSap() {
        dadosTableView.refresh();
    }

    public void atualizarItensRequisicao() {
        requisicaoTableView.refresh();
    }

    public void atualizarTabelaRequisicao() {
        List<Requisicao> requisicoes = bancoDados.listarRequisicao();
        requisicaoTableView.getItems().clear();
        requisicaoTableView.getItems().addAll(requisicoes);
    }

    @FXML public void adicionarRequisicao (Requisicao requisicao) {
        if (requisicao.getCodRequisicao() == 0) {
            requisicaoDAO.inserindoRequisicao(requisicao);
            atualizarTabelaRequisicao();
        } else {
        }

    }

    @FXML public void CadastrarCredor() throws IOException {
        Stage credorStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarCredorJanela.fxml"));
        Parent root = loader.load();

        CredorController controller = loader.getController();
        controller.setPrincipalController(this);
        credorStage.setTitle("CADASTRAR CREDOR");
        credorStage.setScene(new Scene(root));
        credorStage.setResizable(false);
        credorStage.show();
    }

    @FXML public void CadastrarSubacao() throws IOException {
        Stage subacaoController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarSubacaoJanela.fxml"));
        Parent root = loader.load();

        SubacaoController controller = loader.getController();
        controller.setPrincipalController(this);
        subacaoController.setTitle("CADASTRAR SUBAÇÃO");
        subacaoController.setScene(new Scene(root));
        subacaoController.setResizable(false);
        subacaoController.show();
    }

    @FXML public List<Sap> pegarSaps() {
        return dadosTableView.getItems();
    }

    @FXML public List<Requisicao> pegarRequisicao() {
        return  requisicaoTableView.getItems();
    }

    @FXML public void CadastrarNaturezaDespesa() throws IOException {
        Stage naturezaDespesaController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarNaturezaDespesaJanela.fxml"));
        Parent root = loader.load();

        NaturezaDespesaController controller = loader.getController();
        controller.setPrincipalController(this);
        naturezaDespesaController.setTitle("CADASTRAR NATUREZA DE DESPESA");
        naturezaDespesaController.setScene(new Scene(root));
        naturezaDespesaController.show();
    }

    @FXML public void CadastrarUsuario() throws IOException {
        Stage usuarioController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarUsuarioJanela.fxml"));
        Parent root = loader.load();

        UsuarioController controller = loader.getController();
        controller.setPrincipalController(this);
        usuarioController.setTitle("CADASTRAR USUÁRIO");
        usuarioController.setScene(new Scene(root));
        usuarioController.setResizable(false);
        usuarioController.show();
    }

    @FXML public void CadastrarFonteRecurso() throws IOException {
        Stage fonteRecursoController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarFonteRecursoJanela.fxml"));
        Parent root = loader.load();

        FonteRecursoController controller = loader.getController();
        controller.setPrincipalController(this);
        fonteRecursoController.setTitle("CADASTRAR FONTE DE RERCURSO");
        fonteRecursoController.setScene(new Scene(root));
        fonteRecursoController.show();
    }

    @FXML public void excluirSap() {
        ObservableList<Sap> sapSelected, todasSaps;
        todasSaps = dadosTableView.getItems();
        sapSelected = dadosTableView.getSelectionModel().getSelectedItems();

        sapSelected.forEach(bancoDados::excluirSap);
        todasSaps.removeAll(sapSelected);
        excluindoSap();
    }

    @FXML public void excluirRequisicao() {
        ObservableList<Requisicao> requisicaoSelected, todasRequisicoes;
        todasRequisicoes = requisicaoTableView.getItems();
        requisicaoSelected = requisicaoTableView.getSelectionModel().getSelectedItems();

        requisicaoSelected.forEach(bancoDados::excluirRequisicao);
        todasRequisicoes.removeAll(requisicaoSelected);
        excluindoRequisicao();
    }

    @FXML public void imprimirDocumento() throws IOException {
        Stage imprimirController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaImprimir.fxml"));
        Parent root = loader.load();

        ImprimirController controller = loader.getController();
        controller.setPrincipalController(this);
        imprimirController.setTitle("IMPRIMIR DOCUMENTO");
        imprimirController.setScene(new Scene(root));
        imprimirController.setResizable(false);
        imprimirController.show();
    }

    @FXML public void editarSap() throws IOException {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaSap.fxml"));
        Parent root = loader.load();

        SapController controller = loader.getController();
        controller.setPrincipalController(this);
        controller.setSap(dadosTableView.getSelectionModel().getSelectedItem());
        secondStage.setTitle("EDITAR SAP");
        secondStage.setScene(new Scene(root));
        secondStage.setResizable(false);
        secondStage.show();
    }

    @FXML public void editarRequisicao() throws IOException {
        Stage requisicaoStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaRequisicao.fxml"));
        Parent root = loader.load();

        RequisicaoController requisicaoController = loader.getController();
        requisicaoController.setPrincipalController(this);
        requisicaoController.setRequisicao(requisicaoTableView.getSelectionModel().getSelectedItem());
        requisicaoStage.setTitle("EDITAR REQUISIÇÃO");
        requisicaoStage.setScene(new Scene(root));
        requisicaoStage.setResizable(false);
        requisicaoStage.show();
    }

    @FXML public void sobreSistema() {

        String versao = ".0.0.7";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre o sistema");
        alert.setHeaderText("Sistema de Pagamento -- IPREV 2017");
        alert.setContentText(
                        "Versão do sistema v" + versao + "\n"  +
                        "Desenvolvido por: Guilherme Humberto Dias\n" +
                        "Instituto de Previdência do Estado de Santa Catarina");
        alert.showAndWait();
    }

    ListCredorController listCredorController = new ListCredorController();

    public void adicionarCredor(Credor credor) {
        if (credor.getIdentificacao() != null) {
            credorDAO.inserindoCredor(credor);
            credController.atualizarItens();
        } else {
            credorDAO.atualizandoCredores(credor);

        }
    }

    public void excluindoSap() {
        Image img = new Image(getClass().getResourceAsStream("delete.png"));
        Notifications notifications = Notifications.create()
                .title("SAP Excluida")
                .text("SAP removida com sucesso")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //System.out.println("Notificação cadastro");
                    }
                });
        notifications.show();
    }

    public void excluindoRequisicao() {
        Image img = new Image(getClass().getResourceAsStream("delete.png"));
        Notifications notifications = Notifications.create()
                .title("Requisição Excluida")
                .text("Requisição removida com sucesso")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //System.out.println("Notificação cadastro");
                    }
                });
        notifications.show();
    }

    public void listarUsuarios() throws IOException {
        Stage listUsuariosController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaUsuarios.fxml"));
        Parent root = loader.load();

        usuController = loader.getController();
        usuController.setPrincipalController(this);
        listUsuariosController.setTitle("Usuários");
        listUsuariosController.setScene(new Scene(root));
        listUsuariosController.setResizable(false);
        listUsuariosController.show();

    }

    public void listarSubacoes() throws IOException {
        Stage subacoesStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaSubacoes.fxml"));
        Parent root = loader.load();

        subController = loader.getController();
        subController.setPrincipalController(this);
        subacoesStage.setTitle("Subações");
        subacoesStage.setScene(new Scene(root));
        subacoesStage.setResizable(false);
        subacoesStage.show();

    }

    public void listarCredor() throws IOException {
        Stage subacoesStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaCredor.fxml"));
        Parent root = loader.load();

        credController = loader.getController();
        credController.setListCredorController(this);
        subacoesStage.setTitle("Credores");
        subacoesStage.setScene(new Scene(root));
        subacoesStage.setResizable(false);
        subacoesStage.show();
    }

    public void listarNatureza() throws IOException {
        Stage subacoesStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("janelaNatureza.fxml"));
        Parent root = loader.load();

        natlistController = loader.getController();
        natlistController.setListCredorController(this);
        subacoesStage.setTitle("Natureza Despesa");
        subacoesStage.setScene(new Scene(root));
        subacoesStage.setResizable(false);
        subacoesStage.show();
    }

    @FXML public void filtrarSaps() {

        String filtro = filtroTextField.getText();

        FiltroSAP filtroSAP = (FiltroSAP) filtroComboBox.getValue();

        List<Sap> listaSaps =  sapDAO.filtrarSap(filtroSAP, filtro);

        filtroTextField.clear();

        dadosTableView.getItems().clear();
        dadosTableView.getItems().addAll(listaSaps);

    }

    public void doubleClickSap() {
        dadosTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarSap();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

    }

    public void doubleClickReq() {
        requisicaoTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            editarRequisicao();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    @FXML public void addNatureza() throws IOException {
        Stage naturezaDespesaController = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarNaturezaDespesaJanela.fxml"));
        Parent root = loader.load();

        natController = loader.getController();
        natController.setPrincipalController(this);
        naturezaDespesaController.setTitle("CADASTRAR NATUREZA DE DESPESA");
        naturezaDespesaController.setScene(new Scene(root));
        naturezaDespesaController.show();
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario.getMatricula() != null) {
            usuarioDAO.cadastrandoUsuario(usuario);
            usuController.atualizarTabela();
            //atualizarTabela();
        } else {
            usuarioDAO.cadastrandoUsuario(usuario);
            /*listUsuarioController.atualizarTabela();*/
        }

    }
}
