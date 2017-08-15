--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `bien_sueldo` (
  `id` int(11) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `empresa_id` int(11) NOT NULL,
  `soporte_id` varchar(36) DEFAULT NULL,
  `tercero_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd3i8jrx8v3me5j7c9q1sw4tff` (`empresa_id`),
  KEY `FKab8kcytd9pte79hux1ehnkbw1` (`soporte_id`),
  KEY `FKco37bq3h5a0mybm9g0mfnsmn0` (`tercero_id`),
  CONSTRAINT `FKab8kcytd9pte79hux1ehnkbw1` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKco37bq3h5a0mybm9g0mfnsmn0` FOREIGN KEY (`tercero_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKd3i8jrx8v3me5j7c9q1sw4tff` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
