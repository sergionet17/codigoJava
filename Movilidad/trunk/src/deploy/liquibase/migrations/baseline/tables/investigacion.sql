--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion` (
  `id` binary(255) NOT NULL,
  `fecha` datetime NOT NULL,
  `recibio_alegatos` bit(1) DEFAULT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `fallo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm85nxg8ity94aajrgjwvla54h` (`carpeta_id`),
  KEY `FKlylx2tx5xljohqqiw7xh3fw67` (`estado_id`),
  KEY `FKgfshipf9871rlp27hbdeodqkl` (`fallo_id`),
  CONSTRAINT `FKgfshipf9871rlp27hbdeodqkl` FOREIGN KEY (`fallo_id`) REFERENCES `fallo_investigacion` (`id`),
  CONSTRAINT `FKlylx2tx5xljohqqiw7xh3fw67` FOREIGN KEY (`estado_id`) REFERENCES `estado_investigacion` (`id`),
  CONSTRAINT `FKm85nxg8ity94aajrgjwvla54h` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
