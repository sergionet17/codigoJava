--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `prueba_segunda_instancia` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `respuesta_id` varchar(36) DEFAULT NULL,
  `solicitud_id` varchar(36) NOT NULL,
  `tercero_id` varchar(36) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj1w3brgbmiia2xyx60cm2ecjj` (`estado_id`),
  KEY `FKc21gvt9fvy6v45kxs65fglgj7` (`respuesta_id`),
  KEY `FKck21kjt9qg1opr41qka6x7lwh` (`solicitud_id`),
  KEY `FK8eiu3as263e7i1ijllvg44cup` (`tercero_id`),
  KEY `FKaytw4dh88uud0351h634mk1os` (`tipo_id`),
  CONSTRAINT `FK8eiu3as263e7i1ijllvg44cup` FOREIGN KEY (`tercero_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKaytw4dh88uud0351h634mk1os` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_prueba_segunda_instancia` (`id`),
  CONSTRAINT `FKc21gvt9fvy6v45kxs65fglgj7` FOREIGN KEY (`respuesta_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKck21kjt9qg1opr41qka6x7lwh` FOREIGN KEY (`solicitud_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKj1w3brgbmiia2xyx60cm2ecjj` FOREIGN KEY (`estado_id`) REFERENCES `estado_prueba_segunda_instancia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
