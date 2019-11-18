drop database if exists banco;
create database banco;
use banco;

create table usuario (
    idUsuario		int             primary key    auto_increment,
    login			varchar(15)     unique,
    senha           varchar(15),
    perfil          int
);

create table conta (
    idConta			int             primary key    auto_increment,
    numero          int,
    saldo           decimal(4,2),
	usuario			int             unique,
    foreign key (usuario) references usuario (idUsuario) ON DELETE CASCADE
);

create table pessoa (
    idPessoa        int             primary key    auto_increment,
	nome            varchar(15),
	email           varchar(20),
	sexo            varchar(15),
	telefone        varchar(15),
	cep             varchar(15),
	nascimento      timestamp,
	usuario			int             unique,
    foreign key (usuario) references usuario (idUsuario) ON DELETE CASCADE
);

create table registro (
    idRegistro      int             primary key    auto_increment,
    nome            varchar(20),
    ip              varchar(20),
    data            timestamp,
	usuario			int,
    foreign key (usuario) references usuario (idUsuario) ON DELETE CASCADE
);

create table operacao (
    idOperacao      int             primary key    auto_increment,
    tipo            int,
    valor           decimal(4,2),
    data            timestamp,
    contaAtual      int,
    contaDestino    int,
    foreign key (contaAtual) references conta (idConta) ON DELETE CASCADE,
    foreign key (contaDestino) references conta (idConta) ON DELETE CASCADE
);

create table filme (
	idFilme 		int 			primary key 	auto_increment,
	poster 			varchar(99),
	titulo 			varchar(99),
	genero 			varchar(99),
	protagonista 	varchar(99),
	diretor 		varchar(99),
	lancamento      int,
	preco 			decimal(4,2),
	estoque         int
);

create table compra (
    idCompra        int             primary key    auto_increment,
    quantidade      int,
    comprado        char,
    data            timestamp,
    usuario         int,
    filme           int,
    foreign key (usuario) references usuario (idUsuario) ON DELETE CASCADE,
    foreign key (filme) references filme (idFilme) ON DELETE CASCADE
);

create table mensagem (
    idMensagem      int             primary key    auto_increment,
    texto           varchar(140),
    data            timestamp,
    visualizada     char,
    remetente       int,
    destinatario    int,
    foreign key (remetente) references usuario (idUsuario) ON DELETE CASCADE,
    foreign key (destinatario) references usuario (idUsuario) ON DELETE CASCADE
);
