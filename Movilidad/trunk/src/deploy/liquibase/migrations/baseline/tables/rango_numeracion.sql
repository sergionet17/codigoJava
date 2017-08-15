--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `rango_numeracion` (
  `id` varchar(36) NOT NULL,
  `fecha_respuesta` datetime DEFAULT NULL,
  `fecha_solicitud` datetime NOT NULL,
  `fin` int(11) NOT NULL,
  `inicio` int(11) NOT NULL,
  `respuesta_solicitud_id` varchar(36) DEFAULT NULL,
  `solicitud_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbx9lj40edrogp8l07775uoeuj` (`respuesta_solicitud_id`),
  KEY `FK71uw8j9hjm773jfmt8y3vqn8f` (`solicitud_id`),
  CONSTRAINT `FK71uw8j9hjm773jfmt8y3vqn8f` FOREIGN KEY (`solicitud_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKbx9lj40edrogp8l07775uoeuj` FOREIGN KEY (`respuesta_solicitud_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
