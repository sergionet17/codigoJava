--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `causal_subsanacion` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
