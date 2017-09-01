--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `patio` (
  `id` int(11) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `direccion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowjkl8lipqbbygm0o2vkd9fbr` (`direccion_id`),
  CONSTRAINT `FKowjkl8lipqbbygm0o2vkd9fbr` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
