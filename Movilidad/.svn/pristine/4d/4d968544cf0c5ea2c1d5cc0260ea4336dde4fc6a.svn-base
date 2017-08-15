--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `perfil_roles` (
  `perfil_id` varchar(36) NOT NULL,
  `roles_id` varchar(36) NOT NULL,
  KEY `FK13jplesfssp3rwfqx2c7dqy0m` (`roles_id`),
  KEY `FKittod1qg09r1t8e17i08q4x31` (`perfil_id`),
  CONSTRAINT `FK13jplesfssp3rwfqx2c7dqy0m` FOREIGN KEY (`roles_id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKittod1qg09r1t8e17i08q4x31` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
