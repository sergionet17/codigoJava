--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `resolucion_automatica_de_fallo` (
  `id` binary(255) NOT NULL,
  `fecha_expedicion` datetime NOT NULL,
  `numero` varchar(255) NOT NULL,
  `autoridad_id` varchar(36) NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `comparendo_id` varchar(36) NOT NULL,
  `documento_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo58aa6wonjj0kktr0rl46x1ej` (`autoridad_id`),
  KEY `FKlbqaakk64x18prnftem9yqwod` (`carpeta_id`),
  KEY `FKlg27qeeaun85br6437pt1orkc` (`comparendo_id`),
  KEY `FK8507pwj5vuhtosce0s6p3fqyv` (`documento_id`),
  CONSTRAINT `FK8507pwj5vuhtosce0s6p3fqyv` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKlbqaakk64x18prnftem9yqwod` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKlg27qeeaun85br6437pt1orkc` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FKo58aa6wonjj0kktr0rl46x1ej` FOREIGN KEY (`autoridad_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
