--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `audiencia_comparendo` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `abogado_id` varchar(36) NOT NULL,
  `comparendo_id` varchar(36) NOT NULL,
  `documento_id` varchar(36) NOT NULL,
  `fallo_id` int(11) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK197gpwpk7hgdktsb20of0ewje` (`abogado_id`),
  KEY `FKe6carfsqc1umm7yvklspyauej` (`comparendo_id`),
  KEY `FKkye220ws7nm4bf8eb6ct3gupr` (`documento_id`),
  KEY `FKt85a9k01ewp1pt602k797wukp` (`fallo_id`),
  KEY `FKar7y4uadmjy8e6m27mhgh1m2r` (`tipo_id`),
  CONSTRAINT `FK197gpwpk7hgdktsb20of0ewje` FOREIGN KEY (`abogado_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKar7y4uadmjy8e6m27mhgh1m2r` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_audiencia_comparendo` (`id`),
  CONSTRAINT `FKe6carfsqc1umm7yvklspyauej` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FKkye220ws7nm4bf8eb6ct3gupr` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKt85a9k01ewp1pt602k797wukp` FOREIGN KEY (`fallo_id`) REFERENCES `fallo_audiencia_comparendo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
