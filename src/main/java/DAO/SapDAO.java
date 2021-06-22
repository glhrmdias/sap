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
public class SapDAO {

Sap sap = new Sap();

    public boolean inserindoSap (Sap sap) {
        Conexao con = new Conexao();

        String sql = "INSERT into sap (data_solicitacao, sigla_solicitante, mod_empenho, unidade_gestao, evento, valor, valor_janeiro, valor_fevereiro, valor_marco, valor_abril," +
                "valor_maio, valor_junho, valor_julho, valor_agosto, valor_setembro, valor_outubro, valor_novembro, valor_dezembro, credor, unidade_orcamentaria, subacao, fonte_recurso, natureza_despesa, " +
                "mod_licitacao, processo, tipo_contrato, instrumento, numero_autos, nome_parte, parte_adv, data_mandado, data_intiprev, data_vencimento, prazo_pgdias, historico)" +
                " VALUES ('" + sap.getDatasolicitacao() + "', '" + sap.getSetorSolicitante().getCodUnidade() + "', '" + sap.getTipoEmpenhosolicitado().getCodMod() + "', '"
                + sap.getUnidadeGestora().getCodUg() + "', '" + sap.getEvento().getCodEvento() + "', '" + sap.getValor() + "', '" + sap.getValor_janeiro() + "', '" + sap.getValor_fevereiro() + "', '" + sap.getValor_marco() + "', '" + sap.getValor_abril() + "', '" + sap.getValor_maio() + "', '"
                + sap.getValor_junho() + "', '" + sap.getValor_julho() + "', '" + sap.getValor_agosto() + "', '" + sap.getValor_setembro() + "', '" + sap.getValor_outubro() + "', '" + sap.getValor_novembro() + "', '" + sap.getValor_dezembro() + "', '"
                + sap.getCredor().getIdentificacao() + "', '" + sap.getUnidadeOrcamentaria().getCodUo() + "', '"
                + sap.getSubacao().getSubacao() + "', '" + sap.getFonteRecurso().getCodFr() + "', '" + sap.getNaturezaDespesa().getCodDespesa() + "', '"
                + sap.getModalidadeLicitacao().getCodMod() + "', '" + sap.getProcesso() + "', '" + sap.getTipoContrato().getCodTipoct() + "', '" + sap.getInstrumento().getCodInstrumento() + "', '"
                + sap.getNumeroAutos() + "', '" + sap.getNomeParte() + "', " + (sap.getParteAdvogado() != null ? sap.getParteAdvogado().getCodPa() : null) + ", "
                // se for em branco, envia null; se nao envia a data colocada
                + (sap.getDataMandado() == null ? null : "'" + sap.getDataMandado() + "'") + ", " + (sap.getDataIntimacaoIprev() == null ? null : "'" + sap.getDataIntimacaoIprev() + "'") + ", " + (sap.getDataVencimento() == null ? null : "'" + sap.getDataVencimento() + "'") + ", '"
                + sap.getPrazoPagamento() + "', '" + sap.getHistorico() + "');";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);

