--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `permiso_roles` (
  `permiso_nombre` varchar(255) NOT NULL,
  `roles_id` varchar(36) NOT NULL,
  KEY `FKjp6r5ulrrqddb85ey6cgi0rko` (`roles_id`),
  KEY `FKdpq073hggkgmma81ilguk7uiw` (`permiso_nombre`),
  CONSTRAINT `FKdpq073hggkgmma81ilguk7uiw` FOREIGN KEY (`permiso_nombre`) REFERENCES `permiso` (`nombre`),
  CONSTRAINT `FKjp6r5ulrrqddb85ey6cgi0rko` FOREIGN KEY (`roles_id`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
