package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Subacao {

    private int codSubacao;
    private String subacao;
    private String descricao;

    public Subacao() {

    }

    @Override
    public String toString() {
        return subacao + " | " + descricao;
    }

    public Subacao(int codSubacao,
                   String descricao,
                   String subacao) {
        this.subacao = subacao;
        this.codSubacao = codSubacao;
        this.descricao = descricao;
    }

    public int getCodSubacao() {
        return codSubacao;
    }

    public String getSubacao() {
        return subacao;
    }

    public String setSubacao(String subacao) {
        this.subacao = subacao;
        return subacao;
    }

    public String getDescricao() {
        return descricao;
    }

    /*public void setCodSubacao(int codSubacao) {
        this.codSubacao = codSubacao;
    }*/

    public String setDescricao(String descricao) {
        this.descricao = descricao;
        return descricao;
    }
}
