package DAO;

import filter.FiltroSUBACAO;
import model.Subacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */


public class SubacaoDAO {

    public boolean cadastrandoSubacao(Subacao subacao) {

        Conexao con = new Conexao();

        String sql = "INSERT into subacao (subacao, descricao)"
                    + " VALUES('" + subacao.getSubacao() + "', '"
                + subacao.getDescricao() + "');";

        System.out.println(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
           return false;
        }

    }

    public boolean atualizandoSubacao(Subacao subacao) {

        Conexao con = new Conexao();

        String sql = "UPDATE subacao set descricao = '" + subacao.getDescricao()
                + "', subacao = '" + subacao.getSubacao() + "' WHERE subacao = '" + subacao.getSubacao() + "'";

        System.out.println(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Subacao> filtrandoSubacao(FiltroSUBACAO filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados banco = new BancoDados();

        String sql = "SELECT s.*, u.subacao from  subacao s, subacao u WHERE u.subacao = s.subacao";

        if (filtro == FiltroSUBACAO.SUBACAO) {
            sql += " AND s.subacao like '%"+ texto + "%'";
        } else if (filtro == FiltroSUBACAO.DESCRICAO) {
            sql += " AND s.descricao like '%"+ texto + "%'";
        }

        System.out.println(sql);

        List<Subacao> subacaos = new ArrayList<Subacao>();

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String subacao = resultSet.getString("subacao");
                    String descricao = resultSet.getString("descricao");
                    /*int subacao = resultSet.getInt("subacao");*/
                    Subacao subac = new Subacao();
                    /*subac.setCodSubacao(codSubacao);*/
                    subac.setDescricao(descricao);
                    subac.setSubacao(subacao);
                    subacaos.add(subac);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return subacaos;
    }


}
