--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_bien_inmueble` (
  `investigacion_bien_id` int(11) NOT NULL,
  `inmueble_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ebmm844oenjhhtevn9o1tss4q` (`inmueble_id`),
  KEY `FKs46pulsjj1agwbdp1x0kmdais` (`investigacion_bien_id`),
  CONSTRAINT `FKfcys880fvrv6qvy91pm1khpmw` FOREIGN KEY (`inmueble_id`) REFERENCES `bien_inmueble` (`id`),
  CONSTRAINT `FKs46pulsjj1agwbdp1x0kmdais` FOREIGN KEY (`investigacion_bien_id`) REFERENCES `investigacion_bien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
