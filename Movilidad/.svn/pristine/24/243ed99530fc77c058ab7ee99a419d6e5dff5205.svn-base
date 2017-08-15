--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `transaccion` (
  `id` binary(255) NOT NULL,
  `codigo_evento` varchar(255) NOT NULL,
  `descripcion_evento` varchar(255) DEFAULT NULL,
  `fecha_evento` datetime NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `hash` varchar(255) NOT NULL,
  `referencia` binary(255) NOT NULL,
  `tipo_referencia` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
