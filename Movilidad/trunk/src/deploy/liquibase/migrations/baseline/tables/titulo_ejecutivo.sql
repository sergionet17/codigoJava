--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `titulo_ejecutivo` (
  `id` int(11) NOT NULL,
  `fecha_ejecutoria` datetime NOT NULL,
  `fecha_titulo` datetime NOT NULL,
  `participacion` int(11) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `comparendo_id` varchar(36) NOT NULL,
  `dependencia_id` varchar(36) NOT NULL,
  `deudor_id` varchar(36) NOT NULL,
  `estado_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKatfamptva3fe14jnbf9t3anm1` (`carpeta_id`),
  KEY `FKnpvr49tkdxjxy03r5ak5c4x73` (`comparendo_id`),
  KEY `FK9wwmgddky27mb4peump8rjgdb` (`dependencia_id`),
  KEY `FKm7ir5wbbs3lxhxea0pb9h91c3` (`deudor_id`),
  KEY `FK1n7qm3xajt9o7l70hwjq9ncn1` (`estado_id`),
  CONSTRAINT `FK1n7qm3xajt9o7l70hwjq9ncn1` FOREIGN KEY (`estado_id`) REFERENCES `estado_titulo` (`id`),
  CONSTRAINT `FK9wwmgddky27mb4peump8rjgdb` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencia` (`id`),
  CONSTRAINT `FKatfamptva3fe14jnbf9t3anm1` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKm7ir5wbbs3lxhxea0pb9h91c3` FOREIGN KEY (`deudor_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKnpvr49tkdxjxy03r5ak5c4x73` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
