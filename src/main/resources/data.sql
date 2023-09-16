create table tb_cidade (
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade (id_cidade, nome, qtd_habitantes) values
    (1, 'São Paulo', 1223343434),
    (2, 'Rio de Janeiro', 1000002),
    (3, 'Fortaleza', 100000),
    (4, 'Salvador', 100000),
    (5, 'Belo Horizonte', 100000),
    (6, 'Curitiba', 100000),
    (7, 'Porto Alegre', 100000),
    (8, 'Porto Velho', 100000);