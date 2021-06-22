package filter;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public enum FiltroCREDOR {

    CNPJ ("CNPJ"),
    NOME ("Nome");

    public final String descricao;

    FiltroCREDOR(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
