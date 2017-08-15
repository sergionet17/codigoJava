--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `persona_direcciones` (
  `persona_id` varchar(36) NOT NULL,
  `direcciones_id` int(11) NOT NULL,
  UNIQUE KEY `UK_rsf99cdmq7curbftm943h70wx` (`direcciones_id`),
  KEY `FKuk9delbibcqxmu44vphi9bqb` (`persona_id`),
  CONSTRAINT `FKm6pb7n1i9oei1fuj29vbgfk7s` FOREIGN KEY (`direcciones_id`) REFERENCES `direccion` (`id`),
  CONSTRAINT `FKuk9delbibcqxmu44vphi9bqb` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
