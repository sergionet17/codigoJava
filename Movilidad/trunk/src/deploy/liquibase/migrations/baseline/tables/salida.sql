--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `salida` (
  `id` binary(255) NOT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `inmovilizacion_id` binary(255) DEFAULT NULL,
  `subsanacion_id` binary(255) DEFAULT NULL,
  `tipo_salida_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgb9yco9exc1c2n7kc56ubo6pv` (`inmovilizacion_id`),
  KEY `FKnh4xngtyy0ope7811xx53n9s6` (`subsanacion_id`),
  KEY `FKthwaf6oepung6ygnwolup57gj` (`tipo_salida_id`),
  CONSTRAINT `FKgb9yco9exc1c2n7kc56ubo6pv` FOREIGN KEY (`inmovilizacion_id`) REFERENCES `inmovilizacion` (`id`),
  CONSTRAINT `FKnh4xngtyy0ope7811xx53n9s6` FOREIGN KEY (`subsanacion_id`) REFERENCES `subsanacion` (`id`),
  CONSTRAINT `FKthwaf6oepung6ygnwolup57gj` FOREIGN KEY (`tipo_salida_id`) REFERENCES `tipo_salida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
