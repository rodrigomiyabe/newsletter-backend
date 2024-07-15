create table Cliente (
    id IDENTITY primary key not null ,
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

CREATE SEQUENCE s_cliente
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 202700
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE s_noticia
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 202700
    NOCACHE
    NOCYCLE;
