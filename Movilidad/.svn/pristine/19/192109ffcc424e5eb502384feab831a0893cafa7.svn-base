--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `documento_identidad` (
  `id` varchar(36) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKboacovl3nxu56sya8q5xdv5pj` (`tipo_id`),
  CONSTRAINT `FKboacovl3nxu56sya8q5xdv5pj` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
