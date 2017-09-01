--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `plantilla_transaccion` (
  `id` binary(255) NOT NULL,
  `clase_generadora` varchar(255) NOT NULL,
  `codigo_evento` varchar(255) NOT NULL,
  `fin_vigencia` datetime DEFAULT NULL,
  `inicio_vigencia` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
