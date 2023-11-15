CREATE DATABASE servlet_example;

\c servlet_example;

CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(150) NOT NULL,
	cpf VARCHAR(14) NOT NULL,
	telefone VARCHAR(14) NOT NULL
);

CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL
);

CREATE TABLE venda(
	id SERIAL PRIMARY KEY,
	cliente_id INTEGER,
	produto_id INTEGER,
	total FLOAT NOT NULL,
	CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id),
	CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
