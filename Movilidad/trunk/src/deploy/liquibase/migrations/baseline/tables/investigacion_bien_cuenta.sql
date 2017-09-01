--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien_cuenta` (
  `investigacion_bien_id` int(11) NOT NULL,
  `cuenta_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dvautkq8105cdihxs4xnccra3` (`cuenta_id`),
  KEY `FKoue9i88nvdsy6qm0rawkmi3du` (`investigacion_bien_id`),
  CONSTRAINT `FK9v4jpryv90wevnhoc2kq4277q` FOREIGN KEY (`cuenta_id`) REFERENCES `bien_cuenta` (`id`),
  CONSTRAINT `FKoue9i88nvdsy6qm0rawkmi3du` FOREIGN KEY (`investigacion_bien_id`) REFERENCES `investigacion_bien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
