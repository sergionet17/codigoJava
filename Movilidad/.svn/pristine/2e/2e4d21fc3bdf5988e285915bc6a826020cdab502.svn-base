--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `agente_transito` (
  `id` int(11) NOT NULL,
  `placa` varchar(255) NOT NULL,
  `primer_apellido` varchar(255) NOT NULL,
  `primer_nombre` varchar(255) NOT NULL,
  `segundo_apellido` varchar(255) DEFAULT NULL,
  `segundo_nombre` varchar(255) DEFAULT NULL,
  `entidad_agente_transito_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1hv5tdlpy71lmphvw74enafvh` (`entidad_agente_transito_id`),
  CONSTRAINT `FK1hv5tdlpy71lmphvw74enafvh` FOREIGN KEY (`entidad_agente_transito_id`) REFERENCES `entidad_agente_transito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
