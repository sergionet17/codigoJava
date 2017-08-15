--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `anulacion_numeracion_numeros` (
  `anulacion_numeracion_id` varchar(36) NOT NULL,
  `numeros_numero` int(11) NOT NULL,
  UNIQUE KEY `UK_5fsy1hhgwsday7jafyqiox4xh` (`numeros_numero`),
  KEY `FK48jji8uc0xwfjle37c98r0ftm` (`anulacion_numeracion_id`),
  CONSTRAINT `FK48jji8uc0xwfjle37c98r0ftm` FOREIGN KEY (`anulacion_numeracion_id`) REFERENCES `anulacion_numeracion` (`id`),
  CONSTRAINT `FKeuhtmhxnxebwoird1lqs48v9b` FOREIGN KEY (`numeros_numero`) REFERENCES `numero_comparendo` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
