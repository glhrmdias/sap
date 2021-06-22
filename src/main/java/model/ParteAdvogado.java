package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ParteAdvogado {

    int codPa;
    String tipo;

    public ParteAdvogado() {

    }

    @Override
    public String toString() {
        return tipo;
    }

    public int getCodPa() {
        return codPa;
    }

    public void setCodPa(int codPa) {
        this.codPa = codPa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
