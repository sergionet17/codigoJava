--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `telefono` (
  `id` int(11) NOT NULL,
  `numero` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;