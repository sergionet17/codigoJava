--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `departamento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `pais_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs4ob59h57ccjv16ie2esul77h` (`pais_id`),
  CONSTRAINT `FKs4ob59h57ccjv16ie2esul77h` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
