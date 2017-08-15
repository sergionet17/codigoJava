--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `entrada` (
  `id` binary(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fecha_evento` datetime NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `hash` varchar(255) NOT NULL,
  `referencia` binary(255) DEFAULT NULL,
  `tipo_movimiento` bit(1) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `cuenta_id` binary(255) NOT NULL,
  `transaccion_id` binary(255) NOT NULL,
  `transaccion` binary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4p0goe0vmq7138d6uqnwk1j03` (`cuenta_id`),
  KEY `FKphiwk6vhtd0r6m4vjpqa9gv47` (`transaccion_id`),
  KEY `FKbi5mxuahdq1xrvaw1j69t4hil` (`transaccion`),
  CONSTRAINT `FK4p0goe0vmq7138d6uqnwk1j03` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKbi5mxuahdq1xrvaw1j69t4hil` FOREIGN KEY (`transaccion`) REFERENCES `transaccion` (`id`),
  CONSTRAINT `FKphiwk6vhtd0r6m4vjpqa9gv47` FOREIGN KEY (`transaccion_id`) REFERENCES `transaccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
