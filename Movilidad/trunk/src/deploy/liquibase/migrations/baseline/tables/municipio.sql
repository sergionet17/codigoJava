--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `municipio` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `departamento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr52i0ndybrnmns34vmihs292c` (`departamento_id`),
  CONSTRAINT `FKr52i0ndybrnmns34vmihs292c` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
