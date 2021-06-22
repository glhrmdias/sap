package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class TipoContrato {

    private int codTipoct;
    private String descricao;

    @Override
    public String toString() {
        return descricao;
    }

    public int getCodTipoct() {
        return codTipoct;
    }

    public void setCodTipoct(int codTipoct) {
        this.codTipoct = codTipoct;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
