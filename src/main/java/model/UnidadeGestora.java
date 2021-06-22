package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class UnidadeGestora {

    private int codUg;
    private String sigla;
    private int gestao;

    @Override
    public String toString() {
        return codUg + "| "+sigla;
    }

    public int getCodUg() {
        return codUg;
    }

    public void setCodUg(int codUg) {
        this.codUg = codUg;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getGestao() {
        return gestao;
    }

    public void setGestao(int gestao) {
        this.gestao = gestao;
    }
}
