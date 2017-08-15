--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `solicitud_devolucion` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `beneficiario_id` varchar(36) NOT NULL,
  `carpeta_id` varchar(36) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `oficio_id` varchar(36) NOT NULL,
  `radicador_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22yfauds1k3t7xl3ilqb2t9bn` (`beneficiario_id`),
  KEY `FK8bu2nb3wduqckn356dt5uxxm5` (`carpeta_id`),
  KEY `FKjhn5no05ca86f6y6of0j0fepg` (`estado_id`),
  KEY `FKi4bk0qqi1folq0ojkcgymn4fe` (`oficio_id`),
  KEY `FK4551wteferxxienv5kmqnogi3` (`radicador_id`),
  CONSTRAINT `FK22yfauds1k3t7xl3ilqb2t9bn` FOREIGN KEY (`beneficiario_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FK4551wteferxxienv5kmqnogi3` FOREIGN KEY (`radicador_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK8bu2nb3wduqckn356dt5uxxm5` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKi4bk0qqi1folq0ojkcgymn4fe` FOREIGN KEY (`oficio_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKjhn5no05ca86f6y6of0j0fepg` FOREIGN KEY (`estado_id`) REFERENCES `estado_devolucion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
