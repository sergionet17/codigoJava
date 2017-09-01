--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `recurso_queja` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `carpeta_origen_id` varchar(36) NOT NULL,
  `decision_id` int(11) DEFAULT NULL,
  `estado_id` int(11) NOT NULL,
  `oficio_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd1ms1kfjheuiajmfl8w41t36d` (`carpeta_origen_id`),
  KEY `FKqop8c6mpvr8mhpplxq2ib66pf` (`decision_id`),
  KEY `FKpxj6thcvoeb8lwff2caxk4lo8` (`estado_id`),
  KEY `FKruqcitefqnp5kqmv3upehlc61` (`oficio_id`),
  CONSTRAINT `FKd1ms1kfjheuiajmfl8w41t36d` FOREIGN KEY (`carpeta_origen_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKpxj6thcvoeb8lwff2caxk4lo8` FOREIGN KEY (`estado_id`) REFERENCES `estado_recurso_queja` (`id`),
  CONSTRAINT `FKqop8c6mpvr8mhpplxq2ib66pf` FOREIGN KEY (`decision_id`) REFERENCES `decision_recurso_queja` (`id`),
  CONSTRAINT `FKruqcitefqnp5kqmv3upehlc61` FOREIGN KEY (`oficio_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
