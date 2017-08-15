--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `prueba_investigacion` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado_id` int(11) NOT NULL,
  `tercero_id` varchar(36) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqwrs1ayrwyrl5mp47w7oultgp` (`estado_id`),
  KEY `FKdulqi7rfd1uuatuh2rloofpga` (`tercero_id`),
  KEY `FKk8kqpx63fd4niq7bu0u8c81sk` (`tipo_id`),
  CONSTRAINT `FKdulqi7rfd1uuatuh2rloofpga` FOREIGN KEY (`tercero_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKk8kqpx63fd4niq7bu0u8c81sk` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_prueba_investigacion` (`id`),
  CONSTRAINT `FKqwrs1ayrwyrl5mp47w7oultgp` FOREIGN KEY (`estado_id`) REFERENCES `estado_prueba_investigacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
