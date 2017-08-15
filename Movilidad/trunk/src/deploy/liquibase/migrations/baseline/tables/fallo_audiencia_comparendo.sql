--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `fallo_audiencia_comparendo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
