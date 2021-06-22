package filter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public enum FiltroUSUARIO {

    MATRICULA ("Matricula"),
    NOME ("Nome"),
    SETOR ("Setor");

    public final String descricao;

    FiltroUSUARIO(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
