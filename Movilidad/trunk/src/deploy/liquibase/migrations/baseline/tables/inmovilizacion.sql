--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `inmovilizacion` (
  `id` binary(255) NOT NULL,
  `fecha` datetime NOT NULL,
  `numero` varchar(255) NOT NULL,
  `salida_id` binary(255) DEFAULT NULL,
  `vehiculo_id` binary(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn091n70wn5dtdwrec3dgsdext` (`salida_id`),
  KEY `FKls6k7br7uu7saro5hujbix3wm` (`vehiculo_id`),
  CONSTRAINT `FKls6k7br7uu7saro5hujbix3wm` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`),
  CONSTRAINT `FKn091n70wn5dtdwrec3dgsdext` FOREIGN KEY (`salida_id`) REFERENCES `salida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
