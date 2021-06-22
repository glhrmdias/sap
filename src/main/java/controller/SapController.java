package controller;

import DAO.BancoDados;
import DAO.SapDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import model.Sap;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.ComboBoxKeyCompleter;

import javax.swing.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */


public class SapController {

    @FXML public ComboBox gestaoComboBox;
    @FXML public ComboBox<Requisicao> requisicaoComboBox;
    @FXML public ComboBox<Unidade> setorComboBox;
    @FXML public ComboBox<ModalidadeEmpenho> emprenhoComboBox;
    @FXML public ComboBox<UnidadeGestora> unidadegestoraComboBox;
    @FXML public ComboBox<Evento> eventoComboBox;
    @FXML public ComboBox<Credor> credorComboBox;
    @FXML public ComboBox<UnidadeOrcamentaria> unidadeorcamentariaComboBox;
    @FXML public ComboBox<Subacao> subacaoComboBox;
    @FXML public ComboBox<FonteRecurso> recursoComboBox;
    @FXML public ComboBox<NaturezaDespesa> naturezaComboBox;
    @FXML public ComboBox<ModalidadeLicitacao> modlicComboBox;
    @FXML public ComboBox<TipoContrato> tipocontratoComboBox;
    @FXML public ComboBox<Instrumento> instrumentoComboBox;
    @FXML public TextArea historicoTextArea;
    @FXML public ComboBox<ParteAdvogado> parteadvogadoComboBox;
    @FXML public DatePicker datasolicitacaoDatePicker, datamandadoDatePicker, dataintiprevDatePicker, datavencimentoDatePicker;
    @FXML public TextField nomeparteTextField, prazopagamentoTextField, valorTextField, processoTextField, numeroautosTextField, numeroSapTextField, janeiroTextField, fevereiroTextField, marcoTextField, abrilTextField, maioTextField,
            junhoTextField, julhoTextField, agostoTextField, setembroTextField,
            outubroTextField, novembroTextField, dezembroTextField;
    @FXML public CheckBox cronogramaCheckBox;
    @FXML public TitledPane dadosjuridicoTitledPane, empenhoTitledPane;
    @FXML public Button fecharButton, salvarButton, editarCredorButton;

    public PrincipalController principalController;
    public SapController sapController;

    private Sap sap;

    RequisicaoController requisicaoController = new RequisicaoController();
    SapDAO sapDAO = new SapDAO();

    public void setSap(Sap sap) {
        this.sap = sap;
        preCadastro();
    }

    public void preCadastro() {
        credorComboBox.setValue(sap.getCredor());
        setorComboBox.setValue(sap.getSetorSolicitante());
        emprenhoComboBox.setValue(sap.getTipoEmpenhosolicitado());
        unidadegestoraComboBox.setValue(sap.getUnidadeGestora());
        eventoComboBox.setValue(sap.getEvento());
        unidadeorcamentariaComboBox.setValue(sap.getUnidadeOrcamentaria());
        subacaoComboBox.setValue(sap.getSubacao());
        recursoComboBox.setValue(sap.getFonteRecurso());
        naturezaComboBox.setValue(sap.getNaturezaDespesa());
        modlicComboBox.setValue(sap.getModalidadeLicitacao());
        tipocontratoComboBox.setValue(sap.getTipoContrato());
        instrumentoComboBox.setValue(sap.getInstrumento());
        valorTextField.setText(String.valueOf(sap.getValor()));
        processoTextField.setText(sap.getProcesso());
        datasolicitacaoDatePicker.setValue(sap.getDatasolicitacao());
        numeroSapTextField.setText(String.valueOf(sap.getNumeroSap()));
        historicoTextArea.setText(sap.getHistorico());
        numeroautosTextField.setText(sap.getNumeroAutos());
        parteadvogadoComboBox.setValue(sap.getParteAdvogado());
        nomeparteTextField.setText(sap.getNomeParte());
        datamandadoDatePicker.setValue(sap.getDataMandado());
        dataintiprevDatePicker.setValue(sap.getDataIntimacaoIprev());
        datavencimentoDatePicker.setValue(sap.getDataVencimento());
        prazopagamentoTextField.setText(sap.getPrazoPagamento());
        janeiroTextField.setText(sap.getValor_janeiro());
        fevereiroTextField.setText(sap.getValor_fevereiro());
        marcoTextField.setText(sap.getValor_marco());
        abrilTextField.setText(sap.getValor_abril());
        maioTextField.setText(sap.getValor_maio());
        junhoTextField.setText(sap.getValor_junho());
        julhoTextField.setText(sap.getValor_julho());
        agostoTextField.setText(sap.getValor_agosto());
        setembroTextField.setText(sap.getValor_setembro());
        outubroTextField.setText(sap.getValor_outubro());
        novembroTextField.setText(sap.getValor_novembro());
        dezembroTextField.setText(sap.getValor_dezembro());
    }

