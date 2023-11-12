CREATE DATABASE servlet_example;

\c servlet_example;

CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(150) NOT NULL,
	cpf VARCHAR(14) NOT NULL,
	telefone VARCHAR(14) NOT NULL
);

