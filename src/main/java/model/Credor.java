package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Credor {

    String identificacao;
    String nomeCredor;
    TipoCredor tipoCredor;
    String inscEstado;
    String inscMunicipio;
    String observacao;
    String cep;
    String endComercio;
    String bairro;
    String municipio;
    String uf;
    String pontoReferencia;
    String telefone;
    String telefoneCelular;
    String banco;
    String agencia;
    String conta;
    String nomeConta;
    String email;
    Credor credor;

    public Credor(String identificacao, String nomeCredor, TipoCredor tipoCredor, String inscEstado, String inscMunicipio, String observacao, String cep, String endComercio,
                  String bairro, String municipio, String uf, String pontoReferencia, String telefone, String telefoneCelular, String banco, String agencia,
                  String conta, String nomeConta, String email, Credor credor) {
        this.identificacao = identificacao;
        this.nomeCredor = nomeCredor;
        this.tipoCredor = tipoCredor;
        this.inscEstado = inscEstado;
        this.inscMunicipio = inscMunicipio;
        this.observacao = observacao;
        this.cep = cep;
        this.endComercio = endComercio;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
        this.pontoReferencia = pontoReferencia;
        this.telefone = telefone;
        this.telefoneCelular = telefoneCelular;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.nomeConta = nomeConta;
        this.email = email;
        this.credor = credor;
    }

    public Credor() {

    }

    @Override
    public String toString() {
        return identificacao + "| " +nomeCredor;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public String setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
        return identificacao;
    }

    public String getNomeCredor() {
        return nomeCredor;
    }

    public String setNomeCredor(String nomeCredor) {
        this.nomeCredor = nomeCredor;
        return nomeCredor;
    }

    public TipoCredor getTipoCredor() {
        return tipoCredor;
    }

    public String getInscEstado() {
        return inscEstado;
    }

    public String getInscMunicipio() {
        return inscMunicipio;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getCep() {
        return cep;
    }

    public String getEndComercio() {
        return endComercio;
    }

    public String getBairro() {
        return bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public String getEmail() {
        return email;
    }

    public TipoCredor setTipoCredor(TipoCredor tipoCredor) {
        this.tipoCredor = tipoCredor;
        return tipoCredor;
    }

    public String setInscEstado(String inscEstado) {
        this.inscEstado = inscEstado;
        return inscEstado;
    }

    public String setInscMunicipio(String inscMunicipio) {
        this.inscMunicipio = inscMunicipio;
        return inscMunicipio;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String setCep(String cep) {
        this.cep = cep;
        return cep;
    }

    public String setEndComercio(String endComercio) {
        this.endComercio = endComercio;
        return endComercio;
    }

    public String setBairro(String bairro) {
        this.bairro = bairro;
        return bairro;
    }

    public String setMunicipio(String municipio) {
        this.municipio = municipio;
        return municipio;
    }

    public String setUf(String uf) {
        this.uf = uf;
        return uf;
    }

    public String setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
        return pontoReferencia;
    }

    public String setTelefone(String telefone) {
        this.telefone = telefone;
        return telefone;
    }

    public String setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
        return telefoneCelular;
    }

    public String setBanco(String banco) {
        this.banco = banco;
        return banco;
    }

    public String setAgencia(String agencia) {
        this.agencia = agencia;
        return agencia;
    }

    public String setConta(String conta) {
        this.conta = conta;
        return conta;
    }

    public String setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
        return nomeConta;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }
}
