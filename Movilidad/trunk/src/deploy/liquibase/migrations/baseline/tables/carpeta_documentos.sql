--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `carpeta_documentos` (
  `carpeta_id` varchar(36) NOT NULL,
  `documentos_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_c5ot507m8wiixmfwes94aphax` (`documentos_id`),
  KEY `FKfq8f3sv91sgvx2uremgl41lb7` (`carpeta_id`),
  CONSTRAINT `FKfq8f3sv91sgvx2uremgl41lb7` FOREIGN KEY (`carpeta_id`) REFERENCES `carpeta` (`id`),
  CONSTRAINT `FKq0bkgtk6615n8851n6nmebus5` FOREIGN KEY (`documentos_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
