--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `numero_comparendo` (
  `numero` int(11) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `rango_numeracion_id` varchar(36) NOT NULL,
  `rango_numeracion` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `FKqqeykpoh7c6h9a9ooyhjtci6a` (`estado_id`),
  KEY `FKrkg1ab9eqosbq5ngq6160wkvn` (`rango_numeracion_id`),
  KEY `FKiq9s2ipc1tseyl3cbncd9isei` (`rango_numeracion`),
  CONSTRAINT `FKiq9s2ipc1tseyl3cbncd9isei` FOREIGN KEY (`rango_numeracion`) REFERENCES `rango_numeracion` (`id`),
  CONSTRAINT `FKqqeykpoh7c6h9a9ooyhjtci6a` FOREIGN KEY (`estado_id`) REFERENCES `estado_numero_comparendo` (`id`),
  CONSTRAINT `FKrkg1ab9eqosbq5ngq6160wkvn` FOREIGN KEY (`rango_numeracion_id`) REFERENCES `rango_numeracion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
