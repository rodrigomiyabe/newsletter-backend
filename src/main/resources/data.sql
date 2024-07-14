create table Cliente (
    id LONG primary key not null ,
    nome varchar2(100) not null,
    email varchar2(30) not null unique,
    datanascimento date
);

create table Noticia(
    id LONG primary key not null,
    titulo varchar2(100) not null,
    descricao varchar2(500) not null,
    link varchar2(100) not null,
    processada varchar2(1) not null default 'N'
);