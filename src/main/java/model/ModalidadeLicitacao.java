package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class ModalidadeLicitacao {

    int codMod;
    String descricao;

    @Override
    public String toString() {
        return codMod + "| " +descricao;
    }

    public int getCodMod() {
        return codMod;
    }

    public void setCodMod(int codMod) {
        this.codMod = codMod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
