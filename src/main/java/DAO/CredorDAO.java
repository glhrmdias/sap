package DAO;

import model.Credor;
import filter.FiltroCREDOR;
import model.TipoCredor;

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
public class CredorDAO {


    public boolean inserindoCredor (Credor credor) {
        Conexao con = new Conexao();

        String sql = "INSERT into credor (identificacao, tipo_credor, nome_credor, insc_estado, insc_municipio, cep, end_comercio, bairro, " +
                "municipio, uf, ponto_ref, tel_comercio, tel_celular, banco, agencia, conta_bancaria, nome_conta, email)" +
                " VALUES ('" + credor.getIdentificacao() + "', '" + credor.getTipoCredor().getCodTcredor() + "', '" + credor.getNomeCredor() + "', '"
                + credor.getInscEstado()+ "', '" +credor.getInscMunicipio()+ "', '" +credor.getCep()+ "', '" +credor.getEndComercio()+ "', '" +credor.getBairro()+ "', '"
                +credor.getMunicipio()+ "', '" +credor.getUf()+ "', '" +credor.getPontoReferencia()+ "', '" +credor.getTelefone()+ "', '" +credor.getTelefoneCelular()+ "', '"
                +credor.getBanco()+ "', '" +credor.getAgencia()+ "', '" +credor.getConta()+ "', '" +credor.getNomeConta()+ "', '" +credor.getEmail() +"');";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);
        con.fecharConexao();
        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizandoCredores (Credor credor) {

        Conexao con = new Conexao();

        String sql = "UPDATE credor set identificacao = '" + credor.getIdentificacao() + "', tipo_credor = '" + credor.getTipoCredor().getCodTcredor() + "', nome_credor = '" + credor.getNomeCredor() + "', " +
                "insc_estado = '" + credor.getInscEstado() + "', insc_municipio = '" + credor.getInscMunicipio() + "', cep = '" + credor.getCep() + "', end_comercio = '" + credor.getEndComercio() + "', bairro = '" + credor.getBairro() + "', " +
                "municipio = '" + credor.getMunicipio() + "', uf = '" + credor.getUf() + "', ponto_ref = '" + credor.getPontoReferencia() + "', " +
                "tel_comercio = '" + credor.getTelefone() + "', tel_celular = '" + credor.getTelefoneCelular() + "', banco = '" + credor.getBanco() + "', agencia = '" + credor.getAgencia() + "', " +
                "conta_bancaria = '" + credor.getConta() + "', nome_conta = '" + credor.getNomeConta() + "', email = '" + credor.getEmail()
                + "' WHERE identificacao = '" + credor.getIdentificacao() + "'";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);
        con.fecharConexao();
        if (res != 0) {
            return true;
        } else {
            return false;
        }


    }

    public List<Credor> filtrarCredor(FiltroCREDOR filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados bancoDados = new BancoDados();

        String sql = "SELECT c.*, c.identificacao from credor c WHERE c.identificacao = c.identificacao";

        if (filtro == FiltroCREDOR.CNPJ) {
            sql += " AND c.identificacao like '%" + texto + "%'";
        } else if (filtro == FiltroCREDOR.NOME) {
            sql += " AND c.nome_credor like '%" + texto + "%'";
        }

        List<Credor> credores = new ArrayList<Credor>();

        List<TipoCredor> tipoCredores = bancoDados.getTipoCredor();
        Map<Integer, TipoCredor> mapTipoCredor = new HashMap<>();

        for (TipoCredor tipoCredor : tipoCredores) {
            mapTipoCredor.put(tipoCredor.getCodTcredor(), tipoCredor);
        }

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String identificacao = resultSet.getString("identificacao");
                    String nomeCredor = resultSet.getString("nome_credor");
                    int tipoCredor = resultSet.getInt("tipo_credor");
                    String inscricaoEstado = resultSet.getString("insc_estado");
                    String inscricaoMunicipio = resultSet.getString("insc_municipio");
                    String cep = resultSet.getString("cep");
                    String endereco = resultSet.getString("end_comercio");
                    String bairro = resultSet.getString("bairro");
                    String municipio = resultSet.getString("municipio");
                    String uf = resultSet.getString("uf");
                    String pontoReferencia = resultSet.getString("ponto_ref");
                    String telefoneComercio = resultSet.getString("tel_comercio");
                    String telefoneCelular = resultSet.getString("tel_celular");
                    String banco = resultSet.getString("banco");
                    String contaBancaria = resultSet.getString("conta_bancaria");
                    String agencia = resultSet.getString("agencia");
                    String nomeConta = resultSet.getString("nome_conta");
                    String email = resultSet.getString("email");
                    Credor credor = new Credor();
                    credor.setIdentificacao(identificacao);
                    credor.setTipoCredor(mapTipoCredor.get(tipoCredor));
                    credor.setNomeCredor(nomeCredor);
                    credor.setInscEstado(inscricaoEstado);
                    credor.setInscMunicipio(inscricaoMunicipio);
                    credor.setCep(cep);
                    credor.setEndComercio(endereco);
                    credor.setBairro(bairro);
                    credor.setMunicipio(municipio);
                    credor.setUf(uf);
                    credor.setPontoReferencia(pontoReferencia);
                    credor.setTelefone(telefoneComercio);
                    credor.setTelefoneCelular(telefoneCelular);
                    credor.setBanco(banco);
                    credor.setConta(contaBancaria);
                    credor.setAgencia(agencia);
                    credor.setNomeConta(nomeConta);
                    credor.setEmail(email);
                    credores.add(credor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return credores;


    }
}
