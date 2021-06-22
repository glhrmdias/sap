package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class TipoCredor {

    private int codTcredor;
    private String tipo;

    public TipoCredor(int codTcredor, String tipo) {
        this.codTcredor = codTcredor;
        this.tipo = tipo;
    }

    public TipoCredor() {

    }

    @Override
    public String toString() {
        return tipo;
    }

    public int getCodTcredor() {
        return codTcredor;
    }

    public void setCodTcredor(int codTcredor) {
        this.codTcredor = codTcredor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
