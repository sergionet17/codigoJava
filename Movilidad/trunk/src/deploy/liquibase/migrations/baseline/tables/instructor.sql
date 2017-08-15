--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `instructor` (
  `id` varchar(36) NOT NULL,
  `fin_vinculacion` datetime DEFAULT NULL,
  `inicio_vinculacion` datetime DEFAULT NULL,
  `persona_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmsobhey271816of13j7uyx7m` (`persona_id`),
  CONSTRAINT `FKrmsobhey271816of13j7uyx7m` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
