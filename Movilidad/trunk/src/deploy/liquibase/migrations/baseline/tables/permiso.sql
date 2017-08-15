--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `permiso` (
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
