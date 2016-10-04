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
INSERT INTO cargo(nombrecargo)
VALUES ('Administrador'), ('Secretaria'),('Operario');
INSERT INTO trabajador(nombres,apellidos,edad,id_cargo)
VALUES ('Manuel','Guarniz',21,1),('Mario','Torres',24,3),('Dana','Matiel',19,2);
SELECT * FROM cargo;
SELECT t.id,nombres,apellidos,edad,c.nombrecargo FROM trabajador t INNER JOIN cargo c ON t.id_cargo = c.id;
USE `empresa`;
DROP procedure IF EXISTS `spListarTrabajadores`;

DELIMITER $$
USE `empresa`$$
CREATE PROCEDURE `spListarTrabajadores` ()
BEGIN
	SELECT t.id,nombres,apellidos,edad,c.nombrecargo FROM trabajador t INNER JOIN cargo c ON t.id_cargo = c.id;
END$$

DELIMITER ;

CALL spListarTrabajadores();
SELECT id, nombrecargo FROM cargo ORDER BY nombrecargo;
DELETE FROM trabajador WHERE id = 4;
UPDATE trabajador SET nombres='marita',apellidos='lujan',edad=18, id_cargo = 3 WHERE id = 7