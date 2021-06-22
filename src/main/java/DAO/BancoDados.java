package DAO;
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
public class BancoDados {

    public List<Usuario> getUsuario() {
        Conexao con = new Conexao();

        String sql = "SELECT * from usuario ORDER BY nome";

        List<Usuario> usuarios = new ArrayList<Usuario>();

        List<Unidade> unidades = getUnidade();
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

    public Usuario getUsuarioMatricula(String matricula) {

        Conexao con = new Conexao();

        Usuario usuario = null;

        String sql = "SELECT * from usuario WHERE matricula ='" + matricula + "'";

        System.out.println(sql);

        ResultSet resultSet = con.ExecutaSelect(sql);

        List<Unidade> unidades = getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        for(Unidade unidade : unidades) {
            mapUnidade.put(unidade.getCodUnidade(), unidade);
        }

        if (resultSet != null ) {
            try {
                if (resultSet.next()) {
                    String matriculaUsuario = resultSet.getString("matricula");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    int setor = resultSet.getInt("setor");
                    usuario = new Usuario();
                    usuario.setMatricula(matricula);
                    usuario.setNome(nome);
                    usuario.setSetor(mapUnidade.get(setor));
                    usuario.setSenha(senha);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        con.fecharConexao();
        return usuario;

    }

    public List<Evento> getEventos() {

        Conexao con = new Conexao();

        List<Evento> eventos = new ArrayList<Evento>();

        String sql = "SELECT * from evento";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codEvento = resultSet.getInt("cod_evento");
                    String evento = resultSet.getString("evento");
                    int codigo = resultSet.getInt("codigo");
                    Evento event = new Evento();
                    event.setCodEvento(codEvento);
                    event.setEvento(evento);
                    event.setCodigo(codigo);
                    eventos.add(event);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         con.fecharConexao();
        return eventos;
    }

    public List<TipoCredor> getTipoCredor() {

        Conexao con = new Conexao();

        List<TipoCredor> tipoCredores = new ArrayList<TipoCredor>();

        String sql = "SELECT * from tipo_credor";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codTcredor = resultSet.getInt("cod_tcredor");
                    String tipo = resultSet.getString("tipo");
                    TipoCredor tipoCredor = new TipoCredor();
                    tipoCredor.setCodTcredor(codTcredor);
                    tipoCredor.setTipo(tipo);
                    tipoCredores.add(tipoCredor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return tipoCredores;
    }

    public List<Subacao> getSubacao() {

        Conexao con = new Conexao();

        String sql = "SELECT * from subacao ORDER BY descricao";

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

    public List<Unidade> getUnidade() {

        Conexao con = new Conexao();

        List<Unidade> unidades = new ArrayList<Unidade>();

        String sql = "SELECT * from unidade";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codUnidade = resultSet.getInt("cod_unidade");
                    String nome = resultSet.getString("nome");
                    Unidade unidade = new Unidade();
                    unidade.setCodUnidade(codUnidade);
                    unidade.setNome(nome);
                    unidades.add(unidade);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return unidades;

    }

    public List<UnidadeGestora> getUnidadeGestora() {

        Conexao con = new Conexao();

        List<UnidadeGestora> unidadesGestora = new ArrayList<UnidadeGestora>();

        String sql = "SELECT * from unidade_gestora";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codUg = resultSet.getInt("cod_ug");
                    String sigla = resultSet.getString("sigla");
                    int gestao = resultSet.getInt("gestao");
                    UnidadeGestora unidadeGestora = new UnidadeGestora();
                    unidadeGestora.setCodUg(codUg);
                    unidadeGestora.setSigla(sigla);
                    unidadeGestora.setGestao(gestao);
                    unidadesGestora.add(unidadeGestora);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return unidadesGestora;
    }

    public List<ModalidadeLicitacao> getModalidadeLicitacao() {

        Conexao  con = new Conexao();

        List<ModalidadeLicitacao> modalidadesLicitacao = new ArrayList<ModalidadeLicitacao>();

        String sql = "SELECT * from mod_licitacao ORDER BY cod_mod";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codMod = resultSet.getInt("cod_mod");
                    String descricao = resultSet.getString("descricao");
                    ModalidadeLicitacao modalidadeLicitacao = new ModalidadeLicitacao();
                    modalidadeLicitacao.setCodMod(codMod);
                    modalidadeLicitacao.setDescricao(descricao);
                    modalidadesLicitacao.add(modalidadeLicitacao);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return modalidadesLicitacao;
    }

    public List<ModalidadeEmpenho> getModalidadeEmpenho() {

        Conexao con = new Conexao();

        List<ModalidadeEmpenho> modalidadesEmpenho = new ArrayList<ModalidadeEmpenho>();

        String sql = "SELECT * from mod_empenho";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codMod = resultSet.getInt("cod_mod");
                    String empenho = resultSet.getString("empenho");
                    ModalidadeEmpenho modalidadeEmpenho = new ModalidadeEmpenho();
                    modalidadeEmpenho.setCodMod(codMod);
                    modalidadeEmpenho.setEmpenho(empenho);
                    modalidadesEmpenho.add(modalidadeEmpenho);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return modalidadesEmpenho;
    }

    public List<FonteRecurso> getFonteRecurso() {

        Conexao con = new Conexao();

        List<FonteRecurso> fontesRecurso = new ArrayList<FonteRecurso>();

        String sql = "SELECT * from fonte_recurso ORDER BY relacao_fontes";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codFr = resultSet.getInt("cod_fr");
                    String descricao = resultSet.getString("descricao");
                    String relacaoFontes = resultSet.getString("relacao_fontes");
                    FonteRecurso fonteRecurso = new FonteRecurso();
                    fonteRecurso.setCodFr(codFr);
                    fonteRecurso.setDescricao(descricao);
                    fonteRecurso.setRelacaoFontes(relacaoFontes);
                    fontesRecurso.add(fonteRecurso);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return fontesRecurso;
    }

    public List<NaturezaDespesa> getNaturezaDespesa() {

        Conexao con = new Conexao();

        List<NaturezaDespesa> naturezasDespesa = new ArrayList<NaturezaDespesa>();

        List<GrupoFinanceiro> grupoFinanceiros = getGrupoFinanceiro();
        Map<Integer, GrupoFinanceiro> mapGrupoFinanceiro = new HashMap<>();

        for(GrupoFinanceiro grupoFinanceiro : grupoFinanceiros) {
            mapGrupoFinanceiro.put(grupoFinanceiro.getCodGf(), grupoFinanceiro);
        }

        String sql = "SELECT * from natureza_despesa ORDER BY cod_despesa";

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

    public List<TipoContrato> getTipoContrato() {

        Conexao con = new Conexao();

        List<TipoContrato> tiposContrato = new ArrayList<TipoContrato>();

        String sql = "SELECT * from tipo_contrato";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codTipoct = resultSet.getInt("cod_tipoct");
                    String descricao = resultSet.getString("descricao");
                    TipoContrato tipoContrato = new TipoContrato();
                    tipoContrato.setCodTipoct(codTipoct);
                    tipoContrato.setDescricao(descricao);
                    tiposContrato.add(tipoContrato);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return tiposContrato;
    }

    public List<Instrumento> getInstrumento() {

        Conexao con = new Conexao();

        List<Instrumento> instrumentos = new ArrayList<Instrumento>();

        String sql = "SELECT * from instrumento";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codInstrumento = resultSet.getInt("cod_instrumento");
                    String descricao = resultSet.getString("descricao");
                    Instrumento instrumento = new Instrumento();
                    instrumento.setCodInstrumento(codInstrumento);
                    instrumento.setDescricao(descricao);
                    instrumentos.add(instrumento);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return instrumentos;
    }

    public List<UnidadeOrcamentaria> getUnidadeOrcamentaria() {

        Conexao con = new Conexao();

        List<UnidadeOrcamentaria> unidadesOrcamentaria = new ArrayList<UnidadeOrcamentaria>();

        String sql = "SELECT * from unidade_orcamentaria";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codUo = resultSet.getInt("cod_uo");
                    String descricao = resultSet.getString("descricao");
                    UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();
                    unidadeOrcamentaria.setCodUo(codUo);
                    unidadeOrcamentaria.setDescricao(descricao);
                    unidadesOrcamentaria.add(unidadeOrcamentaria);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return unidadesOrcamentaria;
    }

    public List<ParteAdvogado> getParteAdvogado() {

        Conexao con = new Conexao();

        List<ParteAdvogado> partesAdvogado = new ArrayList<ParteAdvogado>();

        String sql = "SELECT * from parte_adv";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codPa = resultSet.getInt("cod_pa");
                    String tipo = resultSet.getString("tipo");
                    ParteAdvogado parteAdvogado = new ParteAdvogado();
                    parteAdvogado.setCodPa(codPa);
                    parteAdvogado.setTipo(tipo);
                    partesAdvogado.add(parteAdvogado);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return partesAdvogado;
    }

    public List<Credor> getCredor() {

        Conexao con = new Conexao();

        List<Credor> credores = new ArrayList<Credor>();

        String sql = "SELECT * from credor ORDER BY nome_credor";

        List<TipoCredor> tipoCredores = getTipoCredor();
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

    public List<GrupoFinanceiro> getGrupoFinanceiro() {

        Conexao con = new Conexao();

        List<GrupoFinanceiro> grupoFinanceiros = new ArrayList<GrupoFinanceiro>();

        String sql = "SELECT * from grupo_financeiro";

        ResultSet resultSet = con.ExecutaSelect(sql);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int codGf = resultSet.getInt("cod_gf");
                    String descricao = resultSet.getString("descricao");
                    GrupoFinanceiro grupoFinanceiro = new GrupoFinanceiro();
                    grupoFinanceiro.setCodGf(codGf);
                    grupoFinanceiro.setDescricao(descricao);
                    grupoFinanceiros.add(grupoFinanceiro);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        con.fecharConexao();
        return grupoFinanceiros;
    }

    public List<Sap> listarSaps() {

        String sql = "SELECT * from sap ORDER BY cod_sap";

        List<Sap> saps = new ArrayList<Sap>();

        List<Unidade> unidades = getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        List<UnidadeGestora> unidadeGestoras = getUnidadeGestora();
        Map<Integer, UnidadeGestora> mapUnidadeGestora = new HashMap<>();

        List<Evento> eventos  = getEventos();
        Map<Integer, Evento> mapEvento = new HashMap<>();

        List<Credor> credores = getCredor();
        Map<String, Credor> mapCredor = new HashMap<>();

        List<NaturezaDespesa> naturezaDespesas = getNaturezaDespesa();
        Map<String, NaturezaDespesa> mapNatureza = new HashMap<>();

        List<ModalidadeEmpenho> modalidadeEmpenhos = getModalidadeEmpenho();
        Map<Integer, ModalidadeEmpenho> mapModalidadeEmpenho = new HashMap<>();

        List<UnidadeOrcamentaria> unidadeOrcamentarias  = getUnidadeOrcamentaria();
        Map<Integer, UnidadeOrcamentaria > mapUnidadeOrcamentaria = new HashMap<>();

        List<Subacao> subacaos = getSubacao();
        Map<String, Subacao> mapSubacao = new HashMap<>();

        List<FonteRecurso> fonteRecursos = getFonteRecurso();
        Map<Integer, FonteRecurso > mapFonteRecurso = new HashMap<>();

        List<ModalidadeLicitacao> modalidadeLicitacaos = getModalidadeLicitacao();
        Map<Integer, ModalidadeLicitacao> mapModalidadeLicitacao = new HashMap<>();

        List<TipoContrato> tipoContratos  = getTipoContrato();
        Map<Integer, TipoContrato > mapTipoContrato = new HashMap<>();

        List<Instrumento> instrumentos  = getInstrumento();
        Map<Integer, Instrumento > mapInstrumento = new HashMap<>();

        List<ParteAdvogado> parteAdvogados  = getParteAdvogado();
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

        Conexao con = new Conexao();

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

    public  List<Requisicao> listarRequisicao() {

        String sql = "SELECT * from requisicao ORDER BY cod_requisicao";

        List<Requisicao> requisicoes = new ArrayList<Requisicao>();

        List<Unidade> unidades = getUnidade();
        Map<Integer, Unidade> mapUnidade = new HashMap<>();

        List<Credor> credores = getCredor();
        Map<String, Credor> mapCredor = new HashMap<>();

        List<NaturezaDespesa> naturezaDespesas = getNaturezaDespesa();
        Map<String, NaturezaDespesa> mapNatureza = new HashMap<>();

        List<Subacao> subacaos = getSubacao();
        Map<String, Subacao> mapSubacao = new HashMap<>();

        List<FonteRecurso> fonteRecursos = getFonteRecurso();
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

        Conexao con = new Conexao();

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

    public boolean excluirSap (Sap sap) {
        Conexao con = new Conexao();

        String sql = "DELETE from sap WHERE cod_sap = '" + sap.getCodSap() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return  true;
        } else {
            return false;
        }
    }

    public boolean excluirSubacao (Subacao subacao) {
        Conexao con = new Conexao();

        String sql = "DELETE from subacao WHERE subacao = '" + subacao.getSubacao() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return  true;
        } else {
            return false;
        }
    }

    public boolean excluirRequisicao (Requisicao requisicao) {
        Conexao con = new Conexao();

        String sql = "DELETE from requisicao WHERE cod_requisicao = '" + requisicao.getCodRequisicao() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if(res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean excluirUsuario(Usuario usuario) {

        Conexao con = new Conexao();

        String sql = "DELETE from usuario WHERE matricula = '" + usuario.getMatricula() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return  true;
        } else {
            return false;
        }
    }

    public boolean excluirCredor(Credor credor) {
        Conexao con = new Conexao();

        String sql = "DELETE from credor WHERE identificacao = '" + credor.getIdentificacao() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean excluirNatureza (NaturezaDespesa naturezaDespesa) {
        Conexao con = new Conexao();

        String sql = "DELETE from natureza_despesa WHERE cod_despesa = '" + naturezaDespesa.getCodDespesa() + "'";

        con.ExecutaSQL(sql);

        int res = con.ExecutaSQL(sql);

        con.fecharConexao();

        if (res != 0) {
            return true;
        } else {
            return false;
        }
    }


}
