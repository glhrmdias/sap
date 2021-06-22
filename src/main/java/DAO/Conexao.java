package DAO;

import java.sql.*;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Conexao {

    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    Conexao() {

        /*url = "jdbc:postgresql://localhost:5432/sistemasap";
        usuario = "postgres";
        senha = "Guigo1997";*/

        /*url = "jdbc:postgresql://172.19.212.95:5432/sap";
        usuario = "sap";
        senha = "sap";*/

        /*url = "jdbc:postgresql://172.19.212.96:5432/sap_dev";
        usuario = "sap";
        senha = "sap";*/

        url = "jdbc:postgresql://10.111.10.13:5432/sap_dev";
        usuario = "sap";
        senha = "sap";


        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            //System.out.printf("[BANCO DE DADOS] = CONEXÃO BEM SUCEDIDA COM \n" + url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[BANCO DE DADOS] = ERRO NA CONEXÃO");
        }
    }

    public int ExecutaSQL(String sql) {

        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet ExecutaSelect(String sql) {

        try {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void fecharConexao() {

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
