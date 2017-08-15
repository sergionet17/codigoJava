--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien_soporte` (
  `investigacion_bien_id` int(11) NOT NULL,
  `soporte_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_rh2kxy7xr37yg2m7kcanax3q` (`soporte_id`),
  KEY `FKabenkrhkydm8mtmvovahclqs0` (`investigacion_bien_id`),
  CONSTRAINT `FKabenkrhkydm8mtmvovahclqs0` FOREIGN KEY (`investigacion_bien_id`) REFERENCES `investigacion_bien` (`id`),
  CONSTRAINT `FKk6du5q1c4q7qluotpawf4obq6` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
