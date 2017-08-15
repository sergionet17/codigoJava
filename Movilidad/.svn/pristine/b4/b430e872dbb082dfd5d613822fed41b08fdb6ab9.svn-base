--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `diligencia_remate` (
  `id` int(11) NOT NULL,
  `deudor_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb8o5hw2h1fseqc63vntbfe39a` (`deudor_id`),
  CONSTRAINT `FKb8o5hw2h1fseqc63vntbfe39a` FOREIGN KEY (`deudor_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
