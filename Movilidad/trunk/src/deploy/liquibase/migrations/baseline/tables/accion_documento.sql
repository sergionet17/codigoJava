--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `accion_documento` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `tipo_accion_id` int(11) NOT NULL,
  `usuario_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14mru9q6tsw4xtss8mw7i3b89` (`tipo_accion_id`),
  KEY `FKmfbgji9n0y34e3vpy0c3pa02j` (`usuario_id`),
  CONSTRAINT `FK14mru9q6tsw4xtss8mw7i3b89` FOREIGN KEY (`tipo_accion_id`) REFERENCES `tipo_accion` (`id`),
  CONSTRAINT `FKmfbgji9n0y34e3vpy0c3pa02j` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
