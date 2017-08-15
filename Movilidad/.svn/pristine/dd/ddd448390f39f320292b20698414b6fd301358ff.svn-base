--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `cuenta` (
  `id` binary(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `last_hash` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `obliga_referencia` bit(1) NOT NULL,
  `naturaleza_cuenta_id` binary(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsp6bycoav2um2wp4v7hre7wy7` (`naturaleza_cuenta_id`),
  CONSTRAINT `FKsp6bycoav2um2wp4v7hre7wy7` FOREIGN KEY (`naturaleza_cuenta_id`) REFERENCES `naturaleza_cuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
