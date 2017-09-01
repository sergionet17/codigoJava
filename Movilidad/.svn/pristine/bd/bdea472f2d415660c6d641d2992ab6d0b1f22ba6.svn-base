--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `documento` (
  `id` varchar(36) NOT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_radicacion` datetime DEFAULT NULL,
  `hash` varchar(255) NOT NULL,
  `mime_type` varchar(255) NOT NULL,
  `numero_folios` int(11) NOT NULL,
  `radicado` varchar(255) DEFAULT NULL,
  `ref_contenido` varchar(255) NOT NULL,
  `aprobador_id` varchar(36) DEFAULT NULL,
  `dependencia_destino_id` varchar(36) DEFAULT NULL,
  `fuente_envio_id` varchar(36) DEFAULT NULL,
  `revisor_id` varchar(36) DEFAULT NULL,
  `sustanciador_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqj8onb9x5hx77l7gvd91gdk9s` (`aprobador_id`),
  KEY `FK7l9yj9n61i137ynj5obvm2ps7` (`dependencia_destino_id`),
  KEY `FKabeoq76pcttjemscvgh8vn0ql` (`fuente_envio_id`),
  KEY `FKpwyjrnduysxkse5gh8qjfd00` (`revisor_id`),
  KEY `FKfewbmq4675n3a3qwmggj4yuvu` (`sustanciador_id`),
  CONSTRAINT `FK7l9yj9n61i137ynj5obvm2ps7` FOREIGN KEY (`dependencia_destino_id`) REFERENCES `dependencia` (`id`),
  CONSTRAINT `FKabeoq76pcttjemscvgh8vn0ql` FOREIGN KEY (`fuente_envio_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKfewbmq4675n3a3qwmggj4yuvu` FOREIGN KEY (`sustanciador_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKpwyjrnduysxkse5gh8qjfd00` FOREIGN KEY (`revisor_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKqj8onb9x5hx77l7gvd91gdk9s` FOREIGN KEY (`aprobador_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
