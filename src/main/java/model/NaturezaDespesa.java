package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 *
 */
public class NaturezaDespesa {


    String codDespesa;
    String descricao;
    GrupoFinanceiro grupoFinanceiro;

    public NaturezaDespesa() {

    }

    @Override
    public String toString() {
        return codDespesa+ "| " +descricao;
    }

    public NaturezaDespesa(String descricao, GrupoFinanceiro grupoFinanceiro, String codDespesa) {
        this.descricao = descricao;
        this.grupoFinanceiro = grupoFinanceiro;
        this.codDespesa = codDespesa;
    }

    public String getCodDespesa() {
        return codDespesa;
    }

    public String setCodDespesa(String codDespesa) {
        this.codDespesa = codDespesa;
        return codDespesa;
    }

    public GrupoFinanceiro getGrupoFinanceiro() {
        return grupoFinanceiro;
    }

    public GrupoFinanceiro setGrupoFinanceiro(GrupoFinanceiro grupoFinanceiro) {
        this.grupoFinanceiro = grupoFinanceiro;
        return grupoFinanceiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public String setDescricao(String descricao) {
        this.descricao = descricao;
        return descricao;
    }
}
