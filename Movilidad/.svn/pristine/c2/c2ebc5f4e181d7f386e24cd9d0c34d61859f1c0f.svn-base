--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `bien_vehiculo` (
  `id` int(11) NOT NULL,
  `embargado` bit(1) DEFAULT NULL,
  `inmovilizado` bit(1) DEFAULT NULL,
  `placa` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `soporte_id` varchar(36) DEFAULT NULL,
  `tercero_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkcp90n8p06nvmdn4gm3780k53` (`soporte_id`),
  KEY `FKlcfya9ajqjqtugk3cqquj3jqv` (`tercero_id`),
  CONSTRAINT `FKkcp90n8p06nvmdn4gm3780k53` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKlcfya9ajqjqtugk3cqquj3jqv` FOREIGN KEY (`tercero_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
