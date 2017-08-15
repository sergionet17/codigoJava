--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `recurso_comparendo` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `comparendo_id` varchar(36) NOT NULL,
  `documento_id` varchar(36) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmmxyn3ca9xdj3dsenifopxifv` (`comparendo_id`),
  KEY `FK7f1n98htt2nl9pkufck8gfcck` (`documento_id`),
  KEY `FKpxu0h3j5eo8aipq9und961d3n` (`tipo_id`),
  CONSTRAINT `FK7f1n98htt2nl9pkufck8gfcck` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKmmxyn3ca9xdj3dsenifopxifv` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FKpxu0h3j5eo8aipq9und961d3n` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_recurso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
