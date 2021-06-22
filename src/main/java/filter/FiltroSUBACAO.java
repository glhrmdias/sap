package filter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public enum FiltroSUBACAO {

    SUBACAO ("Subação"),
    DESCRICAO ("Descrição");

    public final String descricao;

    FiltroSUBACAO(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