    BancoDados bancoDados = new BancoDados();
    String valorCompleto = "";

    @FXML
    public void initialize() {
        eventoComboBox.setItems(eventos);
        eventos.addAll(bancoDados.getEventos());
        subacaoComboBox.setItems(subacaos);
        subacaos.addAll(bancoDados.getSubacao());
        setorComboBox.setItems(unidades);
        unidades.addAll(bancoDados.getUnidade());
        unidadegestoraComboBox.setItems(unidadesGestora);
        unidadesGestora.addAll(bancoDados.getUnidadeGestora());
        modlicComboBox.setItems(modalidadesLicitacao);
        modalidadesLicitacao.addAll(bancoDados.getModalidadeLicitacao());
        emprenhoComboBox.setItems(modalidadesEmpenho);
        modalidadesEmpenho.addAll(bancoDados.getModalidadeEmpenho());
        recursoComboBox.setItems(fontesRecursos);
        fontesRecursos.addAll(bancoDados.getFonteRecurso());
        naturezaComboBox.setItems(naturezasDespesa);
        naturezasDespesa.addAll(bancoDados.getNaturezaDespesa());
        tipocontratoComboBox.setItems(tiposContrato);
        tiposContrato.addAll(bancoDados.getTipoContrato());
        instrumentoComboBox.setItems(instrumentos);
        instrumentos.addAll(bancoDados.getInstrumento());
        unidadeorcamentariaComboBox.setItems(unidadesOrcamentaria);
        unidadesOrcamentaria.addAll(bancoDados.getUnidadeOrcamentaria());
        parteadvogadoComboBox.setItems(partesAdvogado);
        partesAdvogado.addAll(bancoDados.getParteAdvogado());
        credorComboBox.setItems(credores);
        credores.addAll(bancoDados.getCredor());
        requisicaoComboBox.setItems(requisicoes);
        requisicoes.addAll(bancoDados.listarRequisicao());
        gestaoComboBox.setItems(unidadesOrcamentaria);


        datasolicitacaoDatePicker.setValue(LocalDate.now());

        empenhoTitledPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("teste");
            Stage stage = (Stage) empenhoTitledPane.getScene().getWindow();
            stage.sizeToScene();
        });
        dadosjuridicoTitledPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            //  System.out.println("teste");
            Stage stage = (Stage) empenhoTitledPane.getScene().getWindow();
            stage.sizeToScene();
        });

        valorTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                dividirValores();
            }
        });

        emprenhoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            validarEmpenho();
        });

        eventoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            definirHistorico();
        });

        requisicaoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            pegarDadosReq();
        });

        unidadegestoraComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            definirGestao();
        });


        ComboBoxKeyCompleter completer = new ComboBoxKeyCompleter();
        completer.install(credorComboBox);
        completer.install(setorComboBox);
        completer.install(emprenhoComboBox);
        completer.install(unidadegestoraComboBox);
        completer.install(eventoComboBox);
        completer.install(unidadeorcamentariaComboBox);
        completer.install(subacaoComboBox);
        completer.install(recursoComboBox);
        completer.install(naturezaComboBox);
        completer.install(modlicComboBox);
        completer.install(tipocontratoComboBox);
        completer.install(instrumentoComboBox);

        Image cross = new Image(getClass().getResourceAsStream("cross.png"));
        Image save = new Image(getClass().getResourceAsStream("save.png"));
        salvarButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));

    }

    NumberFormat nf = NumberFormat.getCurrencyInstance();

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    private ObservableList<Credor> credores = FXCollections.observableArrayList();
    private ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private ObservableList<Subacao> subacaos = FXCollections.observableArrayList();
    private ObservableList<Unidade> unidades = FXCollections.observableArrayList();
    private ObservableList<UnidadeGestora> unidadesGestora = FXCollections.observableArrayList();
    private ObservableList<ModalidadeLicitacao> modalidadesLicitacao = FXCollections.observableArrayList();
    private ObservableList<ModalidadeEmpenho> modalidadesEmpenho = FXCollections.observableArrayList();
    private ObservableList<FonteRecurso> fontesRecursos = FXCollections.observableArrayList();
    private ObservableList<NaturezaDespesa> naturezasDespesa = FXCollections.observableArrayList();
    private ObservableList<TipoContrato> tiposContrato = FXCollections.observableArrayList();
    private ObservableList<Instrumento> instrumentos = FXCollections.observableArrayList();
    private ObservableList<UnidadeOrcamentaria> unidadesOrcamentaria = FXCollections.observableArrayList();
    private ObservableList<ParteAdvogado> partesAdvogado = FXCollections.observableArrayList();
    private ObservableList<Requisicao> requisicoes = FXCollections.observableArrayList();

    @FXML
    public void fecharSap() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void salvarSap() {

        if (!validarDadosSap()) {
            exibirMensagemErro("Por favor, preencha todos os dados!");
            return;
        }

        boolean editando = true;

        if (sap == null) {
            sap = new Sap();
            editando = false;
        }
        sap.setEvento(eventoComboBox.getValue());
        sap.setSubacao(subacaoComboBox.getValue());
        sap.setSetorSolicitante(setorComboBox.getValue());
        sap.setUnidadeGestora(unidadegestoraComboBox.getValue());
        sap.setModalidadeLicitacao(modlicComboBox.getValue());
        sap.setTipoEmpenhosolicitado(emprenhoComboBox.getValue());
        sap.setFonteRecurso(recursoComboBox.getValue());
        sap.setNaturezaDespesa(naturezaComboBox.getValue());
        sap.setTipoContrato(tipocontratoComboBox.getValue());
        sap.setInstrumento(instrumentoComboBox.getValue());
        sap.setUnidadeOrcamentaria(unidadeorcamentariaComboBox.getValue());
        sap.setParteAdvogado(parteadvogadoComboBox.getValue());
        sap.setCredor(credorComboBox.getValue());
        sap.setValor((Float.parseFloat(valorTextField.getText())));

        sap.setDatasolicitacao(datasolicitacaoDatePicker.getValue());
        sap.setDataMandado(datamandadoDatePicker.getValue());
        sap.setDataIntimacaoIprev(dataintiprevDatePicker.getValue());
        sap.setDataVencimento(datavencimentoDatePicker.getValue());
        sap.setProcesso(processoTextField.getText());
        sap.setNumeroAutos(numeroautosTextField.getText());
        sap.setNomeParte(nomeparteTextField.getText());
        sap.setPrazoPagamento(prazopagamentoTextField.getText());
        sap.setHistorico(historicoTextArea.getText());
        sap.setValor_janeiro(janeiroTextField.getText());
        sap.setValor_fevereiro(fevereiroTextField.getText());
        sap.setValor_marco(marcoTextField.getText());
        sap.setValor_abril(abrilTextField.getText());
        sap.setValor_maio(maioTextField.getText());
        sap.setValor_junho(junhoTextField.getText());
        sap.setValor_julho(julhoTextField.getText());
        sap.setValor_agosto(agostoTextField.getText());
        sap.setValor_setembro(setembroTextField.getText());
        sap.setValor_outubro(outubroTextField.getText());
        sap.setValor_novembro(novembroTextField.getText());
        sap.setValor_dezembro(dezembroTextField.getText());

        if (editando == false) {
            principalController.adicionarSap(sap);
            sap = null;
            salvarButton.getScene().getWindow().hide();
            notificandoCadastro();
        } else if (editando == true) {
            principalController.atualizarItensSap();
            sapDAO.atualizandoSap(sap);
            salvarButton.getScene().getWindow().hide();
            notificandoAtualizacao();
        }

    }

    Date data = new Date();
    GregorianCalendar mesAtual = new GregorianCalendar();

    LocalDate localDate = LocalDate.now();

    public void dividirValores() {

        mesAtual.setTime(data);

        ModalidadeEmpenho empenho = emprenhoComboBox.getValue();

        float valor = Float.parseFloat(valorTextField.getText());
        int todosMeses = 13;
        int mes = localDate.getMonthValue();
        float divisao = valor / (todosMeses - mes);

        float valorEstimativo = 0;

        if (cronogramaCheckBox.isSelected() && empenho.getCodMod() == 2
                || cronogramaCheckBox.isSelected() && empenho.getCodMod() == 1) {
            if (localDate.getMonthValue() == 1) {
                janeiroTextField.setText(nf.format(divisao));
                fevereiroTextField.setText(nf.format(divisao));
                marcoTextField.setText(nf.format(divisao));
                abrilTextField.setText(nf.format(divisao));
                maioTextField.setText(nf.format(divisao));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 2) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(divisao));
                marcoTextField.setText(nf.format(divisao));
                abrilTextField.setText(nf.format(divisao));
                maioTextField.setText(nf.format(divisao));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 3) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(divisao));
                abrilTextField.setText(nf.format(divisao));
                maioTextField.setText(nf.format(divisao));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 4) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(divisao));
                maioTextField.setText(nf.format(divisao));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 5) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(divisao));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 6) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(divisao));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 7) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(divisao));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 8) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(valorEstimativo));
                agostoTextField.setText(nf.format(divisao));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 9) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(valorEstimativo));
                agostoTextField.setText(nf.format(valorEstimativo));
                setembroTextField.setText(nf.format(divisao));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 10) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(valorEstimativo));
                agostoTextField.setText(nf.format(valorEstimativo));
                setembroTextField.setText(nf.format(valorEstimativo));
                outubroTextField.setText(nf.format(divisao));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 11) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(valorEstimativo));
                agostoTextField.setText(nf.format(valorEstimativo));
                setembroTextField.setText(nf.format(valorEstimativo));
                outubroTextField.setText(nf.format(valorEstimativo));
                novembroTextField.setText(nf.format(divisao));
                dezembroTextField.setText(nf.format(divisao));
            }
            if (localDate.getMonthValue() == 12) {
                janeiroTextField.setText(nf.format(valorEstimativo));
                fevereiroTextField.setText(nf.format(valorEstimativo));
                marcoTextField.setText(nf.format(valorEstimativo));
                abrilTextField.setText(nf.format(valorEstimativo));
                maioTextField.setText(nf.format(valorEstimativo));
                junhoTextField.setText(nf.format(valorEstimativo));
                julhoTextField.setText(nf.format(valorEstimativo));
                agostoTextField.setText(nf.format(valorEstimativo));
                setembroTextField.setText(nf.format(valorEstimativo));
                outubroTextField.setText(nf.format(valorEstimativo));
                novembroTextField.setText(nf.format(valorEstimativo));
                dezembroTextField.setText(nf.format(divisao));
            }
        } else {
            janeiroTextField.setText(nf.format(valorEstimativo));
            fevereiroTextField.setText(nf.format(valorEstimativo));
            marcoTextField.setText(nf.format(valorEstimativo));
            abrilTextField.setText(nf.format(valorEstimativo));
            maioTextField.setText(nf.format(valorEstimativo));
            junhoTextField.setText(nf.format(valorEstimativo));
            julhoTextField.setText(nf.format(valorEstimativo));
            agostoTextField.setText(nf.format(valorEstimativo));
            setembroTextField.setText(nf.format(valorEstimativo));
            outubroTextField.setText(nf.format(valorEstimativo));
            novembroTextField.setText(nf.format(valorEstimativo));
            dezembroTextField.setText(nf.format(valorEstimativo));
        }

    }

    public void validarEmpenho() {

        ModalidadeEmpenho tipo = emprenhoComboBox.getValue();

        if (tipo.getCodMod() == 3) {
            empenhoTitledPane.setExpanded(false);
            empenhoTitledPane.setDisable(true);
            empenhoTitledPane.setVisible(false);
        } else {
            empenhoTitledPane.setExpanded(true);
            empenhoTitledPane.setDisable(false);
            empenhoTitledPane.setVisible(true);
        }
    }

    Requisicao requisicao = new Requisicao();

    public void pegarDadosReq() {

        Requisicao req = requisicaoComboBox.getValue();

        setorComboBox.setValue(req.getSetorSolicitante());
        credorComboBox.setValue(req.getCredor());
        valorTextField.setText(String.valueOf(req.getValorRequisicao()));
        subacaoComboBox.setValue(req.getSubacao());
        recursoComboBox.setValue(req.getFonteRecurso());
        naturezaComboBox.setValue(req.getNaturezaDespesa());
        historicoTextArea.setText(historicoTextArea.getText() + " " + req.getDescricao() + " " + req.getJustificativa());
    }

    public void pegarHistorico() {
        String descricaoReq = requisicaoController.descricaoTextArea.getText();
        String justificativa = requisicaoController.justificativaTextArea.getText();

        String text = justificativa + " " + descricaoReq;
        historicoTextArea.setText(text);
    }

    public boolean validarDadosSap() {

        if (datasolicitacaoDatePicker.getValue() == null) {
            return false;
        }

        if (setorComboBox.getValue() == null) {
            return false;
        }

        if (emprenhoComboBox.getValue() == null) {
            return false;
        }

        if (unidadegestoraComboBox.getValue() == null) {
            return false;
        }

        if (eventoComboBox.getValue() == null) {
            return false;
        }

        if (valorTextField.getText().isEmpty() || valorTextField.getText() == null) {
            return false;
        }

        if (credorComboBox.getValue() == null) {
            return false;
        }

        if (unidadeorcamentariaComboBox.getValue() == null) {
            return false;
        }

        if (subacaoComboBox.getValue() == null) {
            return false;
        }

        if (recursoComboBox.getValue() == null) {
            return false;
        }

        if (naturezaComboBox.getValue() == null) {
            return false;
        }

        if (modlicComboBox.getValue() == null) {
            return false;
        }

        if (processoTextField.getText() == null || processoTextField.getText().isEmpty()) {
            return true;
        }

        if (tipocontratoComboBox.getValue() == null) {
            return false;
        }

        if (instrumentoComboBox.getValue() == null) {
            return false;
        }

        if (numeroautosTextField.getText() == null || numeroautosTextField.getText().isEmpty()) {
            return true;
        }

        if (nomeparteTextField.getText() == null || nomeparteTextField.getText().isEmpty()) {
            return true;
        }

        if (prazopagamentoTextField.getText() == null || prazopagamentoTextField.getText().isEmpty()) {
            return true;
        }

        if (parteadvogadoComboBox.getValue() == null) {
            return true;
        }

        if (datamandadoDatePicker.getValue() == null) {
            return true;
        }

        if (dataintiprevDatePicker.getValue() == null) {
            return true;
        }

        if (datavencimentoDatePicker.getValue() == null) {
            return true;
        }

        if (historicoTextArea.getText() == null || historicoTextArea.getText().isEmpty()) {
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

    public void definirHistorico() {

        Evento evento = eventoComboBox.getValue();

        if (evento.getCodEvento() == 1) {
            historicoTextArea.setText("Empenho que se processa em nome do credor, em Despesa Orçamentária a Liquidar, referente: ");
        } else if (evento.getCodEvento() == 2) {
            historicoTextArea.setText("Empenho que se processa em nome do credor, em Despesa Orçamentária a Liquidar, referente: ");
        } else if (evento.getCodEvento() == 3) {
            historicoTextArea.setText("Empenho que se processa em nome do credor, em Despesa Orçamentária a Liquidar, referente: ");
        } else if (evento.getCodEvento() == 4) {
            historicoTextArea.setText("Estorno parcial do empenho nº XXXXXXXXXXXXXX de XX/XX/XX, em virtude de não ter sido utilizado em sua totalidade.");
        } else if (evento.getCodEvento() == 5) {
            historicoTextArea.setText("Empenho que se processa em nome do credor, em Despesa Orçamentária a Liquidar, referente:");
        }


    }

    public void definirGestao() {

        UnidadeGestora undg = unidadegestoraComboBox.getValue();

        if (undg.getCodUg() == 470022) {
            gestaoComboBox.setValue("1");
        } else if (undg.getCodUg() == 470075) {
            gestaoComboBox.setValue("47075");
        } else if (undg.getCodUg() == 470076)
            gestaoComboBox.setValue("47076");

    }

    String filter = "";

    @FXML
    public void editandoCredor() throws IOException {
        /*System.out.println("editando credor");*/
        Stage credorStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CadastrarCredorJanela.fxml"));
        Parent root = loader.load();

        CredorController credorControl = loader.getController();
        credorControl.setSapController(this);

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
                .title("SAP Cadastrada")
                .text("SAP cadastrada com sucesso")
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
                .title("SAP Atualizada")
                .text("SAP atualizada com sucesso")
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

    public void definirValores(Float dividao) {

        float divisao = 0;

        janeiroTextField.setText(nf.format(divisao));
        fevereiroTextField.setText(nf.format(divisao));
        marcoTextField.setText(nf.format(divisao));
        abrilTextField.setText(nf.format(divisao));
        maioTextField.setText(nf.format(divisao));
        junhoTextField.setText(nf.format(divisao));
        julhoTextField.setText(nf.format(divisao));
        agostoTextField.setText(nf.format(divisao));
        setembroTextField.setText(nf.format(divisao));
        outubroTextField.setText(nf.format(divisao));
        novembroTextField.setText(nf.format(divisao));
        dezembroTextField.setText(nf.format(divisao));

    }

    public void credorSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erro de Sistema");
        alert.setHeaderText("Nenhum credor foi selecionado.");
        alert.setContentText("É necessário selecionar um credor para abrir a janela de informações.");
        alert.showAndWait();
    }


}
