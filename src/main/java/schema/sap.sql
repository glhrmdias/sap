create table sap (
	cod_sap serial primary key,
	numero_sap int,
	ano_sap int,
	data_solicitacao date,
	sigla_solicitante int,
	mod_empenho int,
	unidade_gestao int,
	evento int,
	valor numeric(10,2),
	valor_janeiro varchar(50),
	valor_fevereiro varchar(50),
	valor_marco varchar(50),
	valor_abril varchar(50),
	valor_maio varchar(50),
	valor_junho varchar(50),
	valor_julho varchar(50),
	valor_agosto varchar(50),
	valor_setembro varchar(50),
	valor_outubro varchar(50),
	valor_novembro varchar(50),
	valor_dezembro varchar(50),
	credor varchar,
	unidade_orcamentaria int,
	subacao varchar(100),
	fonte_recurso int,
	natureza_despesa varchar(100),
	mod_licitacao int,
	processo varchar(50),
	tipo_contrato int,
	instrumento int,
	numero_autos varchar(50),
	nome_parte varchar(100),
	parte_adv int null,
	data_mandado date null,
	data_intiprev date null,
	data_vencimento date null,
	prazo_pgdias varchar(50),
	historico text,
	foreign key (mod_empenho) references mod_empenho (cod_mod),
	foreign key (sigla_solicitante) references unidade (cod_unidade),
	foreign key (unidade_gestao) references unidade_gestora (cod_ug),
	foreign key (evento) references evento (cod_evento),
	foreign key (credor) references credor (identificacao),
	foreign key (unidade_orcamentaria) references unidade_orcamentaria (cod_uo),
	foreign key (subacao) references subacao (subacao),
	foreign key (fonte_recurso) references fonte_recurso (cod_fr),
	foreign key (natureza_despesa) references natureza_despesa (cod_despesa),
	foreign key (mod_licitacao) references mod_licitacao (cod_mod),
	foreign key (tipo_contrato) references tipo_contrato (cod_tipoct),
	foreign key (instrumento) references instrumento (cod_instrumento),
	foreign key (parte_adv) references parte_adv (cod_pa),
	unique (numero_sap, ano_sap)
	);

	CREATE TRIGGER sap_num_seq_trigger
      BEFORE INSERT
      ON sap
      FOR EACH ROW
      EXECUTE PROCEDURE create_numero_seq();

	CREATE OR REPLACE FUNCTION create_numero_seq()
      RETURNS trigger AS
    $BODY$
    declare
      seq_name text;
      cur_year text;
    begin
      cur_year := extract(year from now());
      begin
        seq_name := format('%s_num_seq_%s', TG_TABLE_NAME, cur_year);
        execute format('create sequence %s', seq_name);
      exception when duplicate_table then end;
      new.numero_sap := nextval(seq_name);
      new.ano_sap := cur_year;
      return new;
    end;
    $BODY$
      LANGUAGE plpgsql VOLATILE
      COST 100;
