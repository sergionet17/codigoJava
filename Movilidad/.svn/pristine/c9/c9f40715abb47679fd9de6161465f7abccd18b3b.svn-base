--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `titulo_ejecutivo_tipo_cartera` (
  `titulo_ejecutivo_id` int(11) NOT NULL,
  `tipo_cartera_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ax0vu0kyxuws22w36h064lnqi` (`tipo_cartera_id`),
  KEY `FKdx29i38e40mgsqxcki1tphn59` (`titulo_ejecutivo_id`),
  CONSTRAINT `FKdx29i38e40mgsqxcki1tphn59` FOREIGN KEY (`titulo_ejecutivo_id`) REFERENCES `titulo_ejecutivo` (`id`),
  CONSTRAINT `FKk6r2npknb1bjpfeqgx4po6a4j` FOREIGN KEY (`tipo_cartera_id`) REFERENCES `tipo_cartera_coactivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
