--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `persona_telefonos` (
  `persona_id` varchar(36) NOT NULL,
  `telefonos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qwduen4eo24y0dlmqo7ffa2hj` (`telefonos_id`),
  KEY `FKk6psp7v2efkes7kiwyfpdeo14` (`persona_id`),
  CONSTRAINT `FKbc56pu2ofcp6hokqei2hefus4` FOREIGN KEY (`telefonos_id`) REFERENCES `telefono` (`id`),
  CONSTRAINT `FKk6psp7v2efkes7kiwyfpdeo14` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
