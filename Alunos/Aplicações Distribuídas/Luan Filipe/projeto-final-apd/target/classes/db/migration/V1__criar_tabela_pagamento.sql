CREATE TABLE pagamentos (
 id serial PRIMARY KEY,
 valor double precision NOT NULL,
 nome varchar(100) DEFAULT NULL,
 status varchar(20) DEFAULT NULL,
 pedido bigint NOT NULL
);