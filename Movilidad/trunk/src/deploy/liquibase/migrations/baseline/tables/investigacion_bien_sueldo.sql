--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien_sueldo` (
  `investigacion_bien_id` int(11) NOT NULL,
  `sueldo_id` int(11) NOT NULL,
  UNIQUE KEY `UK_s90k545f8vv8g162oofk7jhe6` (`sueldo_id`),
  KEY `FKdansae9v4u7x2hcqu2ow40n8m` (`investigacion_bien_id`),
  CONSTRAINT `FKdansae9v4u7x2hcqu2ow40n8m` FOREIGN KEY (`investigacion_bien_id`) REFERENCES `investigacion_bien` (`id`),
  CONSTRAINT `FKicmotr4b3wio40ik5a7woxk13` FOREIGN KEY (`sueldo_id`) REFERENCES `bien_sueldo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
