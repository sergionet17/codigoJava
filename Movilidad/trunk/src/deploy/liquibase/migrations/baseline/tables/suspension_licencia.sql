--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `suspension_licencia` (
  `id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado_id` int(11) NOT NULL,
  `expediente_id` varchar(36) NOT NULL,
  `licencia_id` binary(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoo1j68vw5uu6g5w8my9b0m941` (`estado_id`),
  KEY `FKtl82jddvuwhjw8e2j2drqca7p` (`expediente_id`),
  KEY `FKg0uvied0sogx6ud6785csreqm` (`licencia_id`),
  CONSTRAINT `FKg0uvied0sogx6ud6785csreqm` FOREIGN KEY (`licencia_id`) REFERENCES `licencia` (`id`),
  CONSTRAINT `FKoo1j68vw5uu6g5w8my9b0m941` FOREIGN KEY (`estado_id`) REFERENCES `estado_suspension_licencia` (`id`),
  CONSTRAINT `FKtl82jddvuwhjw8e2j2drqca7p` FOREIGN KEY (`expediente_id`) REFERENCES `carpeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
