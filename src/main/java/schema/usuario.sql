create table usuario (
	matricula varchar(50) primary key,
	nome varchar(50),
	setor int,
	senha varchar(60),
	foreign key (setor) references unidade (cod_unidade)
	);