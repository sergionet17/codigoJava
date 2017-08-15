--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `salon` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `sede_id` varchar(36) DEFAULT NULL,
  `sede` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3cjpo6m6jdojiwc3w1t7lt8h` (`sede_id`),
  KEY `FKbg0ugg4rysjqamp4cbnt7mtob` (`sede`),
  CONSTRAINT `FKa3cjpo6m6jdojiwc3w1t7lt8h` FOREIGN KEY (`sede_id`) REFERENCES `sede` (`id`),
  CONSTRAINT `FKbg0ugg4rysjqamp4cbnt7mtob` FOREIGN KEY (`sede`) REFERENCES `sede` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
