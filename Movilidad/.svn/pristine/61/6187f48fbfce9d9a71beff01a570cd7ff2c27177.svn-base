--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `licencia` (
  `id` binary(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `categoria_licencia_id` int(11) NOT NULL,
  `organismo_transito_id` int(11) NOT NULL,
  `persona_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKndfe51wtfp3e7tlwg9ncum3px` (`categoria_licencia_id`),
  KEY `FKapp2430wrllgke7d75dxjhewu` (`organismo_transito_id`),
  KEY `FKpyrc0vdouuppbaplyx7yd2af8` (`persona_id`),
  CONSTRAINT `FKapp2430wrllgke7d75dxjhewu` FOREIGN KEY (`organismo_transito_id`) REFERENCES `organismo_transito` (`id`),
  CONSTRAINT `FKndfe51wtfp3e7tlwg9ncum3px` FOREIGN KEY (`categoria_licencia_id`) REFERENCES `categoria_licencia` (`id`),
  CONSTRAINT `FKpyrc0vdouuppbaplyx7yd2af8` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
