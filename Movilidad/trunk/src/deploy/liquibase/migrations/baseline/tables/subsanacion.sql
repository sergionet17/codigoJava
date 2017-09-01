--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `subsanacion` (
  `id` binary(255) NOT NULL,
  `fecha_limite` datetime NOT NULL,
  `salida_id` binary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpqd2uomlv1qhquksx7q8nfqhd` (`salida_id`),
  CONSTRAINT `FKpqd2uomlv1qhquksx7q8nfqhd` FOREIGN KEY (`salida_id`) REFERENCES `salida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
