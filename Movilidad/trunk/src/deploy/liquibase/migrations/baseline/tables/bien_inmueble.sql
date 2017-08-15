--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `bien_inmueble` (
  `id` int(11) NOT NULL,
  `folio` varchar(255) NOT NULL,
  `matricula_inmoviliaria` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `soporte_id` varchar(36) DEFAULT NULL,
  `zona_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2uvdw0skre7phfem4em28jd3e` (`soporte_id`),
  KEY `FK8dtl487t1id27cefcbey69rrm` (`zona_id`),
  CONSTRAINT `FK2uvdw0skre7phfem4em28jd3e` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FK8dtl487t1id27cefcbey69rrm` FOREIGN KEY (`zona_id`) REFERENCES `zona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
