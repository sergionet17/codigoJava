--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `email` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rebota` bit(1) DEFAULT NULL,
  `verificado` bit(1) DEFAULT NULL,
  `persona_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcvnftwqc4pbmeo3u88x3wr4ue` (`persona_id`),
  CONSTRAINT `FKcvnftwqc4pbmeo3u88x3wr4ue` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
