--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `recurso_apelacion` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `carpeta_origen_id` varchar(36) NOT NULL,
  `decision_id` int(11) DEFAULT NULL,
  `oficio_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl0cwr5d6fnopc6ic4680h4y7p` (`carpeta_origen_id`),
  KEY `FK4qrv0ryqwouimf787f1cpegn1` (`decision_id`),
  KEY `FKh4jvl6ykdg850mahuqf1c09l8` (`oficio_id`),
  CONSTRAINT `FK4qrv0ryqwouimf787f1cpegn1` FOREIGN KEY (`decision_id`) REFERENCES `decision_recurso_apelacion` (`id`),
  CONSTRAINT `FKh4jvl6ykdg850mahuqf1c09l8` FOREIGN KEY (`oficio_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKl0cwr5d6fnopc6ic4680h4y7p` FOREIGN KEY (`carpeta_origen_id`) REFERENCES `carpeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
