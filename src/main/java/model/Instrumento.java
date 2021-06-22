package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Instrumento {

    int codInstrumento;
    String descricao;

    @Override
    public String toString() {
        return descricao;
    }

    public int getCodInstrumento() {
        return codInstrumento;
    }

    public void setCodInstrumento(int codInstrumento) {
        this.codInstrumento = codInstrumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
