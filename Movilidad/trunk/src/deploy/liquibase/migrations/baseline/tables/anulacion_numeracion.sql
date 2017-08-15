--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `anulacion_numeracion` (
  `id` varchar(36) NOT NULL,
  `fecha` datetime NOT NULL,
  `resolucion_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiq3q4viqob0ytd4of72wl5es6` (`resolucion_id`),
  CONSTRAINT `FKiq3q4viqob0ytd4of72wl5es6` FOREIGN KEY (`resolucion_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
