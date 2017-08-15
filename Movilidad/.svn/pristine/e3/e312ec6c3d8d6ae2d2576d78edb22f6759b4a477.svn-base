--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `vehiculo` (
  `id` binary(255) NOT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `numero_pasajeros` varchar(255) DEFAULT NULL,
  `placa_vehiculo` varchar(255) NOT NULL,
  `tarjeta_operacion` varchar(255) DEFAULT NULL,
  `clase_id` int(11) NOT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `propietario_id` varchar(36) DEFAULT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ef30wcim7tunjmanl8d1m92m` (`clase_id`),
  KEY `FK3yr9o7kuqofc3beyge4ajx0yg` (`estado_id`),
  KEY `FKod6gqfv6y04qgw6nhdhgk8wfd` (`propietario_id`),
  KEY `FKqqcpmhi5o77tn04okx6pcwju4` (`tipo_id`),
  CONSTRAINT `FK3yr9o7kuqofc3beyge4ajx0yg` FOREIGN KEY (`estado_id`) REFERENCES `estado_vehiculo` (`id`),
  CONSTRAINT `FK4ef30wcim7tunjmanl8d1m92m` FOREIGN KEY (`clase_id`) REFERENCES `clase_servicio_vehiculo` (`id`),
  CONSTRAINT `FKod6gqfv6y04qgw6nhdhgk8wfd` FOREIGN KEY (`propietario_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKqqcpmhi5o77tn04okx6pcwju4` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_vehiculo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
