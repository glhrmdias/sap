package DAO;

import filter.FiltroUSUARIO;
import model.Unidade;
import model.Usuario;

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
public class UsuarioDAO {

    public boolean cadastrandoUsuario(Usuario usuario) {

        Conexao con = new Conexao();

        String sql = "INSERT into usuario (matricula, nome, setor, senha)"
                + " VALUES('" + usuario.getMatricula() + "', '" + usuario.getNome() + "', '" + usuario.getSetor().getCodUnidade() + "', '"
                + usuario.getSenha() + "');";

        System.out.println(sql);

        /*con.ExecutaSQL(sql);*/

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean atualizandoUsuario(Usuario usuario) {

        Conexao con = new Conexao();

        String sql = "UPDATE usuario set matricula = '" + usuario.getMatricula()
                + "', nome = '" + usuario.getNome()  + "', setor = '" + usuario.getSetor().getCodUnidade()
                + "', senha = '" + usuario.getSenha() + "' WHERE matricula = '" + usuario.getMatricula() + "'";

        System.out.println(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Usuario> filtrarUsuarios(FiltroUSUARIO filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados banco = new BancoDados();

        String sql = "SELECT u.*, s.nome from usuario u, unidade s WHERE u.setor = s.cod_unidade";

        if (filtro == FiltroUSUARIO.MATRICULA) {
            sql += " AND u.matricula like '%" + texto + "%'";
        } else if (filtro == FiltroUSUARIO.NOME) {
            sql += " AND u.nome like '%" + texto + "%'";
        } else if (filtro == FiltroUSUARIO.SETOR) {
            sql += " AND s.nome like '%" + texto + "%'";
        }

        List<Usuario> usuarios = new ArrayList<Usuario>();

        List<Unidade> unidades = banco.getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        for(Unidade unidade : unidades) {
            mapUnidade.put(unidade.getCodUnidade(), unidade);
        }

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null ) {
            try {
                while (resultSet.next()) {
                    String matricula = resultSet.getString("matricula");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    int setor = resultSet.getInt("setor");
                    Usuario usu = new Usuario();
                    usu.setMatricula(matricula);
                    usu.setNome(nome);
                    usu.setSetor(mapUnidade.get(setor));
                    usu.setSenha(senha);
                    usuarios.add(usu);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return usuarios;
    }


}
