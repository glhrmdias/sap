package model;

import java.time.LocalDate;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Sap {

    private int codSap;
    private int numeroSap;
    /*private String requisicaoCompra;*/
    private LocalDate Datasolicitacao;
    private Unidade setorSolicitante;
    private ModalidadeEmpenho tipoEmpenhosolicitado;
    private UnidadeGestora unidadeGestora;
    private Evento evento;
    public float valor;
    private String valor_janeiro;
    private String valor_fevereiro;
    private String valor_marco;
    private String valor_abril;
    private String valor_maio;
    private String valor_junho;
    private String valor_julho;
    private String valor_agosto;
    private String valor_setembro;
    private String valor_outubro;
    private String valor_novembro;
    private String valor_dezembro;
    private Credor credor;
    private UnidadeOrcamentaria unidadeOrcamentaria;
    private Subacao subacao;
    private FonteRecurso fonteRecurso;
    private NaturezaDespesa naturezaDespesa;
    private ModalidadeLicitacao modalidadeLicitacao;
    private String processo;
    private TipoContrato tipoContrato;
    private Instrumento instrumento;

    private String numeroAutos;
    private ParteAdvogado parteAdvogado;
    private String nomeParte;
    private String dataSocilitacao;
    private LocalDate dataMandado;
    private LocalDate dataIntimacaoIprev;
    private LocalDate dataVencimento;
    private String prazoPagamento;

    private String historico;


    public Sap(String datasolicitacao, Unidade setorSolicitante, ModalidadeEmpenho tipoEmpenhosolicitado,
               UnidadeGestora unidadeGestora, Evento evento, float valor, Credor credor, UnidadeOrcamentaria unidadeOrcamentaria, Subacao subacao,
               FonteRecurso fonteRecurso, NaturezaDespesa naturezaDespesa, ModalidadeLicitacao modalidadeLicitacao, String processo, TipoContrato tipoContrato, String numeroAutos,
               ParteAdvogado parteAdvogado, String nomeParte, LocalDate dataMandado, LocalDate dataIntimacaoIprev, LocalDate dataVencimento, String prazoPagamento, String historico, Instrumento instrumento, int numeroSap,
               String valor_janeiro, String valor_fevereiro, String valor_marco, String valor_abril, String valor_maio, String valor_junho, String valor_julho,
               String valor_agosto, String valor_setembro, String valor_outubro, String valor_novembro, String valor_dezembro) {
        /*this.requisicaoCompra = requisicaoCompra;*/
        this.dataSocilitacao = datasolicitacao;
        this.setorSolicitante = setorSolicitante;
        this.tipoEmpenhosolicitado = tipoEmpenhosolicitado;
        this.unidadeGestora = unidadeGestora;
        this.evento = evento;
        this.valor = valor;
        this.credor = credor;
        this.unidadeOrcamentaria = unidadeOrcamentaria;
        this.subacao = subacao;
        this.fonteRecurso = fonteRecurso;
        this.naturezaDespesa = naturezaDespesa;
        this.modalidadeLicitacao = modalidadeLicitacao;
        this.processo = processo;
        this.tipoContrato = tipoContrato;
        this.numeroAutos = numeroAutos;
        this.parteAdvogado = parteAdvogado;
        this.nomeParte = nomeParte;
        this.dataMandado = dataMandado;
        this.dataIntimacaoIprev = dataIntimacaoIprev;
        this.dataVencimento = dataVencimento;
        this.prazoPagamento = prazoPagamento;
        this.historico = historico;
        this.instrumento = instrumento;
        this.numeroSap = numeroSap;
        this.valor_janeiro = valor_janeiro;
        this.valor_fevereiro = valor_fevereiro;
        this.valor_marco = valor_marco;
        this.valor_abril = valor_abril;
        this.valor_maio = valor_maio;
        this.valor_junho = valor_junho;
        this.valor_julho = valor_julho;
        this.valor_agosto = valor_agosto;
        this.valor_setembro = valor_setembro;
        this.valor_outubro = valor_outubro;
        this.valor_novembro = valor_novembro;
        this.valor_dezembro = valor_dezembro;
    }

    public Sap() {

    }

    /*public String getRequisicaoCompra() {
        return requisicaoCompra;
    }

    public void setRequisicaoCompra(String requisicaoCompra) {
        this.requisicaoCompra = requisicaoCompra;
    }*/

    public LocalDate getDatasolicitacao() {
        return Datasolicitacao;
    }

    public LocalDate setDatasolicitacao(LocalDate datasolicitacao) {
        Datasolicitacao = datasolicitacao;
        return datasolicitacao;
    }

    public Unidade getSetorSolicitante() {
        return setorSolicitante;
    }

    public Unidade setSetorSolicitante(Unidade setorSolicitante) {
        this.setorSolicitante = setorSolicitante;
        return null;
    }

    public ModalidadeEmpenho getTipoEmpenhosolicitado() {
        return tipoEmpenhosolicitado;
    }

    public ModalidadeEmpenho setTipoEmpenhosolicitado(ModalidadeEmpenho tipoEmpenhosolicitado) {
        this.tipoEmpenhosolicitado = tipoEmpenhosolicitado;
        return tipoEmpenhosolicitado;
    }

    public UnidadeGestora getUnidadeGestora() {
        return unidadeGestora;
    }

    public UnidadeGestora setUnidadeGestora(UnidadeGestora unidadeGestora) {
        this.unidadeGestora = unidadeGestora;
        return unidadeGestora;
    }

    public Evento getEvento() {
        return evento;
    }

    public Evento setEvento(Evento evento) {
        this.evento = evento;
        return evento;
    }

    public float getValor() {
        return valor;
    }

    public float setValor(float valor) {
        this.valor = valor;
        return valor;
    }

    public Credor getCredor() {
        return credor;
    }

    public Credor setCredor(Credor credor) {
        this.credor = credor;
        return credor;
    }

    public UnidadeOrcamentaria getUnidadeOrcamentaria() {
        return unidadeOrcamentaria;
    }

    public UnidadeOrcamentaria setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
        this.unidadeOrcamentaria = unidadeOrcamentaria;
        return unidadeOrcamentaria;
    }

    public Subacao getSubacao() {
        return subacao;
    }

    public Subacao setSubacao(Subacao subacao) {
        this.subacao = subacao;
        return subacao;
    }

    public FonteRecurso getFonteRecurso() {
        return fonteRecurso;
    }

    public FonteRecurso setFonteRecurso(FonteRecurso fonteRecurso) {
        this.fonteRecurso = fonteRecurso;
        return fonteRecurso;
    }

    public NaturezaDespesa getNaturezaDespesa() {
        return naturezaDespesa;
    }

    public NaturezaDespesa setNaturezaDespesa(NaturezaDespesa naturezaDespesa) {
        this.naturezaDespesa = naturezaDespesa;
        return naturezaDespesa;
    }

    public ModalidadeLicitacao getModalidadeLicitacao() {
        return modalidadeLicitacao;
    }

    public ModalidadeLicitacao setModalidadeLicitacao(ModalidadeLicitacao modalidadeLicitacao) {
        this.modalidadeLicitacao = modalidadeLicitacao;
        return null;
    }

    public String getProcesso() {
        return processo;
    }

    public String setProcesso(String processo) {
        this.processo = processo;
        return processo;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public TipoContrato setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
        return null;
    }

    public String getNumeroAutos() {
        return numeroAutos;
    }

    public String setNumeroAutos(String numeroAutos) {
        this.numeroAutos = numeroAutos;
        return numeroAutos;
    }

    public ParteAdvogado getParteAdvogado() {
        return parteAdvogado;
    }

    public ParteAdvogado setParteAdvogado(ParteAdvogado parteAdvogado) {
        this.parteAdvogado = parteAdvogado;
        return parteAdvogado;
    }

    public String getNomeParte() {
        return nomeParte;
    }

    public String setNomeParte(String nomeParte) {
        this.nomeParte = nomeParte;
        return nomeParte;
    }

    public LocalDate getDataMandado() {
        return dataMandado;
    }

    public LocalDate setDataMandado(LocalDate dataMandado) {
        this.dataMandado = dataMandado;
        return dataMandado;
    }

    public LocalDate getDataIntimacaoIprev() {
        return dataIntimacaoIprev;
    }

    public LocalDate setDataIntimacaoIprev(LocalDate dataIntimacaoIprev) {
        this.dataIntimacaoIprev = dataIntimacaoIprev;
        return dataIntimacaoIprev;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        return dataVencimento;
    }

    public String getPrazoPagamento() {
        return prazoPagamento;
    }

    public String setPrazoPagamento(String prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
        return prazoPagamento;
    }

    public String getHistorico() {
        return historico;
    }

    public String setHistorico(String historico) {
        this.historico = historico;
        return historico;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public Instrumento setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
        return instrumento;
    }

    public int getNumeroSap() {
        return numeroSap;
    }

    public int setNumeroSap(int  numeroSap) {
        this.numeroSap = numeroSap;
        return numeroSap;
    }

    /*public String getDataSocilitacao() {
        return dataSocilitacao;
    }

    public void setDataSocilitacao(String dataSocilitacao) {
        this.dataSocilitacao = dataSocilitacao;
    }*/

    public void setCodSap(int codSap) {
        this.codSap = codSap;
    }

    public int getCodSap() {
        return codSap;
    }

    public String getValor_janeiro() {
        return valor_janeiro;
    }

    public String setValor_janeiro(String valor_janeiro) {
        this.valor_janeiro = valor_janeiro;
        return valor_janeiro;
    }

    public String getValor_fevereiro() {
        return valor_fevereiro;
    }

    public String setValor_fevereiro(String valor_fevereiro) {
        this.valor_fevereiro = valor_fevereiro;
        return valor_fevereiro;
    }

    public String getValor_marco() {
        return valor_marco;
    }

    public String setValor_marco(String valor_marco) {
        this.valor_marco = valor_marco;
        return valor_marco;
    }

    public String getValor_abril() {
        return valor_abril;
    }

    public String setValor_abril(String valor_abril) {
        this.valor_abril = valor_abril;
        return valor_abril;
    }

    public String getValor_maio() {
        return valor_maio;
    }

    public String setValor_maio(String valor_maio) {
        this.valor_maio = valor_maio;
        return valor_maio;
    }

    public String getValor_junho() {
        return valor_junho;
    }

    public String setValor_junho(String valor_junho) {
        this.valor_junho = valor_junho;
        return valor_junho;
    }

    public String getValor_julho() {
        return valor_julho;
    }

    public String setValor_julho(String valor_julho) {
        this.valor_julho = valor_julho;
        return valor_julho;
    }

    public String getValor_agosto() {
        return valor_agosto;
    }

    public String setValor_agosto(String valor_agosto) {
        this.valor_agosto = valor_agosto;
        return valor_agosto;
    }

    public String getValor_setembro() {
        return valor_setembro;
    }

    public String setValor_setembro(String valor_setembro) {
        this.valor_setembro = valor_setembro;
        return valor_setembro;
    }

    public String getValor_outubro() {
        return valor_outubro;
    }

    public String setValor_outubro(String valor_outubro) {
        this.valor_outubro = valor_outubro;
        return valor_outubro;
    }

    public String getValor_novembro() {
        return valor_novembro;
    }

    public String setValor_novembro(String valor_novembro) {
        this.valor_novembro = valor_novembro;
        return valor_novembro;
    }

    public String getValor_dezembro() {
        return valor_dezembro;
    }

    public String setValor_dezembro(String valor_dezembro) {
        this.valor_dezembro = valor_dezembro;
        return valor_dezembro;
    }

    @Override
    public String toString() {
        return String.valueOf("SAP: "+ numeroSap);
    }
}
