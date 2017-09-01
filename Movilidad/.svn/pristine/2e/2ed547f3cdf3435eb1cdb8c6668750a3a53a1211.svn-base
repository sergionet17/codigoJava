--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `bien` (
  `id` int(11) NOT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `cuenta_id` int(11) DEFAULT NULL,
  `inmueble_id` int(11) DEFAULT NULL,
  `propietario_id` varchar(36) NOT NULL,
  `soporte_id` varchar(36) DEFAULT NULL,
  `sueldo_id` int(11) DEFAULT NULL,
  `vehiculo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsvdpmginmk56hacquuqg2dvl2` (`cuenta_id`),
  KEY `FKkmg9bu9ldpe4rfn1dfkiqxphw` (`inmueble_id`),
  KEY `FK4u518wydldpm3utbiu5j3j5iv` (`propietario_id`),
  KEY `FKk9epw4xj2vnva0idaeuin8e2i` (`soporte_id`),
  KEY `FKsx44ygado68w7onbso943k40r` (`sueldo_id`),
  KEY `FK1wf864auq7dsg84uiw0lkrn7` (`vehiculo_id`),
  CONSTRAINT `FK1wf864auq7dsg84uiw0lkrn7` FOREIGN KEY (`vehiculo_id`) REFERENCES `bien_vehiculo` (`id`),
  CONSTRAINT `FK4u518wydldpm3utbiu5j3j5iv` FOREIGN KEY (`propietario_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKk9epw4xj2vnva0idaeuin8e2i` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKkmg9bu9ldpe4rfn1dfkiqxphw` FOREIGN KEY (`inmueble_id`) REFERENCES `bien_inmueble` (`id`),
  CONSTRAINT `FKsvdpmginmk56hacquuqg2dvl2` FOREIGN KEY (`cuenta_id`) REFERENCES `bien_cuenta` (`id`),
  CONSTRAINT `FKsx44ygado68w7onbso943k40r` FOREIGN KEY (`sueldo_id`) REFERENCES `bien_sueldo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
