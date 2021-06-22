create table conta_bancaria (
	cod_cb int primary key,
	banco varchar(25),
	agencia varchar(25),
	conta_bancaria varchar(50),
	cod_tc int,
	nome_conta varchar(25),
	foreign key (cod_tc) references tipo_conta (cod_tc));