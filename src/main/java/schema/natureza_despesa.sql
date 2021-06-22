create table natureza_despesa (
	cod_despesa varchar(50) primary key,
	descricao varchar(100),
	cod_gf int,
	foreign key (cod_gf) references grupo_financeiro (cod_gf));