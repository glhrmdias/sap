package DAO;

import filter.FiltroNATUREZA;
import model.GrupoFinanceiro;
import model.NaturezaDespesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class NaturezaDAO {

    public boolean cadastrandoNatureza(NaturezaDespesa naturezaDespesa) {

        Conexao con = new Conexao();

        String sql = "INSERT into natureza_despesa (cod_despesa, descricao, cod_gf)"
                + "VALUES('" + naturezaDespesa.getCodDespesa() + "', '" + naturezaDespesa.getDescricao() + "', " + naturezaDespesa.getGrupoFinanceiro().getCodGf() + ");";

        System.out.println(sql);
        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizandoNatureza(NaturezaDespesa naturezaDespesa) {

        Conexao con = new Conexao();

        String sql = "UPDATE natureza_despesa set cod_despesa = '"
                + naturezaDespesa.getCodDespesa() + "', descricao = '"
                + naturezaDespesa.getDescricao() + "', cod_gf = '"
                + naturezaDespesa.getGrupoFinanceiro().getCodGf() + "' WHERE cod_despesa = '" + naturezaDespesa.getCodDespesa() + "'";

        System.out.println(sql);
        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<NaturezaDespesa> filtrarNatureza(FiltroNATUREZA filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados banco = new BancoDados();

        String sql = "SELECT n.*, g.cod_gf from natureza_despesa n, grupo_financeiro g WHERE n.cod_gf = g.cod_gf";

        if (filtro == FiltroNATUREZA.CODIGO) {
            sql += " AND n.cod_despesa like '%" + texto + "%'";
        }

        List<NaturezaDespesa> naturezasDespesa = new ArrayList<NaturezaDespesa>();

        List<GrupoFinanceiro> grupoFinanceiros = banco.getGrupoFinanceiro();
        Map<Integer, GrupoFinanceiro> mapGrupoFinanceiro = new HashMap<>();

        for(GrupoFinanceiro grupoFinanceiro : grupoFinanceiros) {
            mapGrupoFinanceiro.put(grupoFinanceiro.getCodGf(), grupoFinanceiro);
        }

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String codDespesa = resultSet.getString("cod_despesa");
                    String descricao = resultSet.getString("descricao");
                    int codGf = resultSet.getInt("cod_gf");
                    NaturezaDespesa naturezaDespesa = new NaturezaDespesa();
                    naturezaDespesa.setCodDespesa(codDespesa);
                    naturezaDespesa.setDescricao(descricao);
                    naturezaDespesa.setGrupoFinanceiro(mapGrupoFinanceiro.get(codGf));
                    naturezasDespesa.add(naturezaDespesa);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return naturezasDespesa;


    }

}
