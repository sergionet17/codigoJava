--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_pruebas` (
  `investigacion_id` binary(255) NOT NULL,
  `pruebas_id` int(11) NOT NULL,
  UNIQUE KEY `UK_lyv5sw2c3ocwujk755aupfypg` (`pruebas_id`),
  KEY `FKqbtk3cwpk93h63wkjnefl7txr` (`investigacion_id`),
  CONSTRAINT `FKh49g0wk10573oa2mqu36bfw5o` FOREIGN KEY (`pruebas_id`) REFERENCES `prueba_investigacion` (`id`),
  CONSTRAINT `FKqbtk3cwpk93h63wkjnefl7txr` FOREIGN KEY (`investigacion_id`) REFERENCES `investigacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
