package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class GrupoFinanceiro {

    int codGf;
    String descricao;


    public GrupoFinanceiro(int codGf, String descricao) {
        this.codGf = codGf;
        this.descricao = descricao;
    }

    public GrupoFinanceiro() {

    }


    @Override
    public String toString() {
        return descricao;
    }

    public int getCodGf() {
        return codGf;
    }

    public void setCodGf(int codGf) {
        this.codGf = codGf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
