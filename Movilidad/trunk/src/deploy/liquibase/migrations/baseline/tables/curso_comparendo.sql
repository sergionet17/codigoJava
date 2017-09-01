--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `curso_comparendo` (
  `id` int(11) NOT NULL,
  `fecha_registro` datetime NOT NULL,
  `comparendo_id` varchar(36) DEFAULT NULL,
  `curso_id` varchar(36) DEFAULT NULL,
  `estado_id` int(11) NOT NULL,
  `imagen_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK63ybjr2aubw52ns5ed5wopini` (`comparendo_id`),
  KEY `FK8ajpbd94qlxhakeuxrjrvloyk` (`curso_id`),
  KEY `FKgvljtqt8kkodvkp0cipt2nhyq` (`estado_id`),
  KEY `FKjsflrdm3dmbpuwrm1uh3qrrr5` (`imagen_id`),
  CONSTRAINT `FK63ybjr2aubw52ns5ed5wopini` FOREIGN KEY (`comparendo_id`) REFERENCES `comparendo` (`id`),
  CONSTRAINT `FK8ajpbd94qlxhakeuxrjrvloyk` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKgvljtqt8kkodvkp0cipt2nhyq` FOREIGN KEY (`estado_id`) REFERENCES `estado_asistencia_curso` (`id`),
  CONSTRAINT `FKjsflrdm3dmbpuwrm1uh3qrrr5` FOREIGN KEY (`imagen_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
