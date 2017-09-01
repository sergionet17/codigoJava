--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `preliminares_investigacion` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `investigacion_id` binary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6wgpxs7onlhd87m6m81xea9gs` (`carpeta_id`),
  KEY `FKm3pah3d8lpw9w13ohp1dl41xk` (`estado_id`),
  KEY `FK9v9yqlnwrnf9uq2togc79cfun` (`investigacion_id`),
  CONSTRAINT `FK6wgpxs7onlhd87m6m81xea9gs` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FK9v9yqlnwrnf9uq2togc79cfun` FOREIGN KEY (`investigacion_id`) REFERENCES `investigacion` (`id`),
  CONSTRAINT `FKm3pah3d8lpw9w13ohp1dl41xk` FOREIGN KEY (`estado_id`) REFERENCES `estado_preliminares_investigacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
