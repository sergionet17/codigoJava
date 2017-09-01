--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `curso` (
  `id` varchar(36) NOT NULL,
  `fecha` date NOT NULL,
  `fecha_registro` datetime DEFAULT NULL,
  `hora_final` varchar(255) NOT NULL,
  `hora_inicial` varchar(255) NOT NULL,
  `maximo_asistentes` int(11) NOT NULL,
  `tema` varchar(255) NOT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `instructor_id` varchar(36) DEFAULT NULL,
  `salon_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqmocui298dmgsdhurf5sqqedu` (`estado_id`),
  KEY `FKsrwl9m1nnupot43bhv2792r9w` (`instructor_id`),
  KEY `FKnwrfxcleaapmspi777u61o6os` (`salon_id`),
  CONSTRAINT `FKnwrfxcleaapmspi777u61o6os` FOREIGN KEY (`salon_id`) REFERENCES `salon` (`id`),
  CONSTRAINT `FKqmocui298dmgsdhurf5sqqedu` FOREIGN KEY (`estado_id`) REFERENCES `estado_curso` (`id`),
  CONSTRAINT `FKsrwl9m1nnupot43bhv2792r9w` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
