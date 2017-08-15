--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `rol_perfil` (
  `rol_id` varchar(36) NOT NULL,
  `perfil_id` varchar(36) NOT NULL,
  KEY `FKmnwaurfmwk3mmnsmno4uohbx6` (`perfil_id`),
  KEY `FKloc2fkqngrr22pptec3ghcp2k` (`rol_id`),
  CONSTRAINT `FKloc2fkqngrr22pptec3ghcp2k` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKmnwaurfmwk3mmnsmno4uohbx6` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
