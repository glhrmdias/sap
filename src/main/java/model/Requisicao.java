package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Requisicao {

    public int codRequisicao;
    public int numeroRequisicao;
    public Unidade setorSolicitante;
    public Credor credor;
    public Subacao subacao;
    public FonteRecurso fonteRecurso;
    public NaturezaDespesa naturezaDespesa;
    public LocalDate dataRequisicao;
    public float valorRequisicao;
    public String tipo;
    public String descricao;
    public String justificativa;

    public Requisicao (int numeroRequisicao, Unidade setorSolicitante, Credor credor, String tipo,
                       Subacao subacao, FonteRecurso fonteRecurso, NaturezaDespesa naturezaDespesa, LocalDate dataRequisicao, float valorRequisicao, String descricao, String justificativa) {
        this.numeroRequisicao = numeroRequisicao;
        this.setorSolicitante = setorSolicitante;
        this.credor = credor;
        this.subacao = subacao;
        this.fonteRecurso = fonteRecurso;
        this.naturezaDespesa = naturezaDespesa;
        this.dataRequisicao = dataRequisicao;
        this.valorRequisicao = valorRequisicao;
        this.tipo = tipo;
        this.descricao = descricao;
        this.justificativa = justificativa;
    }

    public Requisicao() {

    }

    public String getDescricao() {
        return descricao;
    }

    public String setDescricao(String descricao) {
        this.descricao = descricao;
        return descricao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String setJustificativa(String justificativa) {
        this.justificativa = justificativa;
        return justificativa;
    }

    public LocalDate getDataRequisicao() {
        return dataRequisicao;
    }

    public LocalDate setDataRequisicao(LocalDate dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
        return dataRequisicao;
    }

    public int getNumeroRequisicao() {
        return numeroRequisicao;
    }

    public int setNumeroRequisicao(int numeroRequisicao) {
        this.numeroRequisicao = numeroRequisicao;
        return numeroRequisicao;
    }

    public String getTipo() {
        return tipo;
    }

    public String setTipo(String tipo) {
        this.tipo = tipo;
        return tipo;
    }

    public Credor getCredor() {
        return credor;
    }

    public Credor setCredor(Credor credor) {
        this.credor = credor;
        return credor;
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


    public Unidade setSetorSolicitante(Unidade setorSolicitante) {
        this.setorSolicitante = setorSolicitante;
        return setorSolicitante;
    }

    public float getValorRequisicao() {
        return valorRequisicao;
    }

    public float setValorRequisicao(float valorRequisicao) {
        this.valorRequisicao = valorRequisicao;
        return valorRequisicao;
    }

    public Unidade getSetorSolicitante() {
        return setorSolicitante;
    }

    public int getCodRequisicao() {
        return codRequisicao;
    }

    public void setCodRequisicao(int codRequisicao) {
        this.codRequisicao = codRequisicao;
    }

    @Override
    public String toString() {
        return String.valueOf( "Requisição: " + numeroRequisicao);
    }


}
