--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `localidad_id` varchar(255) DEFAULT NULL,
  `municipio_id` varchar(255) NOT NULL,
  `sistema_origen_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbrm0ogdtkt096len14mgaaau7` (`localidad_id`),
  KEY `FKk3o0vkhbccv870al7odoctuxi` (`municipio_id`),
  KEY `FKatqefxixhmwyeyqop9l8xj3oa` (`sistema_origen_id`),
  CONSTRAINT `FKatqefxixhmwyeyqop9l8xj3oa` FOREIGN KEY (`sistema_origen_id`) REFERENCES `sistema_origen` (`id`),
  CONSTRAINT `FKbrm0ogdtkt096len14mgaaau7` FOREIGN KEY (`localidad_id`) REFERENCES `localidad` (`id`),
  CONSTRAINT `FKk3o0vkhbccv870al7odoctuxi` FOREIGN KEY (`municipio_id`) REFERENCES `municipio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
