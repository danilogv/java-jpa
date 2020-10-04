CREATE DATABASE Java_JPA CHARSET = utf8 COLLATE = utf8_general_ci;

USE Java_JPA;

CREATE TABLE Pessoa (
	id int auto_increment,
	nome varchar(50) not null,
	email varchar(50) not null,
	primary key(id),
	unique key nome_unique(nome),
	unique key email_unique(email)
) ENGINE = INNODB;