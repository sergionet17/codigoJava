--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `sede` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
