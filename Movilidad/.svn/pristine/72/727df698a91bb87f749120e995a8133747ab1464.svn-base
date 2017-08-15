--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `constancia_ejecutoria_automatica` (
  `id` binary(255) NOT NULL,
  `fecha_expedicion` datetime NOT NULL,
  `numero` varchar(255) NOT NULL,
  `autoridad_id` varchar(36) NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `comparendo_id` varchar(36) NOT NULL,
  `documento_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76n95jntu7235bb3ykb9kowjr` (`autoridad_id`),
  KEY `FKs00addhcv6mywbofi2dqlbqsy` (`carpeta_id`),
  KEY `FK8p0rneh7ionkihj1p287w484x` (`comparendo_id`),
  KEY `FKm1klowy5wopsf4g7ad2hb9r88` (`documento_id`),
  CONSTRAINT `FK76n95jntu7235bb3ykb9kowjr` FOREIGN KEY (`autoridad_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK8p0rneh7ionkihj1p287w484x` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FKm1klowy5wopsf4g7ad2hb9r88` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKs00addhcv6mywbofi2dqlbqsy` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
