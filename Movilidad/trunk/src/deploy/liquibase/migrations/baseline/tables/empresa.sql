--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `empresa` (
  `id` int(11) NOT NULL,
  `nit` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
