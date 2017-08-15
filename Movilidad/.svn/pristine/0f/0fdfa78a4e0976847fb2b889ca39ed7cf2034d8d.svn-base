--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `comparendo_cartera` (
  `comparendo_id` varchar(36) NOT NULL,
  `cartera_id` binary(255) NOT NULL,
  UNIQUE KEY `UK_pdo41e0xutcdgy86c611l9sl4` (`cartera_id`),
  KEY `FK7v79vbx3yq0h1jgakbi9as4uu` (`comparendo_id`),
  CONSTRAINT `FK7v79vbx3yq0h1jgakbi9as4uu` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FKiua2pok2uivduanutlgtuga3p` FOREIGN KEY (`cartera_id`) REFERENCES `entrada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
