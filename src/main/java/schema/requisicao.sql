create table requisicao (
	cod_requisicao serial primary key,	
	numero_requisicao int,
	ano_requisicao int,
	data_requisicao date,
	sigla_setor int,
	valor numeric(10,2),
	credor varchar,
	subacao varchar(100),
	natureza_despesa varchar(100),
	fonte_recurso int,
	descricao text,
	justificativa text,
	foreign key (credor) references credor 	(identificacao),
	foreign key (subacao) references subacao (subacao),
	foreign key (natureza_despesa)references natureza_despesa (cod_despesa),
	foreign key (fonte_recurso) references fonte_recurso (cod_fr));

	CREATE TRIGGER req_num_seq_trigger
          BEFORE INSERT
          ON requisicao
          FOR EACH ROW
          EXECUTE PROCEDURE create_numero_seq_req();

    	CREATE OR REPLACE FUNCTION create_numero_seq_req()
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
          new.numero_requisicao := nextval(seq_name);
          new.ano_requisicao := cur_year;
          return new;
        end;
        $BODY$
          LANGUAGE plpgsql VOLATILE
          COST 100;