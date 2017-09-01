--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien_vehiculo` (
  `investigacion_bien_id` int(11) NOT NULL,
  `vehiculo_id` int(11) NOT NULL,
  UNIQUE KEY `UK_fqbqtxons64uv32bwgdooxoul` (`vehiculo_id`),
  KEY `FK4a47q67o3suw4n0ehr2bhl3bw` (`investigacion_bien_id`),
  CONSTRAINT `FK4a47q67o3suw4n0ehr2bhl3bw` FOREIGN KEY (`investigacion_bien_id`) REFERENCES `investigacion_bien` (`id`),
  CONSTRAINT `FKhpgnwwdmpaafbe4bcaxnekdrv` FOREIGN KEY (`vehiculo_id`) REFERENCES `bien_vehiculo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
