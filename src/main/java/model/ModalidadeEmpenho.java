package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
 public class ModalidadeEmpenho {

    int codMod;
    String empenho;
    String descricao;

    @Override
    public String toString() {
        return empenho;
    }

    public int getCodMod() {
        return codMod;
    }

    public void setCodMod(int codMod) {
        this.codMod = codMod;
    }

    public String getEmpenho() {
        return empenho;
    }

    public void setEmpenho(String empenho) {
        this.empenho = empenho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
