--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `documento_acciones` (
  `documento_id` varchar(36) NOT NULL,
  `acciones_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_o6hw8t1biy1c7bnpv7ufwmc7o` (`acciones_id`),
  KEY `FK3458kw5gountls50hr9shlo3d` (`documento_id`),
  CONSTRAINT `FK3458kw5gountls50hr9shlo3d` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FK743vvttvtcbfff4vlsfwjxf44` FOREIGN KEY (`acciones_id`) REFERENCES `accion_documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
