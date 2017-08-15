--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `reinicidencia` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `expediente_id` varchar(36) NOT NULL,
  `fallo_id` varchar(36) NOT NULL,
  `reincidente_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6lkfybbc2b2fyrmoefpyfbmj4` (`expediente_id`),
  KEY `FK4l7fsg099vyd3ginml1u69mo7` (`fallo_id`),
  KEY `FKmr6uvqf1j76d7fg8fgsxdxy7v` (`reincidente_id`),
  CONSTRAINT `FK4l7fsg099vyd3ginml1u69mo7` FOREIGN KEY (`fallo_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FK6lkfybbc2b2fyrmoefpyfbmj4` FOREIGN KEY (`expediente_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKmr6uvqf1j76d7fg8fgsxdxy7v` FOREIGN KEY (`reincidente_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
