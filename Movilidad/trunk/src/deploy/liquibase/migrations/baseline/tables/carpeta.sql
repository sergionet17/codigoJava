--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `carpeta` (
  `id` varchar(36) NOT NULL,
  `acepta_documentos` bit(1) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `dependencia_origen_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK63mtrputh8vto68sf2y370hjl` (`dependencia_origen_id`),
  CONSTRAINT `FK63mtrputh8vto68sf2y370hjl` FOREIGN KEY (`dependencia_origen_id`) REFERENCES `dependencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
