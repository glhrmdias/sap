package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class UnidadeOrcamentaria {

    private int codUo;
    String descricao;

    @Override
    public String toString() {
        return codUo + "| " + descricao;
    }

    public int getCodUo() {
        return codUo;
    }

    public void setCodUo(int codUo) {
        this.codUo = codUo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
