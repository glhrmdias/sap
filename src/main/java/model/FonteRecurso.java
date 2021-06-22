package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class FonteRecurso {

    int codFr;
    String descricao;
    String relacaoFontes;

    @Override
    public String toString() {
        return relacaoFontes + "| " +descricao;
    }

    public int getCodFr() {
        return codFr;
    }

    public void setCodFr(int codFr) {
        this.codFr = codFr;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRelacaoFontes() {
        return relacaoFontes;
    }

    public void setRelacaoFontes(String relacaoFontes) {
        this.relacaoFontes = relacaoFontes;
    }
}
