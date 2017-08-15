--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `tipo_accion` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
