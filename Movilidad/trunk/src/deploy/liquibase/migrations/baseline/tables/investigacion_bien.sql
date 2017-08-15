--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien` (
  `id` int(11) NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `resultado_id` int(11) DEFAULT NULL,
  `titulo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsfajya49yjr7elhle2pu4917r` (`carpeta_id`),
  KEY `FK1ioglboy5lhkiom3xiym8g9os` (`estado_id`),
  KEY `FKhptw8ogpf1n9hv0u51te2jmif` (`resultado_id`),
  KEY `FK5j14uwddkb31rw1ylms5um1oe` (`titulo_id`),
  CONSTRAINT `FK1ioglboy5lhkiom3xiym8g9os` FOREIGN KEY (`estado_id`) REFERENCES `estado_investigacion_bien` (`id`),
  CONSTRAINT `FK5j14uwddkb31rw1ylms5um1oe` FOREIGN KEY (`titulo_id`) REFERENCES `titulo_ejecutivo` (`id`),
  CONSTRAINT `FKhptw8ogpf1n9hv0u51te2jmif` FOREIGN KEY (`resultado_id`) REFERENCES `resultado_investigacion_bien` (`id`),
  CONSTRAINT `FKsfajya49yjr7elhle2pu4917r` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
