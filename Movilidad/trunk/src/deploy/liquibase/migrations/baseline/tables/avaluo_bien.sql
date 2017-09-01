--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `avaluo_bien` (
  `id` int(11) NOT NULL,
  `valor_avaluo` decimal(19,2) NOT NULL,
  `bien_id` int(11) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKapykie9qx5nvkism9vqr7l522` (`bien_id`),
  KEY `FKgsriu2m96wpgpnjp2albecvob` (`estado_id`),
  CONSTRAINT `FKapykie9qx5nvkism9vqr7l522` FOREIGN KEY (`bien_id`) REFERENCES `bien` (`id`),
  CONSTRAINT `FKgsriu2m96wpgpnjp2albecvob` FOREIGN KEY (`estado_id`) REFERENCES `estado_avaluo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
