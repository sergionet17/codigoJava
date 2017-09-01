--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `bien_cuenta` (
  `id` int(11) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `banco_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsj5flhewix9uisr3xb3pbixbx` (`banco_id`),
  CONSTRAINT `FKsj5flhewix9uisr3xb3pbixbx` FOREIGN KEY (`banco_id`) REFERENCES `entidad_bancaria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
