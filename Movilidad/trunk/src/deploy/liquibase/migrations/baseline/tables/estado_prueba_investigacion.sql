--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `estado_prueba_investigacion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
