--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `parametro` (
  `clave` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `valor` varchar(255) NOT NULL,
  `categoria_clave` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`clave`),
  KEY `FKagt8n1l6b52nj208g59mw4tes` (`categoria_clave`),
  CONSTRAINT `FKagt8n1l6b52nj208g59mw4tes` FOREIGN KEY (`categoria_clave`) REFERENCES `categoria_parametro` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
