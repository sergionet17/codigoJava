--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `localidad` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `municipio_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81l46blgp3kiew5u2t4bt41c0` (`municipio_id`),
  CONSTRAINT `FK81l46blgp3kiew5u2t4bt41c0` FOREIGN KEY (`municipio_id`) REFERENCES `municipio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
