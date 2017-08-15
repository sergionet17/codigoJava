--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `persona` (
  `id` varchar(36) NOT NULL,
  `primer_apellido` varchar(255) NOT NULL,
  `primer_nombre` varchar(255) NOT NULL,
  `segundo_apellido` varchar(255) DEFAULT NULL,
  `segundo_nombre` varchar(255) DEFAULT NULL,
  `documento_identidad_id` varchar(36) NOT NULL,
  `tipo_persona_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKer3mimsq9quktc0vfujvq4piq` (`documento_identidad_id`),
  KEY `FKxxg7sg5rmf6yp09mn7j67mas` (`tipo_persona_id`),
  CONSTRAINT `FKer3mimsq9quktc0vfujvq4piq` FOREIGN KEY (`documento_identidad_id`) REFERENCES `documento_identidad` (`id`),
  CONSTRAINT `FKxxg7sg5rmf6yp09mn7j67mas` FOREIGN KEY (`tipo_persona_id`) REFERENCES `tipo_persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
