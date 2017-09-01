--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `turno_firma` (
  `id` binary(255) NOT NULL,
  `desde` datetime NOT NULL,
  `hasta` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `autoridad_id` varchar(36) NOT NULL,
  `horario_id` binary(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo6vms27y3uy8vf3h5kkheh85y` (`autoridad_id`),
  KEY `FK8hffecpfvrl0x1nusi7tod18f` (`horario_id`),
  CONSTRAINT `FK8hffecpfvrl0x1nusi7tod18f` FOREIGN KEY (`horario_id`) REFERENCES `horario_firma` (`id`),
  CONSTRAINT `FKo6vms27y3uy8vf3h5kkheh85y` FOREIGN KEY (`autoridad_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
