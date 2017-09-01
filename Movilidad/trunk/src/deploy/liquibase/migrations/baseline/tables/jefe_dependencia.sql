--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `jefe_dependencia` (
  `id` varchar(36) NOT NULL,
  `dependencia_id` varchar(36) DEFAULT NULL,
  `persona_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp5eg1yn23ap01qsu2irw39u1f` (`dependencia_id`),
  KEY `FKpr1xv5vf5nlusxjtmdb1g1pr0` (`persona_id`),
  CONSTRAINT `FKp5eg1yn23ap01qsu2irw39u1f` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencia` (`id`),
  CONSTRAINT `FKpr1xv5vf5nlusxjtmdb1g1pr0` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
