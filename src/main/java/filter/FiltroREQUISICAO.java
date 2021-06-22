package filter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public enum FiltroREQUISICAO {

    UNIDADE ("Setor"),
    CREDOR ("Credor");

    public final String descricao;

    FiltroREQUISICAO(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
