--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `perfil` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
