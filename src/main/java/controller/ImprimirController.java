package controller;

import DAO.BancoDados;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javafx.scene.image.ImageView;
import model.Requisicao;
import model.Sap;
import model.Unidade;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static com.itextpdf.text.FontFactory.TIMES_ROMAN;
import static com.itextpdf.text.FontFactory.contains;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ImprimirController {

    @FXML public Button fecharButton, imprimirButton;
    @FXML public CheckBox sapCheckBox, requisicaoCheckBox;
    @FXML public ComboBox<Sap> sapComboBox;
    @FXML public ComboBox<Requisicao> requisicaoComboBox;

    public PrincipalController principalController;

    Sap sap = new Sap();

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    @FXML
    public void initialize() {
        sapComboBox.setItems(saps);
        saps.addAll(bancoDados.listarSaps());
        requisicaoComboBox.setItems(requisicoes);
        requisicoes.addAll(bancoDados.listarRequisicao());

        javafx.scene.image.Image cross = new javafx.scene.image.Image(getClass().getResourceAsStream("cross.png"));
        javafx.scene.image.Image save = new javafx.scene.image.Image(getClass().getResourceAsStream("print.png"));
        imprimirButton.setGraphic(new ImageView(save));
        fecharButton.setGraphic(new ImageView(cross));
    }

    BancoDados bancoDados = new BancoDados();

    ObservableList<Sap> saps = FXCollections.observableArrayList();
    ObservableList<Requisicao> requisicoes = FXCollections.observableArrayList();

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void imprimirDocumento() {
        if (sapCheckBox.isSelected() && requisicaoCheckBox.isSelected()) {
            imprimirSap();
            imprimirRequisicao();
        } else if (sapCheckBox.isSelected()) {
            imprimirSap();
        } else if (requisicaoCheckBox.isSelected()) {
            imprimirRequisicao();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro de Impressão");
            alert.setHeaderText("Nenhum item foi selecionado.");
            alert.setContentText("Por favor, selecione pelo menos um item para ser impresso.");
            alert.showAndWait();
        }
    }

    NumberFormat nf = NumberFormat.getCurrencyInstance();
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");



    public void imprimirSap() {

        Document sapDocument = null;
        OutputStream outputStream = null;

        File sapFile = new File("DocumentoSAP.pdf");


        try {
            sapDocument = new Document(PageSize.LETTER, 30, 30, 30, 30);
            outputStream = new FileOutputStream(sapFile);
            try {
                PdfWriter.getInstance(sapDocument, outputStream);
                sapDocument.open();

                Sap saps = sapComboBox.getValue();

                Image logo = Image.getInstance("logoIprev.png");
                Paragraph cabecalho = new Paragraph("    ESTADO DE SANTA CATARINA"
                        + "\n    SECRETARIA DE ESTADO DA ADMINISTRAÇÃO"
                        + "\n    INSTITUTO DE PREVIDÊNCIA DO ESTADO DE SANTA CATARINA", FontFactory.getFont(FontFactory.TIMES_BOLD, 11));
                Paragraph tituloSolicitacao = new Paragraph("\nSOLICITÇÃO DE AUTORIZAÇÃO DE PAGAMENTO - SAP\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15));
                Paragraph tituloDois = new Paragraph("Tipo de SAP: " + saps.getEvento().getEvento(), FontFactory.getFont(FontFactory.HELVETICA, 12));
                Paragraph espaco = new Paragraph(" ");
                Paragraph linha = new Paragraph("____________________________________________________________________");
                Paragraph assinatura = new Paragraph("Diretor/ Gerente/ Supervisos responsável pela licitação", FontFactory.getFont(FontFactory.TIMES_ITALIC, 10));

                // criar a tabela
                PdfPTable tabela1 = new PdfPTable(3); // data, setor solicitante, numero sap
                PdfPTable tabela2 = new PdfPTable(4); // evento, unidade gestora, gestao; mod empenho
                PdfPTable tabela3 = new PdfPTable(3); // valor, credor, CNPJ
                PdfPTable tabela4 = new PdfPTable(2); // unidade orçamentaria, subacao
                PdfPTable tabela5 = new PdfPTable(2); // fonte recurso, natureza despesa
                PdfPTable tabela6 = new PdfPTable(2); // modalidade licitacao, instrumento
                PdfPTable tabela7 = new PdfPTable(1); // tipo contrato
                PdfPTable tabela8 = new PdfPTable(3); // conta bancaria, agencia, nome conta
                PdfPTable tabela9 = new PdfPTable(1); // historico
                PdfPTable tabela10 = new PdfPTable(3); // assinatura
                PdfPTable tabela11 = new PdfPTable(4); // janeiro, fevereiro, março, abril
                PdfPTable tabela12 = new PdfPTable(4); // maio, junho, julho, agosto
                PdfPTable tabela13 = new PdfPTable(4); // setembro, outubro, novembro, dezembro

                // criar as colunas
                PdfPCell cellDataSolcitacao = new PdfPCell(new Phrase("Data Solicitação SAP", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));               // tabela1
                PdfPCell cellSetorSolicitante = new PdfPCell(new Phrase("Setor Solicitante", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                // tabela1
                PdfPCell cellNumeroSap = new PdfPCell(new Phrase("Número SAP", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                              // tabela1
                PdfPCell cellEvento = new PdfPCell(new Phrase("Evento", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                     // tabela2
                PdfPCell cellUnidadeGestora = new PdfPCell(new Phrase("Unidade Gestora", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                    // tabela2
                PdfPCell cellModEmpenho = new PdfPCell(new Phrase("Modalidade Empenho", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                     // tabela2
                PdfPCell cellValor = new PdfPCell(new Phrase("Valor", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                       // tabela3
                PdfPCell cellCredor = new PdfPCell(new Phrase("Credor", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                     // tabela3
                PdfPCell cellCnpj = new PdfPCell(new Phrase("CNPJ/CPF", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                     // tabela3
                PdfPCell cellUnidadeOrcamentaria = new PdfPCell(new Phrase("Unidade Orçamentária", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));          // tabela4
                PdfPCell cellSubacao = new PdfPCell(new Phrase("Subação", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                   // tabela4
                PdfPCell cellFonteRecurso = new PdfPCell(new Phrase("Fonte Recurso", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                        // tabela5
                PdfPCell cellNaturezaDespesa = new PdfPCell(new Phrase("Natureza Despesa", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                  // tabela5
                PdfPCell cellModalidadeLicitacao = new PdfPCell(new Phrase("Modalidade Licitação", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));          // tabela6
                PdfPCell cellInstrumento = new PdfPCell(new Phrase("Instrumento", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                           // tabela6
                PdfPCell cellTipoContrato = new PdfPCell(new Phrase("Tipo Contrato", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                        // tabela7
                PdfPCell cellContaBancaria = new PdfPCell(new Phrase("Conta Bancaria", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                      // tabela8
                PdfPCell cellAgencia = new PdfPCell(new Phrase("Agência", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                                   // tabela8
                PdfPCell cellNomeConta = new PdfPCell(new Phrase("Nome Conta", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                              // tabela8
                PdfPCell cellHistorico = new PdfPCell(new Phrase("Histórico", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));                               // tabela9
                PdfPCell cellGestao = new PdfPCell(new Phrase("Gestão", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellResponsavel = new PdfPCell(new Phrase("Responsável", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellCarimbo = new PdfPCell(new Phrase("Carimbo e Assinatura", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellData = new PdfPCell(new Phrase("Data", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellJaneiro = new PdfPCell(new Phrase("Janeiro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellFevereiro = new PdfPCell(new Phrase("Fevereiro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellMarco = new PdfPCell(new Phrase("Março", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellAbril = new PdfPCell(new Phrase("Abril", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellMaio = new PdfPCell(new Phrase("Maio", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellJunho = new PdfPCell(new Phrase("Junho", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellJulho = new PdfPCell(new Phrase("Julho", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellAgosto = new PdfPCell(new Phrase("Agosto", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellSetembro = new PdfPCell(new Phrase("Setembro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellOutubro = new PdfPCell(new Phrase("Outubro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellNovembro = new PdfPCell(new Phrase("Novembro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
                PdfPCell cellDezembro = new PdfPCell(new Phrase("Dezembro", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));

                logo.setAlignment(Image.TEXTWRAP);
                logo.scaleAbsolute(50, 50);

                // editar as tabelas
                // tabela 1
                tabela1.setTotalWidth(580);
                tabela1.setHeaderRows(1);
                tabela1.setLockedWidth(true);
                // tabela 2
                tabela2.setTotalWidth(580);
                tabela2.setHeaderRows(1);
                tabela2.setLockedWidth(true);

                tabela2.setWidths(new float[] {
                        0.44f, 0.29f, 0.07f, 0.20f
                });
                // tabela 3
                tabela3.setTotalWidth(580);
                tabela3.setHeaderRows(1);
                tabela3.setLockedWidth(true);
                // tabela 4
                tabela4.setTotalWidth(580);
                tabela4.setHeaderRows(1);
                tabela4.setLockedWidth(true);

                tabela4.setWidths(new float[] {
                        0.35f, 0.65f
                });

                tabela3.setWidths(new float[] {
                        0.65f, 0.20f, 0.18f
                });
                // tabela 5
                tabela5.setTotalWidth(580);
                tabela5.setHeaderRows(1);
                tabela5.setLockedWidth(true);
                // tabela 6
                tabela6.setTotalWidth(580);
                tabela6.setHeaderRows(1);
                tabela6.setLockedWidth(true);
                // tabela 7
                tabela7.setTotalWidth(580);
                tabela7.setHeaderRows(1);
                tabela7.setLockedWidth(true);
                // tabela 8
                tabela8.setTotalWidth(580);
                tabela8.setHeaderRows(1);
                tabela8.setLockedWidth(true);
                // tabela 9
                tabela9.setTotalWidth(580);
                tabela9.setHeaderRows(1);
                tabela9.setLockedWidth(true);
                // tabela 10
                tabela10.setTotalWidth(580);
                tabela10.setHeaderRows(1);
                tabela10.setLockedWidth(true);

                tabela11.setTotalWidth(580);
                tabela11.setHeaderRows(1);
                tabela11.setLockedWidth(true);

                tabela12.setTotalWidth(580);
                tabela12.setHeaderRows(1);
                tabela12.setLockedWidth(true);

                tabela13.setTotalWidth(580);
                tabela13.setHeaderRows(1);
                tabela13.setLockedWidth(true);

                // adicionar elementos na tabela
                tabela1.addCell(cellDataSolcitacao);
                tabela1.addCell(cellSetorSolicitante);
                tabela1.addCell(cellNumeroSap);
                tabela2.addCell(cellEvento);
                tabela2.addCell(cellUnidadeGestora);
                tabela2.addCell(cellGestao);
                tabela2.addCell(cellModEmpenho);
                tabela3.addCell(cellCredor);
                tabela3.addCell(cellCnpj);
                tabela3.addCell(cellValor);
                tabela4.addCell(cellUnidadeOrcamentaria);
                tabela4.addCell(cellSubacao);
                tabela5.addCell(cellFonteRecurso);
                tabela5.addCell(cellNaturezaDespesa);
                tabela6.addCell(cellModalidadeLicitacao);
                tabela6.addCell(cellInstrumento);
                tabela7.addCell(cellTipoContrato);
                tabela8.addCell(cellContaBancaria);
                tabela8.addCell(cellAgencia);
                tabela8.addCell(cellNomeConta);
                tabela9.addCell(cellHistorico);
                tabela10.addCell(cellResponsavel);
                tabela10.addCell(cellCarimbo);
                tabela10.addCell(cellData);
                tabela11.addCell(cellJaneiro);
                tabela11.addCell(cellFevereiro);
                tabela11.addCell(cellMarco);
                tabela11.addCell(cellAbril);
                tabela12.addCell(cellMaio);
                tabela12.addCell(cellJunho);
                tabela12.addCell(cellJulho);
                tabela12.addCell(cellAgosto);
                tabela13.addCell(cellSetembro);
                tabela13.addCell(cellOutubro);
                tabela13.addCell(cellNovembro);
                tabela13.addCell(cellDezembro);

                // adicionar elementos nas células das tabelas
                // tabela1
                tabela1.addCell(dtf.format(saps.getDatasolicitacao()));
                tabela1.addCell(saps.getSetorSolicitante().getNome());
                tabela1.addCell(String.valueOf(saps.getNumeroSap()) + "/" + saps.getDatasolicitacao().getYear());
                // tabela2
                tabela2.addCell(saps.getEvento().getCodigo() + "| " + saps.getEvento().getEvento());
                tabela2.addCell(saps.getUnidadeGestora().getCodUg() + "| " + saps.getUnidadeGestora().getSigla());
                tabela2.addCell(String.valueOf(saps.getUnidadeGestora().getGestao()));
                tabela2.addCell(saps.getTipoEmpenhosolicitado().getEmpenho());
                // tabela3
                tabela3.addCell(saps.getCredor().getNomeCredor());
                tabela3.addCell(saps.getCredor().getIdentificacao());
                tabela3.addCell(nf.format(saps.getValor()));

                // tabela4
                tabela4.addCell(saps.getUnidadeOrcamentaria().getCodUo() + "| " + saps.getUnidadeOrcamentaria().getDescricao());
                tabela4.addCell(saps.getSubacao().getSubacao() + "| " + saps.getSubacao().getDescricao());

                // tabela5
                tabela5.addCell(saps.getFonteRecurso().getRelacaoFontes() + "| " + saps.getFonteRecurso().getDescricao());
                tabela5.addCell(saps.getNaturezaDespesa().getCodDespesa() + "| " + saps.getNaturezaDespesa().getDescricao());

                // tabela6
                //saps.getModalidadeLicitacao().getCodMod()
                //saps.getModalidadeLicitacao().getDescricao()
                tabela6.addCell(saps.getModalidadeLicitacao().getCodMod() + "| " + saps.getModalidadeLicitacao().getDescricao());
                tabela6.addCell(saps.getInstrumento().getDescricao());

                // tabela7
                tabela7.addCell(saps.getTipoContrato().getDescricao());

                // tabela8
                tabela8.addCell(saps.getCredor().getConta());
                tabela8.addCell(saps.getCredor().getAgencia());
                tabela8.addCell(saps.getCredor().getNomeConta());

                // tabela9
                tabela9.addCell(saps.getHistorico());
                LocalDateTime dataAtual = LocalDateTime.now();

                // tabela10
                tabela10.addCell("Diretor/ Gerente/ Supervisor");
                tabela10.addCell("\n\n\n");
                tabela10.addCell(dtf.format(dataAtual));

                tabela11.addCell(saps.getValor_janeiro());
                tabela11.addCell(saps.getValor_fevereiro());
                tabela11.addCell(saps.getValor_marco());
                tabela11.addCell(saps.getValor_abril());

                tabela12.addCell(saps.getValor_maio());
                tabela12.addCell(saps.getValor_junho());
                tabela12.addCell(saps.getValor_julho());
                tabela12.addCell(saps.getValor_agosto());

                tabela13.addCell(saps.getValor_setembro());
                tabela13.addCell(saps.getValor_outubro());
                tabela13.addCell(saps.getValor_novembro());
                tabela13.addCell(saps.getValor_dezembro());

                // editar as colunas
                cabecalho.setAlignment(Element.ALIGN_LEFT);
                tituloSolicitacao.setAlignment(Element.ALIGN_CENTER);
                tituloDois.setAlignment(Element.ALIGN_CENTER);
                linha.setAlignment(Element.ALIGN_CENTER);
                assinatura.setAlignment(Element.ALIGN_CENTER);

                // adicionar elementos no documento
                sapDocument.add(logo);
                sapDocument.add(cabecalho);
                sapDocument.add(tituloSolicitacao);
                sapDocument.add(tituloDois);
                sapDocument.add(espaco);
                sapDocument.add(tabela1);
                /*sapDocument.add(espaco);*/
                sapDocument.add(tabela2);
                sapDocument.add(espaco);
                sapDocument.add(tabela3);
                /*sapDocument.add(espaco);*/
                sapDocument.add(tabela4);
                /*sapDocument.add(espaco);*/
                sapDocument.add(tabela5);
                /*sapDocument.add(espaco);*/
                sapDocument.add(tabela6);

                // analisa o tipo de empenho da sap, e gera a tabela
                if (saps.getTipoEmpenhosolicitado().getCodMod() == 2) {
                    sapDocument.add(espaco);
                    sapDocument.add(tabela11);
                    sapDocument.add(tabela12);
                    sapDocument.add(tabela13);
                } else if (saps.getTipoEmpenhosolicitado().getCodMod() == 1) {
                    sapDocument.add(espaco);
                    sapDocument.add(tabela11);
                    sapDocument.add(tabela12);
                    sapDocument.add(tabela13);
                }

                sapDocument.add(espaco);
                sapDocument.add(tabela7);
                /*sapDocument.add(espaco);*/
                sapDocument.add(tabela8);
                sapDocument.add(espaco);
                sapDocument.add(tabela9);
                sapDocument.add(espaco);
                /*sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                sapDocument.add(espaco);
                //sapDocument.add(linha);
                //sapDocument.add(assinatura);*/
                sapDocument.add(tabela10);
                // fechar e abrir documento
                sapDocument.close();
                abrirArquivo(sapFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(saps);

    }

    public void imprimirRequisicao() {

        Document requisicaoDocument = null;
        OutputStream outputStream = null;

        File sapFile = new File("DocumentoRequisicao.pdf");


        try {
            requisicaoDocument = new Document(PageSize.LETTER, 30, 30, 30, 30);
            outputStream = new FileOutputStream(sapFile);

            try {
                PdfWriter.getInstance(requisicaoDocument, outputStream);
                requisicaoDocument.open();

                Paragraph espaco = new Paragraph("\n");
                Image logo = Image.getInstance("logoIprev.png");
                Paragraph cabecalho = new Paragraph(
                        "    ESTADO DE SANTA CATARINA"
                                + "\n    SECRETARIA DE ESTADO DA ADMINISTRAÇÃO"
                                + "\n    INSTITUTO DE PREVIDÊNCIA DO ESTADO DE SANTA CATARINA",
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 11));
                Paragraph tituloSolicitacao = new Paragraph(
                        "\nREQUISIÇÃO DE COMPRA DIRETA OU SERVIÇO\n",
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                Paragraph frase = new Paragraph("'É vedada a realização de despesa sem prévio empenho' Art. 60 da lei 4320/64.\n"
                        + "Só autorize o inicio da execução dos serviços após receber a cópia do empenho");

                Paragraph frase2 = new Paragraph("IDENTIFICAÇÃO DOS DADOS DO FORNECEDOR/PRESTADOR DE SERVIÇO A SER CONTRATADO\n"
                        + "GERÊNCIA DA ÁREA (Obs.: a requisição será devolvida caso um dos campos não esteja corretamente preenchido.)\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9));
                Paragraph indicacao = new Paragraph("INDICAÇÃO DA DOTAÇÃO ORÇAMENTÁRIA",
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 9));
                Paragraph confirmacao = new Paragraph("CONFIRMAÇÃO DA EXISTÊNCIA DE RECURSOS FINÂNCEIROS",
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
                Paragraph dados = new Paragraph("Confirmação:\n" + "Grupo Financeiro:______________________\n" + "Valor:\n\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9));
                Paragraph setor = new Paragraph("SETOR DE LICITAÇÃO\n",
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
                Paragraph setorLic = new Paragraph("Confirmação:\n" + "Motivo Despesa:\n" + "Base Legal:\n\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9));
                Paragraph diretor = new Paragraph("AUTORIZAÇÃO DO DIRETOR DE ADMINISTRAÇÃO",
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
                Paragraph diretorDef = new Paragraph("Deferimento/Indeferimento:\n\n\n\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9));


                logo.setAlignment(Image.TEXTWRAP);
                logo.scaleAbsolute(50, 50);

                cabecalho.setAlignment(Element.ALIGN_LEFT);
                tituloSolicitacao.setAlignment(Element.ALIGN_CENTER);
                requisicaoDocument.add(logo);
                requisicaoDocument.add(cabecalho);
                requisicaoDocument.add(tituloSolicitacao);
                requisicaoDocument.add(espaco);

                // criar a tabela
                PdfPTable tabela1 = new PdfPTable(3); // setor, data, numero
                PdfPTable tabela2 = new PdfPTable(1); // justificativa
                PdfPTable tabela3 = new PdfPTable(1); // descricao
                PdfPTable tabela4 = new PdfPTable(1); // frase
                PdfPTable tabela5 = new PdfPTable(2); // credor, cnpj/cpf, valor
                PdfPTable tabela6 = new PdfPTable(3); // conta, agencia, nome conta
                PdfPTable tabela7 = new PdfPTable(2); // carimbo e assinatura, valor
                PdfPTable tabela8 = new PdfPTable(2); // subacao, nat. despesa
                PdfPTable tabela9 = new PdfPTable(2); // fonte recurso, valor
                PdfPTable tabela10 = new PdfPTable(3); // dados, carimbo, data
                PdfPTable tabela11 = new PdfPTable(3); // confirmação, carimbo, data
                PdfPTable tabela12 = new PdfPTable(3); // deferimento, carimbo, data

                // criar as colunas
                PdfPCell cellSetor = new PdfPCell(
                        new Phrase(
                                "Setor Requisitante",
                                FontFactory.getFont
                                        (FontFactory.TIMES_BOLD, 9)));               // tabela1
                PdfPCell cellDataRequisicao = new PdfPCell(new Phrase("Data da Requisição", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));     // tabela1
                PdfPCell cellNumeroRequisicao = new PdfPCell(new Phrase("Número da Requisição", FontFactory.getFont(FontFactory.TIMES_BOLD, 9))); // tabela1
                PdfPCell cellJustificativa = new PdfPCell(new Phrase("Justificativa", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));           // tabela2
                PdfPCell cellDescricao = new PdfPCell(new Phrase(                                                                                                // tabela3
                        "Descrição (Descrição do Material/Serviço, quantitativo, características e local de aplicação)",                                   // tabela3
                        FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                                                                                 // tabela3
                PdfPCell cellFrase = new PdfPCell(frase);                                                                                                        // tabela4
                PdfPCell cellCredor = new PdfPCell(new Phrase("Credor", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                         // tabela5
                PdfPCell cellCnpj = new PdfPCell(new Phrase("CNPJ/CPF", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                         // tabela5
                PdfPCell cellValor = new PdfPCell(new Phrase("Valor", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                         // tabela5
                PdfPCell cellContaBancaria = new PdfPCell(new Phrase("Conta Bancária", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));          // tabela6
                PdfPCell cellAgencia = new PdfPCell(new Phrase("Agência", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                       // tabela6
                PdfPCell cellNomeConta = new PdfPCell(new Phrase("Nome Conta", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));                  // tabela6
                PdfPCell cellCarimboAssinatura = new PdfPCell(new Phrase("Carimbo e Assinatura", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellSubacao = new PdfPCell(new Phrase("Subação", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellNaturezaDespesa = new PdfPCell(new Phrase("Natureza Despesa", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellFonteRecurso = new PdfPCell(new Phrase("Fonte Recurso", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellInformacoes = new PdfPCell(new Phrase("Informações Recurso Financeiro", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellData = new PdfPCell(new Phrase("Data", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellSetorLic = new PdfPCell(new Phrase("Informações Setor de Licitação", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));
                PdfPCell cellDiretor = new PdfPCell(new Phrase("Informações Diretor Administração", FontFactory.getFont(FontFactory.TIMES_BOLD, 9)));

                // editar as colunas
                cellSetor.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellDataRequisicao.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellNumeroRequisicao.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellJustificativa.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellDescricao.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellFrase.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellCredor.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellCnpj.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellContaBancaria.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellAgencia.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellNomeConta.setHorizontalAlignment(Element.ALIGN_LEFT);

                // editar as tabelas
                // tabela 1
                tabela1.setTotalWidth(580);
                tabela1.setHeaderRows(1);
                tabela1.setLockedWidth(true);
                // tabela 2
                tabela2.setTotalWidth(580);
                tabela2.setHeaderRows(1);
                tabela2.setLockedWidth(true);
                // tabela 3
                tabela3.setTotalWidth(580);
                tabela3.setHeaderRows(1);
                tabela3.setLockedWidth(true);
                // tabela 4
                tabela4.setTotalWidth(580);
                tabela4.setHeaderRows(1);
                tabela4.setLockedWidth(true);
                // tabela 5
                tabela5.setTotalWidth(580);
                tabela5.setHeaderRows(1);
                tabela5.setLockedWidth(true);
                // tabela 6
                tabela6.setTotalWidth(580);
                tabela6.setHeaderRows(1);
                tabela6.setLockedWidth(true);
                // tabela 7
                tabela7.setTotalWidth(580);
                tabela7.setHeaderRows(1);
                tabela7.setLockedWidth(true);
                // tabela 8
                tabela8.setTotalWidth(580);
                tabela8.setHeaderRows(1);
                tabela8.setLockedWidth(true);
                // tabela 9
                tabela9.setTotalWidth(580);
                tabela9.setHeaderRows(1);
                tabela9.setLockedWidth(true);
                // tabela 10
                tabela10.setTotalWidth(580);
                tabela10.setHeaderRows(1);
                tabela10.setLockedWidth(true);
                // tabela 11
                tabela11.setTotalWidth(580);
                tabela11.setHeaderRows(1);
                tabela11.setLockedWidth(true);
                // tabela 12
                tabela12.setTotalWidth(580);
                tabela12.setHeaderRows(1);
                tabela12.setLockedWidth(true);

                System.out.println();

                /*if (sap.getTipoEmpenhosolicitado().getCodMod() == 2 && principalController.cr)*/

                // adicionar elementos na tabela
                tabela1.addCell(cellSetor);
                tabela1.addCell(cellDataRequisicao);
                tabela1.addCell(cellNumeroRequisicao);
                tabela2.addCell(cellJustificativa);
                tabela3.addCell(cellDescricao);
                tabela4.addCell(cellFrase);
                tabela5.addCell(cellCredor);
                tabela5.addCell(cellCnpj);
                tabela6.addCell(cellContaBancaria);
                tabela6.addCell(cellAgencia);
                tabela6.addCell(cellNomeConta);
                tabela7.addCell(cellCarimboAssinatura);
                tabela7.addCell(cellValor);
                tabela8.addCell(cellSubacao);
                tabela8.addCell(cellNaturezaDespesa);
                tabela9.addCell(cellFonteRecurso);
                tabela9.addCell(cellValor);
                tabela10.addCell(cellInformacoes);
                tabela10.addCell(cellCarimboAssinatura);
                tabela10.addCell(cellData);
                tabela11.addCell(cellSetorLic);
                tabela11.addCell(cellCarimboAssinatura);
                tabela11.addCell(cellData);
                tabela12.addCell(cellDiretor);
                tabela12.addCell(cellCarimboAssinatura);
                tabela12.addCell(cellData);

                List<Requisicao> requisicoes = pegarRequisicao();

                Requisicao req = requisicaoComboBox.getValue();

                // adicionar elementos nas células das tabelas

                // tabela1 setor, data, numero
                tabela1.addCell(req.getSetorSolicitante().getNome());
                tabela1.addCell(dtf.format(req.getDataRequisicao()));
                tabela1.addCell(String.valueOf(req.getNumeroRequisicao())
                        + "/" + req.getDataRequisicao().getYear());

                // tabela2
                tabela2.addCell(req.getJustificativa());

                // tabela3
                tabela3.addCell(req.getDescricao());

                // tabela4
                for (int i = 0; i < 1; i++) {
                    tabela3.addCell(cellFrase);
                }

                // tabela5
                tabela5.addCell(req.getCredor().getNomeCredor());
                tabela5.addCell(req.getCredor().getIdentificacao());
                /*tabela5.addCell(nf.format(req.getValorRequisicao()));*/

                // tabela6
                tabela6.addCell(req.getCredor().getConta());
                tabela6.addCell(req.getCredor().getAgencia());
                tabela6.addCell(req.getCredor().getNomeConta());

                // tabela7
                tabela7.addCell("\n\n\n");
                tabela7.addCell(nf.format(req.getValorRequisicao()));

                // tabela8
                tabela8.addCell(req.getSubacao().getSubacao()
                        + "| " + req.getSubacao().getDescricao());
                tabela8.addCell(req.getNaturezaDespesa().getCodDespesa()
                        + "| " + req.getNaturezaDespesa().getDescricao());

                // tabela9
                tabela9.addCell(req.getFonteRecurso().getRelacaoFontes()
                        + "| " + req.getFonteRecurso().getDescricao());
                tabela9.addCell("\n");

                tabela10.addCell(dados);
                tabela10.addCell("");
                tabela10.addCell("");

                // tabela 11
                tabela11.addCell(setorLic);
                tabela11.addCell("");
                tabela11.addCell("");

                // tabela 11
                tabela12.addCell(diretorDef);
                tabela12.addCell("\n");
                tabela12.addCell("");

                // adicionar elementos no documento
                requisicaoDocument.add(tabela1);
                requisicaoDocument.add(tabela2);
                requisicaoDocument.add(tabela3);
                requisicaoDocument.add(tabela4);
                requisicaoDocument.add(frase2);
                requisicaoDocument.add(espaco);
                requisicaoDocument.add(tabela5);
                requisicaoDocument.add(tabela6);
                requisicaoDocument.add(tabela7);
                requisicaoDocument.add(indicacao);
                requisicaoDocument.add(espaco);
                requisicaoDocument.add(tabela8);
                requisicaoDocument.add(tabela9);
                requisicaoDocument.add(confirmacao);
                requisicaoDocument.add(espaco);
                requisicaoDocument.add(tabela10);
                requisicaoDocument.add(setor);
                requisicaoDocument.add(espaco);
                requisicaoDocument.add(tabela11);
                requisicaoDocument.add(diretor);
                requisicaoDocument.add(espaco);
                requisicaoDocument.add(tabela12);

                // fechar e abrir documento
                requisicaoDocument.close();
                abrirArquivo(sapFile);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Sap> pegarSap() {

        List<Sap> saps = principalController.pegarSaps();

        List<Sap> retorno = new ArrayList<>();


        return retorno;

    }

    public List<Requisicao> pegarRequisicao() {
        List<Requisicao> requisicoes = principalController.pegarRequisicao();

        List<Requisicao> retorno = new ArrayList<>();

        for (int i = 0; i < 0; i++) {
            retorno.add(requisicoes.get(i));
        }

        return retorno;
    }

    public void abrirArquivo(File file) {
        String SO = System.getProperty("os.name").toLowerCase();
        Runtime r = Runtime.getRuntime();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(file);
                    } else if (SO.indexOf("linux") >= 0) {
                        r.exec("xdg-open " + file.getAbsolutePath());
                    } else {
                        r.exec("rundll32 url.dll,FileProtocolHandler "
                                + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
