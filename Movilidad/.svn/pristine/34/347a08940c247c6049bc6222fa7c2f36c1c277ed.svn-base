--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `rol_permisos` (
  `rol_id` varchar(36) NOT NULL,
  `permisos_nombre` varchar(255) NOT NULL,
  KEY `FK8w1cwye3prn4998r44u8wpu09` (`permisos_nombre`),
  KEY `FKsm8nhys123yjn6ky9hsyxg4jq` (`rol_id`),
  CONSTRAINT `FK8w1cwye3prn4998r44u8wpu09` FOREIGN KEY (`permisos_nombre`) REFERENCES `permiso` (`nombre`),
  CONSTRAINT `FKsm8nhys123yjn6ky9hsyxg4jq` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
