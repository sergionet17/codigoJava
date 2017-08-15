--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `recurso_apelacion_pruebas` (
  `recurso_apelacion_id` int(11) NOT NULL,
  `pruebas_id` int(11) NOT NULL,
  UNIQUE KEY `UK_fdhmhafi63qetwgmt84pv26p6` (`pruebas_id`),
  KEY `FKkwaw16vtxki4yeeknyd1f9ehe` (`recurso_apelacion_id`),
  CONSTRAINT `FK9xr4j7oosombtylmoi15rso0h` FOREIGN KEY (`pruebas_id`) REFERENCES `prueba_segunda_instancia` (`id`),
  CONSTRAINT `FKkwaw16vtxki4yeeknyd1f9ehe` FOREIGN KEY (`recurso_apelacion_id`) REFERENCES `recurso_apelacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
