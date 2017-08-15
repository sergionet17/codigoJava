--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `infraccion` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `fin_vigencia` datetime NOT NULL,
  `grado` int(11) DEFAULT NULL,
  `inicio_vigencia` datetime NOT NULL,
  `nombre` varchar(1000) NOT NULL,
  `reincidencias` int(11) DEFAULT NULL,
  `valor` decimal(19,2) NOT NULL,
  `unidad_medida_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ef6uynouajooq3rsyppexnc5` (`unidad_medida_id`),
  CONSTRAINT `FK2ef6uynouajooq3rsyppexnc5` FOREIGN KEY (`unidad_medida_id`) REFERENCES `unidad_medida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
