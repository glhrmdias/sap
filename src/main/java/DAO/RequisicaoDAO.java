package DAO;

import filter.FiltroSAP;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class RequisicaoDAO {

    public boolean inserindoRequisicao (Requisicao requisicao) {

        Conexao con = new Conexao();

        String sql = "INSERT into requisicao (data_requisicao, sigla_setor, valor, credor, subacao, natureza_despesa, fonte_recurso, descricao, justificativa)"
                + " VALUES ('" + requisicao.getDataRequisicao() + "', '" + requisicao.getSetorSolicitante().getCodUnidade() + "', '"
                + requisicao.getValorRequisicao() + "', '" + requisicao.getCredor().getIdentificacao() + "', '" + requisicao.getSubacao().getSubacao() +  "', '" + requisicao.getNaturezaDespesa().getCodDespesa() + "', '"
                + requisicao.getFonteRecurso().getCodFr() + "', '" + requisicao.getDescricao() + "', '" + requisicao.getJustificativa() + "');";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);
        con.fecharConexao();


        if (res != 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean atualizarRequisicao(Requisicao requisicao) {

        Conexao con = new Conexao();

        String sql = "UPDATE requisicao set data_requisicao = '" + requisicao.getDataRequisicao() + "', "
                + "sigla_setor = '" + requisicao.getSetorSolicitante().getCodUnidade() + "', valor = '" + requisicao.getValorRequisicao() + "', credor = '" + requisicao.getCredor().getIdentificacao() + "', "
                + "subacao = '" + requisicao.getSubacao().getSubacao() + "', natureza_despesa = '" + requisicao.getNaturezaDespesa().getCodDespesa() + "', fonte_recurso = '" + requisicao.getFonteRecurso().getCodFr() + "', descricao = '" + requisicao.getDescricao() + "', justificativa = '" + requisicao.getJustificativa() + "' "
                + " WHERE cod_requisicao = '" + requisicao.getCodRequisicao() + "'";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);
        con.fecharConexao();


        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Requisicao> filtrarRequisicao (FiltroSAP filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados banco = new BancoDados();

        String sql = "SELECT s.*, g.nome from requisicao s, unidade g WHERE s.sigla_setor = g.cod_unidade";

        /*if (filtro == FiltroREQUISICAO.CREDOR) {
            sql += " AND s.credor like '%" + texto + "%'";
        } else if (filtro == FiltroREQUISICAO.UNIDADE) {
            sql += " AND g.nome like '%" + texto + "%'";
        }*/

        List<Requisicao> requisicoes = new ArrayList<Requisicao>();

        List<Unidade> unidades = banco.getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        List<Credor> credores = banco.getCredor();
        Map<String, Credor> mapCredor = new HashMap<>();

        List<NaturezaDespesa> naturezaDespesas = banco.getNaturezaDespesa();
        Map<String, NaturezaDespesa> mapNatureza = new HashMap<>();

        List<Subacao> subacaos = banco.getSubacao();
        Map<String, Subacao> mapSubacao = new HashMap<>();

        List<FonteRecurso> fonteRecursos = banco.getFonteRecurso();
        Map<Integer, FonteRecurso > mapFonteRecurso = new HashMap<>();

        for (Subacao subacao : subacaos) {
            mapSubacao.put(subacao.getSubacao(), subacao);
        }

        for (FonteRecurso fonteRecurso : fonteRecursos) {
            mapFonteRecurso.put(fonteRecurso.getCodFr(), fonteRecurso);
        }


        for (NaturezaDespesa naturezaDespesa : naturezaDespesas) {
            mapNatureza.put(naturezaDespesa.getCodDespesa(), naturezaDespesa);
        }

        for (Credor credor : credores) {
            mapCredor.put(credor.getIdentificacao(), credor);
        }

        for (Unidade unidade : unidades) {
            mapUnidade.put(unidade.getCodUnidade(), unidade);
        }

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while(resultSet.next()) {
                    int codRequisicao = resultSet.getInt("cod_requisicao");
                    int numeroRequisicao = resultSet.getInt("numero_requisicao");
                    int unidade = resultSet.getInt("sigla_setor");
                    LocalDate dataRequisicao = resultSet.getDate("data_requisicao").toLocalDate();
                    String credor = resultSet.getString("credor");
                    String naturezaDespesa = resultSet.getString("natureza_despesa");
                    float valor = resultSet.getFloat("valor");
                    String subacao = resultSet.getString("subacao");
                    int fonteRecurso = resultSet.getInt("fonte_recurso");
                    String descricao = resultSet.getString("descricao");
                    String justificativa = resultSet.getString("justificativa");
                    Requisicao requisicao = new Requisicao();
                    requisicao.setCodRequisicao(codRequisicao);
                    requisicao.setNumeroRequisicao(numeroRequisicao);
                    requisicao.setSetorSolicitante(mapUnidade.get(unidade));
                    requisicao.setDataRequisicao(dataRequisicao);
                    requisicao.setCredor(mapCredor.get(credor));
                    requisicao.setNaturezaDespesa(mapNatureza.get(naturezaDespesa));
                    requisicao.setValorRequisicao(valor);
                    requisicao.setFonteRecurso(mapFonteRecurso.get(fonteRecurso));
                    requisicao.setSubacao(mapSubacao.get(subacao));
                    requisicao.setDescricao(descricao);
                    requisicao.setJustificativa(justificativa);
                    requisicoes.add(requisicao);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return requisicoes;


    }
}
