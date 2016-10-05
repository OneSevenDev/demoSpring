CREATE SCHEMA `empresa` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE empresa;
CREATE TABLE trabajador(
	id int(11) auto_increment PRIMARY KEY,
    nombres varchar(45),
    apellidos varchar(45),
    edad int(3),
    id_cargo int(11) REFERENCES cargo
);
CREATE TABLE cargo(
	id int(11) auto_increment PRIMARY KEY,
    nombrecargo varchar(45)
);