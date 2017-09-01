--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `testigo` (
  `id` int(11) NOT NULL,
  `persona_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnujmavgt55f7dnho3nwrboule` (`persona_id`),
  CONSTRAINT `FKnujmavgt55f7dnho3nwrboule` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
