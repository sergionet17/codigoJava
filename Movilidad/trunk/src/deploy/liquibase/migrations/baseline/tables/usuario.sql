--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `usuario` (
  `id` varchar(36) NOT NULL,
  `activo` bit(1) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_caducidad` datetime NOT NULL,
  `firma` longblob,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `dependencia_id` varchar(36) DEFAULT NULL,
  `documento_soporte_id` varchar(36) DEFAULT NULL,
  `jefe_dependencia_id` varchar(36) DEFAULT NULL,
  `perfil_id` varchar(36) DEFAULT NULL,
  `persona_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5gm0f6dlsqjfs0e66kkeoy616` (`dependencia_id`),
  KEY `FKt9owrjwvob07lssy5wsx8mjhw` (`documento_soporte_id`),
  KEY `FK2f657hkpidj51oeegtlvbl623` (`jefe_dependencia_id`),
  KEY `FK9po12ytp6krwvwht1kmd0qgxf` (`perfil_id`),
  KEY `FKlse7lqghmt3r1sp298ss9s5bc` (`persona_id`),
  CONSTRAINT `FK2f657hkpidj51oeegtlvbl623` FOREIGN KEY (`jefe_dependencia_id`) REFERENCES `jefe_dependencia` (`id`),
  CONSTRAINT `FK5gm0f6dlsqjfs0e66kkeoy616` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencia` (`id`),
  CONSTRAINT `FK9po12ytp6krwvwht1kmd0qgxf` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FKlse7lqghmt3r1sp298ss9s5bc` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKt9owrjwvob07lssy5wsx8mjhw` FOREIGN KEY (`documento_soporte_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
