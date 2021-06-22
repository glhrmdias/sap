package filter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public enum FiltroSAP {

    SETOR ("Setor"),
    CREDOR ("Credor"),
    NATUREZA ("Natureza da Despesa"),
    SUBACAO ("Subação");

    private final String descricao;

    FiltroSAP(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