        con.fecharConexao();
        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizandoSap (Sap sap) {
        Conexao con = new Conexao();

        String sql = "UPDATE sap set data_solicitacao = '" + sap.getDatasolicitacao() + "', sigla_solicitante = '" + sap.getSetorSolicitante().getCodUnidade() + "', mod_empenho = '" + sap.getTipoEmpenhosolicitado().getCodMod() + "', " +
                "unidade_gestao = '" + sap.getUnidadeGestora().getCodUg() + "', evento = '" + sap.getEvento().getCodEvento() + "', valor = '" + sap.getValor() + "', valor_janeiro = '" + sap.getValor_janeiro() + "', valor_fevereiro = '" + sap.getValor_fevereiro() + "', valor_marco = '" + sap.getValor_marco() +
                "', valor_abril = '" + sap.getValor_abril() + "', valor_maio = '" + sap.getValor_maio() + "', valor_junho = '" + sap.getValor_junho() + "', valor_julho = '" + sap.getValor_julho() + "', valor_agosto = '" + sap.getValor_agosto() + "', valor_setembro = '" + sap.getValor_setembro() + "', valor_outubro = '" + sap.getValor_outubro() +
                "', valor_novembro = '" + sap.getValor_novembro() + "', valor_dezembro = '" + sap.getValor_dezembro() + "', credor = '" + sap.getCredor().getIdentificacao() + "', unidade_orcamentaria = '" + sap.getUnidadeOrcamentaria().getCodUo() + "', " +
                "subacao = '" + sap.getSubacao().getSubacao() + "', fonte_recurso = '" + sap.getFonteRecurso().getCodFr() + "', natureza_despesa = '" + sap.getNaturezaDespesa().getCodDespesa() + "', " +
                "mod_licitacao = '" + sap.getModalidadeLicitacao().getCodMod() + "', processo = '" + sap.getProcesso() + "', tipo_contrato = '" + sap.getTipoContrato().getCodTipoct() + "', instrumento = '" + sap.getInstrumento().getCodInstrumento() + "', " +
                "numero_autos = '" + sap.getNumeroAutos() + "', nome_parte = '" + sap.getNomeParte() + "', parte_adv = " + (sap.getParteAdvogado() != null ? sap.getParteAdvogado().getCodPa() : null) + ", data_mandado = " + (sap.getDataMandado() == null ? null : "'" + sap.getDataMandado() + "'") + ", data_intiprev = " + (sap.getDataIntimacaoIprev() == null ? null : "'" + sap.getDataIntimacaoIprev() + "'") +
                ", data_vencimento = " + (sap.getDataVencimento() == null ? null : "'" + sap.getDataVencimento() + "'") + ", " + "prazo_pgdias = '" + sap.getPrazoPagamento() + "', historico = '" + sap.getHistorico() + "'" +
                "WHERE cod_sap = '" + sap.getCodSap() + "'";

        int res = con.ExecutaSQL(sql);
        System.out.println(sql);
        con.fecharConexao();
        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Sap> filtrarSap (FiltroSAP filtro, String texto) {

        Conexao con = new Conexao();
        BancoDados banco = new BancoDados();

        String sql = "SELECT s.*, g.nome from sap s, unidade g WHERE s.sigla_solicitante = g.cod_unidade";

        if (filtro == FiltroSAP.CREDOR) {
            sql += " AND s.credor like '%" + texto + "%'";
        } else if (filtro == FiltroSAP.SETOR) {
            sql += " AND g.nome like '%" + texto + "%'";
        } else if (filtro == FiltroSAP.NATUREZA) {
            sql += " AND s.natureza_despesa like '%" + texto + "%'";
        } else if (filtro == FiltroSAP.SUBACAO) {
            sql += " AND s.subacao like '%" + texto + "%'";
        }

        /*sql += " ORDER BY cod_sap";*/

        List<Sap> saps = new ArrayList<Sap>();

        List<Unidade> unidades = banco.getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        List<UnidadeGestora> unidadeGestoras = banco.getUnidadeGestora();
        Map<Integer, UnidadeGestora> mapUnidadeGestora = new HashMap<>();

        List<Evento> eventos  = banco.getEventos();
        Map<Integer, Evento> mapEvento = new HashMap<>();

        List<Credor> credores = banco.getCredor();
        Map<String, Credor> mapCredor = new HashMap<>();

        List<NaturezaDespesa> naturezaDespesas = banco.getNaturezaDespesa();
        Map<String, NaturezaDespesa> mapNatureza = new HashMap<>();

        List<ModalidadeEmpenho> modalidadeEmpenhos = banco.getModalidadeEmpenho();
        Map<Integer, ModalidadeEmpenho> mapModalidadeEmpenho = new HashMap<>();

        List<UnidadeOrcamentaria> unidadeOrcamentarias  = banco.getUnidadeOrcamentaria();
        Map<Integer, UnidadeOrcamentaria > mapUnidadeOrcamentaria = new HashMap<>();

        List<Subacao> subacaos = banco.getSubacao();
        Map<String, Subacao> mapSubacao = new HashMap<>();

        List<FonteRecurso> fonteRecursos = banco.getFonteRecurso();
        Map<Integer, FonteRecurso > mapFonteRecurso = new HashMap<>();

        List<ModalidadeLicitacao> modalidadeLicitacaos = banco.getModalidadeLicitacao();
        Map<Integer, ModalidadeLicitacao> mapModalidadeLicitacao = new HashMap<>();

        List<TipoContrato> tipoContratos  = banco.getTipoContrato();
        Map<Integer, TipoContrato > mapTipoContrato = new HashMap<>();

        List<Instrumento> instrumentos  = banco.getInstrumento();
        Map<Integer, Instrumento > mapInstrumento = new HashMap<>();

        List<ParteAdvogado> parteAdvogados  = banco.getParteAdvogado();
        Map<Integer, ParteAdvogado > mapParteAdvogado = new HashMap<>();

        for (ParteAdvogado parteAdvogado : parteAdvogados) {
            mapParteAdvogado.put(parteAdvogado.getCodPa(), parteAdvogado);
        }

        for (Instrumento instrumento : instrumentos) {
            mapInstrumento.put(instrumento.getCodInstrumento(), instrumento);
        }

        for (TipoContrato tipoContrato : tipoContratos) {
            mapTipoContrato.put(tipoContrato.getCodTipoct(), tipoContrato);
        }

        for (ModalidadeLicitacao modalidadeLicitacao : modalidadeLicitacaos) {
            mapModalidadeLicitacao.put(modalidadeLicitacao.getCodMod(), modalidadeLicitacao);
        }

        for (FonteRecurso fonteRecurso : fonteRecursos) {
            mapFonteRecurso.put(fonteRecurso.getCodFr(), fonteRecurso);
        }

        for (Subacao subacao : subacaos) {
            mapSubacao.put(subacao.getSubacao(), subacao);
        }

        for (UnidadeOrcamentaria unidadeOrcamentaria : unidadeOrcamentarias) {
            mapUnidadeOrcamentaria.put(unidadeOrcamentaria.getCodUo(), unidadeOrcamentaria);
        }

        for (NaturezaDespesa naturezaDespesa : naturezaDespesas) {
            mapNatureza.put(naturezaDespesa.getCodDespesa(), naturezaDespesa);
        }

        for (Credor credor : credores) {
            mapCredor.put(credor.getIdentificacao(), credor);
        }

        for(Unidade unidade : unidades) {
            mapUnidade.put(unidade.getCodUnidade(), unidade);
        }

        for (Evento evento : eventos) {
            mapEvento.put(evento.getCodEvento(), evento);
        }

        for (UnidadeGestora unidadeGestora : unidadeGestoras) {
            mapUnidadeGestora.put(unidadeGestora.getCodUg(), unidadeGestora);
        }

        for (ModalidadeEmpenho modalidadeEmpenho : modalidadeEmpenhos) {
            mapModalidadeEmpenho.put(modalidadeEmpenho.getCodMod(), modalidadeEmpenho);
        }

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codSap = resultSet.getInt("cod_sap");
                    int numeroSap = resultSet.getInt("numero_sap");
                    int unidade = resultSet.getInt("sigla_solicitante");
                    LocalDate dataSolicitacao = resultSet.getDate("data_solicitacao").toLocalDate();
                    String credor = resultSet.getString("credor");
                    String naturezaDespesa = resultSet.getString("natureza_despesa");
                    float valor = resultSet.getFloat("valor");
                    String valor_janeiro = resultSet.getString("valor_janeiro");
                    String valor_fevereiro = resultSet.getString("valor_fevereiro");
                    String valor_marco = resultSet.getString("valor_marco");
                    String valor_abril = resultSet.getString("valor_abril");
                    String valor_maio = resultSet.getString("valor_maio");
                    String valor_junho = resultSet.getString("valor_junho");
                    String valor_julho = resultSet.getString("valor_julho");
                    String valor_agosto = resultSet.getString("valor_agosto");
                    String valor_setembro = resultSet.getString("valor_setembro");
                    String valor_outubro = resultSet.getString("valor_outubro");
                    String valor_novembro = resultSet.getString("valor_novembro");
                    String valor_dezembro = resultSet.getString("valor_dezembro");
                    int evento = resultSet.getInt("evento");
                    int unidadeGestora = resultSet.getInt("unidade_gestao");
                    int modalidadeEmpenho = resultSet.getInt("mod_empenho");
                    int unidadeOrcamentaria = resultSet.getInt("unidade_orcamentaria");
                    String subacao = resultSet.getString("subacao");
                    int fonteRecurso = resultSet.getInt("fonte_recurso");
                    int tipoContrato = resultSet.getInt("tipo_contrato");
                    int instrumento = resultSet.getInt("instrumento");
                    int modalidadeLicitacao = resultSet.getInt("mod_licitacao");
                    int parteAdvogado = resultSet.getInt("parte_adv");
                    String processo = resultSet.getString("processo");
                    String numeroAutos = resultSet.getString("numero_autos");
                    String nomeParte = resultSet.getString("nome_parte");
                    LocalDate dataMandado = resultSet.getDate("data_mandado") == null ? null : resultSet.getDate("data_mandado").toLocalDate();
                    LocalDate dataIntiprev = resultSet.getDate("data_intiprev") == null ? null : resultSet.getDate("data_intiprev").toLocalDate();
                    LocalDate dataVencimento = resultSet.getDate("data_vencimento") == null ? null : resultSet.getDate("data_vencimento").toLocalDate();
                    String prazoPagamento = resultSet.getString("prazo_pgdias");
                    String historico = resultSet.getString("historico");
                    Sap sap = new Sap();
                    sap.setCodSap(codSap);
                    sap.setNumeroSap(numeroSap);
                    sap.setSetorSolicitante(mapUnidade.get(unidade));
                    sap.setDatasolicitacao(dataSolicitacao);
                    sap.setCredor(mapCredor.get(credor));
                    sap.setNaturezaDespesa(mapNatureza.get(naturezaDespesa));
                    sap.setValor(valor);
                    sap.setValor_janeiro(valor_janeiro);
                    sap.setValor_fevereiro(valor_fevereiro);
                    sap.setValor_marco(valor_marco);
                    sap.setValor_abril(valor_abril);
                    sap.setValor_maio(valor_maio);
                    sap.setValor_junho(valor_junho);
                    sap.setValor_julho(valor_julho);
                    sap.setValor_agosto(valor_agosto);
                    sap.setValor_setembro(valor_setembro);
                    sap.setValor_outubro(valor_outubro);
                    sap.setValor_novembro(valor_novembro);
                    sap.setValor_dezembro(valor_dezembro);
                    sap.setEvento(mapEvento.get(evento));
                    sap.setUnidadeGestora(mapUnidadeGestora.get(unidadeGestora));
                    sap.setTipoEmpenhosolicitado(mapModalidadeEmpenho.get(modalidadeEmpenho));
                    sap.setUnidadeOrcamentaria(mapUnidadeOrcamentaria.get(unidadeOrcamentaria));
                    sap.setSubacao(mapSubacao.get(subacao));
                    sap.setFonteRecurso(mapFonteRecurso.get(fonteRecurso));
                    sap.setTipoContrato(mapTipoContrato.get(tipoContrato));
                    sap.setInstrumento(mapInstrumento.get(instrumento));
                    sap.setModalidadeLicitacao(mapModalidadeLicitacao.get(modalidadeLicitacao));
                    sap.setParteAdvogado(mapParteAdvogado.get(parteAdvogado));
                    sap.setProcesso(processo);
                    sap.setNumeroAutos(numeroAutos);
                    sap.setNomeParte(nomeParte);
                    sap.setDataMandado(dataMandado);
                    sap.setDataIntimacaoIprev(dataIntiprev);
                    sap.setDataVencimento(dataVencimento);
                    sap.setPrazoPagamento(prazoPagamento);
                    sap.setHistorico(historico);
                    saps.add(sap);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return saps;

    }


}
